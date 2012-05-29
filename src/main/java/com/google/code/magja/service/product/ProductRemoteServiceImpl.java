/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis2.AxisFault;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.product.ConfigurableAttributeData;
import com.google.code.magja.model.product.ConfigurableProductData;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.model.product.ProductLink;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.model.product.Visibility;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.category.CategoryRemoteService;

public class ProductRemoteServiceImpl extends GeneralServiceImpl<Product> implements
        ProductRemoteService {

    private static final long serialVersionUID = -3943518467672208326L;

    private CategoryRemoteService categoryRemoteService;

    private ProductMediaRemoteService productMediaRemoteService;

    private ProductLinkRemoteService productLinkRemoteService;

    private transient final Logger log = LoggerFactory.getLogger(ProductRemoteServiceImpl.class);
 
    Cache<String, Object> cache = new DefaultCacheManager().getCache();
    
    /*
     * (non-Javadoc)
     * 
     * @seecom.google.code.magja.service.product.ProductRemoteService#
     * setCategoryRemoteService
     * (com.google.code.magja.service.category.CategoryRemoteService)
     */
    @Override
    public void setCategoryRemoteService(CategoryRemoteService categoryRemoteService) {
        this.categoryRemoteService = categoryRemoteService;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.google.code.magja.service.product.ProductRemoteService#
     * setProductMediaRemoteService
     * (com.google.code.magja.service.product.ProductMediaRemoteService)
     */
    @Override
    public void setProductMediaRemoteService(ProductMediaRemoteService productMediaRemoteService) {
        this.productMediaRemoteService = productMediaRemoteService;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.google.code.magja.service.product.ProductRemoteService#
     * setProductLinkRemoteService
     * (com.google.code.magja.service.product.ProductLinkRemoteService)
     */
    @Override
    public void setProductLinkRemoteService(ProductLinkRemoteService productLinkRemoteService) {
        this.productLinkRemoteService = productLinkRemoteService;
    }

    /**
     * Create a object product with basic fields from the attribute map
     * 
     * @param mpp
     *            - the attribute map
     * @return Product
     */
    private Product buildProductBasic(Map<String, Object> mpp) {
        Product product = new Product();

        // populate the basic fields
        for (Map.Entry<String, Object> attribute : mpp.entrySet())
            product.set(attribute.getKey(), attribute.getValue());

        return product;
    }

    private Product buildProduct(Map<String, Object> mpp, boolean dependencies)
            throws ServiceException {
        if (dependencies) {
            return buildProduct(mpp, true, true, true, true, true, true);
        } else {
            return buildProduct(mpp, false, false, false, false, false, false);
        }
    }

    private Product buildProductWithCategories(Map<String, Object> mpp) throws ServiceException {
        return buildProduct(mpp, true, false, false, false, false, false);
    }

    /**
     * Build the object Product with your dependencies, for the queries
     * 
     * @param mpp
     * @return Product
     * @throws ServiceException
     */
    private Product buildProduct(Map<String, Object> mpp, boolean loadCategories,
            boolean loadMedia, boolean loadLinks, boolean loadTypes, boolean loadAttributeSet,
            boolean loadInventory) throws ServiceException {

        Product product = buildProductBasic(mpp);

        // product visibility
        if (mpp.get("visibility") != null) {
            Integer visi = new Integer(mpp.get("visibility").toString());
            switch (visi) {
            case 1:
                product.setVisibility(Visibility.NOT_VISIBLE_INDIVIDUALLY);
                break;
            case 2:
                product.setVisibility(Visibility.CATALOG);
                break;
            case 3:
                product.setVisibility(Visibility.SEARCH);
                break;
            case 4:
                product.setVisibility(Visibility.CATALOG_SEARCH);
                break;
            default:
                product.setVisibility(Visibility.CATALOG_SEARCH);
                break;
            }
        }

        // set product type
        if (mpp.get("type") != null) {

            ProductType type = ProductType.getType((String) mpp.get("type"));

            if (type == null && loadTypes) {
                /*
                 * means its a type not covered by the enum, so we have to look
                 * in magento api to get this type
                 */
                List<ProductType> types = listAllProductTypes();
                for (ProductType productType : types) {
                    type = productType.getType((String) mpp.get("type"));
                    break;
                }
            }

            if (type != null)
                product.setType(type);
        }

        // set the attributeSet
        if (mpp.get("set") != null && loadAttributeSet)
            product.setAttributeSet(

            getAttributeSet((String) mpp

            .

            get("set")

            ));

        // categories - dont get the full tree, only basic info of categories
        if (mpp.get("categories") != null)

        {
            if (loadCategories) {
                product.getCategories().addAll(
                        getCategoriesBasicInfo((List<Object>) mpp.get("categories")));
            } else {
                List<Category> categories = new ArrayList<Category>();
                for (Object obj : (List<Object>) mpp.get("categories")) {
                    Integer id = Integer.parseInt((String) obj);
                    categories.add(new Category(id));
                }
                product.setCategories(categories);
            }
        }

        // Inventory
        if (loadInventory)

        {
            Set<Product> products = new HashSet<Product>();
            products.add(product);
            getInventoryInfo(products);
        }

        // medias
        if (loadMedia)
            product.setMedias(productMediaRemoteService.listByProduct(product));

        // product links
        if (loadLinks)
            product.setLinks(productLinkRemoteService.list(product));

        return product;
    }

    /**
     * @param ids
     * @return list of categories with specified ids, just the basic info
     * @throws ServiceException
     */
    private List<Category> getCategoriesBasicInfo(List<Object> ids) throws ServiceException {
    	log.info("getCategoriesBasicInfo {}", ids);
        List<Category> categories = new ArrayList<Category>();
        for (Object obj : ids) {
            Integer id = Integer.parseInt((String) obj);
            Category category = categoryRemoteService.getByIdClean(id);
            categories.add(category);
        }
        return categories;
    }

    /**
     * @param id
     * @return the ProductAttributeSet with the specified id
     * @throws ServiceException
     */
    // TODO: this is called multiple times by ProductService.listAll(), please cache this
    private ProductAttributeSet getAttributeSet(String id) throws ServiceException {
    	String cacheKey = "attributeSet." + id;
    	ProductAttributeSet prdAttSet = (ProductAttributeSet) cache.get(cacheKey);
    	if (prdAttSet != null) {
    		log.trace("Returning cached ProductAttributeSet {}: {}", id, prdAttSet);
    	} else {
	        prdAttSet = new ProductAttributeSet();
	        Integer set_id = Integer.parseInt(id);
	
	        // if are the default attribute set, that not list on the api, so we
	        // have to set manually
	        if (set_id.equals(soapClient.getConfig().getDefaultAttributeSetId())) {
	
	            prdAttSet.setId(set_id);
	            prdAttSet.setName("Default");
	
	        } else {
	            List<Map<String, Object>> setList;
	            try {
	                setList = (List<Map<String, Object>>) soapClient.call(
	                        ResourcePath.ProductAttributeSetList, "");
	                log.debug("{} returned {}", ResourcePath.ProductAttributeSetList.getPath(), setList);
	            } catch (AxisFault e) {
	            	log.error("Error getting attribute set " + id, e);
	                if (debug)
	                    e.printStackTrace();
	                throw new ServiceException(e.getMessage());
	            }
	
	            if (setList != null) {
	                for (Map<String, Object> set : setList) {
	                	// Workaround: Some Magento version (or the magja-catalog-ext extension?) return "set_id",
	                	// while the other returns "attribute_set_id". Let's accept both.
	                	String setId = (String) (set.containsKey("set_id") ? set.get("set_id") : set.get("attribute_set_id")); 
	                	String setName = (String) (set.containsKey("set_name") ? set.get("set_name") : set.get("attribute_set_name"));
	                    if (setId.equals(set_id.toString())) {
	                    	prdAttSet.setId(Integer.valueOf(setId));
	                    	prdAttSet.setName(setName);
	                        for (Map.Entry<String, Object> att : set.entrySet())
	                            prdAttSet.set(att.getKey(), att.getValue());
	
	                        break;
	                    }
	                }
	            }
	        }
	        
	        log.trace("Caching ProductAttributeSet {} as {}: {}", new Object[] { id, cacheKey, prdAttSet });
	        cache.put(cacheKey, prdAttSet);
    	}

        return prdAttSet;
    }

    /**
     * Delete a product by your id (prefered) or your sku
     * 
     * @param id
     * @param sku
     * @throws ServiceException
     */
    private void delete(Integer id, String sku) throws ServiceException {

        Boolean success = false;
        try {
            if (id != null) {
                success = (Boolean) soapClient.call(ResourcePath.ProductDelete, id);
            } else if (sku != null) {
                success = (Boolean) soapClient.call(ResourcePath.ProductDelete, sku);
            }

        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        if (!success)
            throw new ServiceException("Not success deleting product.");

    }

    /**
     * Delete a product by sku and category if empty
     * 
     * @param sku
     * @throws ServiceException
     */
    @Override
    public void deleteWithEmptyCategory(String sku) throws ServiceException {
        Product product = getBySku(sku);
        List<Category> categories = product.getCategories();

        delete(sku);

        if (categories != null) {
            for (Category category : categories) {
                categoryRemoteService.deleteEmptyRecursive(category);
            }
        }
    }

    /**
     * List the products, if dependencies is true, the products will be
     * populated with all your dependencies, otherwise, no.
     * 
     * @param dependencies
     * @return List<Product>
     * @throws ServiceException
     */
    private List<Product> list(boolean dependencies) throws ServiceException {
        List<Product> products = new ArrayList<Product>();

        List<Map<String, Object>> productList;

        try {
            productList = (List<Map<String, Object>>) soapClient.call(ResourcePath.ProductList, "");
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (productList == null)
            return products;

        for (Map<String, Object> mpp : productList)
            products.add(buildProduct(mpp, dependencies));

        return products;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#getBySku(java.
     * lang.String)
     */
    @Override
    public Product getBySku(String sku) throws ServiceException {
        return getBySku(sku, false);
    }

    @Override
    public Product getBySkuWithCategories(String sku) throws ServiceException {
        Map<String, Object> mpp = loadBaseProduct(sku);

        if (mpp == null) {
            return null;
        } else {
            return buildProductWithCategories(mpp);
        }
    }

    @Override
    public Product getBySku(String sku, boolean dependencies) throws ServiceException {

        Map<String, Object> mpp = loadBaseProduct(sku);

        if (mpp == null) {
            return null;
        } else {
            return buildProduct(mpp, dependencies);
        }
    }

    private Map<String, Object> loadBaseProduct(String sku) throws ServiceException {
        Map<String, Object> mpp;
        try {
            mpp = (Map<String, Object>) soapClient.call(ResourcePath.ProductInfo, sku);
        } catch (AxisFault e) {
            if (e.getMessage().indexOf("Product not exists") >= 0) {
                mpp = null;
            } else {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        }

        return mpp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#getById(java
     * .lang .Integer)
     */
    @Override
    public Product getById(Integer id) throws ServiceException {

        Map<String, Object> mpp;
        try {
            mpp = (Map<String, Object>) soapClient.call(ResourcePath.ProductInfo, id);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (mpp == null)
            return null;
        else
            return buildProduct(mpp, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.code.magja.service.product.ProductRemoteService#listAll()
     */
    @Override
    public List<Product> listAll() throws ServiceException {
        return list(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#listAllNoDep()
     */
    @Override
    public List<Product> listAllNoDep() throws ServiceException {
        return list(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#create(code.
     * google .magja.model.product.Product)
     */
    @Override
    public void save(Product product, Product existingProduct) throws ServiceException,
            NoSuchAlgorithmException {
        save(product, existingProduct, "");
    }

    @Override
    public void save(Product product, Product existingProduct, String storeView)
            throws ServiceException, NoSuchAlgorithmException {

        if (existingProduct == null) {
            existingProduct = getBySku(product.getSku());
            if (existingProduct != null) {
                product.setId(existingProduct.getId());
            }
        }

        if (product.getId() != null && product.getId() > 0) {
            // means its a existing product
            try {

                List<Object> newProduct = new LinkedList<Object>();
                newProduct.add(product.getId());
                Map<String, Object> props = product.getAllProperties();
                if (product.getVisibility() != null)
                    props.put("visibility", product.getVisibility().getValue());
                newProduct.add(props);
                if (!storeView.isEmpty()) {
                    newProduct.add(storeView);
                }

                log.info("Updating '" + product.getSku() + "'");

                soapClient.call(ResourcePath.ProductUpdate, newProduct);

                if (product.getType().equals(ProductType.CONFIGURABLE))
                    handleConfigurableForNewProducts(product);

            } catch (NumberFormatException e) {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException(e.getMessage());
            } catch (AxisFault e) {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        } else {
            // means its a new product

            // if is a configurable product, call the proper handle
            if (product.getType().equals(ProductType.CONFIGURABLE))
                handleConfigurableForNewProducts(product);

            try {

                List<Object> newProduct = (LinkedList<Object>) product.serializeToApi();

                log.info("Creating '" + product.getSku() + "'");
                int id = Integer.parseInt((String) soapClient.call(ResourcePath.ProductCreate,
                        newProduct));
                if (id > 0)
                    product.setId(id);
                else
                    throw new ServiceException("Error inserting new Product");

            } catch (NumberFormatException e) {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException(e.getMessage());
            } catch (AxisFault e) {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        }

        // inventory

        if (product.getQty() != null)
            updateInventory(product);

        assignProductMedia(product);
        assignProductLinks(product);
        assignCategories(product, existingProduct);

    }

    private void assignCategories(Product product, Product existingProduct) throws ServiceException {
        boolean found;
        boolean positionDiff;

        for (Category cat : product.getCategories()) {
            found = false;
            positionDiff = false;
            if (existingProduct != null) {
                for (Category category : existingProduct.getCategories()) {
                    if (cat.getId().equals(category.getId())) {
                        found = true;
                        if (category.getPosition() != null && cat.getPosition() != null) {
                            if (!cat.getPosition().equals(category.getPosition())) {
                                positionDiff = true;
                            }
                        }
                        break;
                    }
                }
            }
            if (!found) {
                log.debug("Adding '{}' to category #{} ({}) with position {}", new Object[] {
                    	product.getSku(), cat.getId(), cat.getName(), cat.getPosition() });
                categoryRemoteService.assignProductWithPosition(cat, product, cat.getPosition());
            } else {
                log.debug("Updating '{}' to category #{} ({}) with position {}", new Object[] {
                	product.getSku(), cat.getId(), cat.getName(), cat.getPosition() });
                categoryRemoteService.assignProductWithPosition(cat, product, cat.getPosition());
            }
        }

        List<Category> toBeDeleted = new ArrayList<Category>();
        if (existingProduct != null) {
            for (Category category : existingProduct.getCategories()) {
                found = false;
                for (Category newCategory : product.getCategories()) {
                    if (newCategory.getId().equals(category.getId())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    toBeDeleted.add(category);
                }
            }
        }

        if (toBeDeleted.size() > 0) {
            for (Category category : toBeDeleted) {
                log.info("Removing '" + product.getSku() + " from category " + category.getName());
                categoryRemoteService.removeProduct(category, product);
            }
        }

    }

    private void assignProductMedia(Product product) throws ServiceException,
            NoSuchAlgorithmException {
        // if have media, create it too
        boolean found;
        List<String> mediaFound = new ArrayList<String>();
        List<ProductMedia> toBeDeleted = new ArrayList<ProductMedia>();
        List<ProductMedia> existingMedias = productMediaRemoteService.listByProduct(product);

        if (product.getMedias() != null) {

            if (!product.getMedias().isEmpty()) {
                for (ProductMedia media : product.getMedias()) {
                	if (media.getImage() == null) {
                		log.debug("Skipping media '{}' from product #{} ({}) because image is empty", new Object[] {
                				media.getLabel(), product.getId(), product.getSku() });
                		continue;
                	}
                	
                    found = false;
                    for (ProductMedia existingMedia : existingMedias) {
                        if (existingMedia.getLabel().equals(media.getLabel())) {
                            found = true;
                            existingMedia.setTypes(media.getTypes());
                            existingMedia.setImage(media.getImage());
                            log.info("Updating media '{}' in product #{} ({}) ", new Object[] {
                            		existingMedia.getLabel(), product.getId(), product.getSku() });
                            productMediaRemoteService.update(existingMedia);
                            break;
                        }
                    }

                    if (!found) {
                        if (media.getImage() != null && media.getImage().getData() != null)
                            log.info("Adding media '{}' to product #{} ({}) ", new Object[] {
                            		media.getLabel(), product.getId(), product.getSku() });
                        productMediaRemoteService.create(media);
                    }
                }

            }
        }

        for (ProductMedia existingMedia : existingMedias) {
            found = false;
            if (product.getMedias() != null) {
                for (ProductMedia media : product.getMedias()) {
                    if (existingMedia.getLabel().equals(media.getLabel())) {
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                toBeDeleted.add(existingMedia);
            } else {
                if (mediaFound.contains(existingMedia.getLabel())) {
                    toBeDeleted.add(existingMedia);
                } else {
                    mediaFound.add(existingMedia.getLabel());
                }
            }
        }

        for (ProductMedia existingMedia : toBeDeleted) {
            log.info("Deleting media '{}' from product #{} ({}) ", new Object[] {
            		existingMedia.getLabel(), product.getId(), product.getSku() });
            productMediaRemoteService.delete(existingMedia);
        }

    }

    private void assignProductLinks(Product product) throws ServiceException {

        boolean found;
        List<ProductLink> linksToBeDeleted = new ArrayList<ProductLink>();
        Set<ProductLink> existingLinks = productLinkRemoteService.list(product);

        if (product.getLinks() != null) {
            if (!product.getLinks().isEmpty()) {
                for (ProductLink link : product.getLinks()) {

                    found = false;
                    for (ProductLink existingLink : existingLinks) {
                        if (existingLink.getSku().equals(link.getSku())
                                && existingLink.getLinkType().equals(link.getLinkType())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        if (link.getLinkType() != null
                                && (link.getId() != null || link.getSku() != null))
                            log.info("Assigning " + link.getLinkType() + " Link with product : "
                                    + link.getSku());
                        productLinkRemoteService.assign(product, link);
                    }

                }
            }
        }

        for (ProductLink existingLink : existingLinks) {
            found = false;
            if (product.getLinks() != null) {
                for (ProductLink link : product.getLinks()) {
                    if (existingLink.getSku().equals(link.getSku())
                            && existingLink.getLinkType().equals(link.getLinkType())) {
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                linksToBeDeleted.add(existingLink);
            }
        }

        for (ProductLink link : linksToBeDeleted) {
            log.info("Removing " + link.getLinkType() + " Link with product : " + link.getSku());
            productLinkRemoteService.remove(product, link);
        }

    }

    @Override
    public void setConfigurableAttributes(String productSku, Map<String, String> attributeNames)
            throws ServiceException {

        try {
            List<Object> args = new ArrayList<Object>();
            args.add(productSku);
            args.add(attributeNames);
            String results = (String) soapClient.call(ResourcePath.ProductConfigurableAttributes,
                    args);
        } catch (AxisFault e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void setAssociatedProducts(String productSku, Map<String, String> childProducts)
            throws ServiceException {

        try {
            List<Object> args = new ArrayList<Object>();
            args.add(productSku);
            args.add(childProducts);
            String results = (String) soapClient.call(ResourcePath.ProductAssociateChildren, args);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /*
     * Handle configurable products just for insert new products
     */
    private void handleConfigurableForNewProducts(Product product) throws ServiceException,
            NoSuchAlgorithmException {

        // if isn't a configurable product, stop the execution
        if (!product.getType().equals(ProductType.CONFIGURABLE))
            return;

        if (product.getConfigurableAttributesData() != null) {
            Map<String, Object> confAttrDataMap = new HashMap<String, Object>();

            Integer i = 0;
            for (ConfigurableAttributeData configAttr : product.getConfigurableAttributesData()) {
                confAttrDataMap.put(i.toString(), configAttr.serializeToApi());
                i++;
            }

            product.set("configurable_attributes_data", confAttrDataMap);
        }

        if (product.getConfigurableSubProducts() != null) {

            if (product.getConfigurableProductsData() == null)
                product.setConfigurableProductsData(new HashMap<String, Map<String, Object>>());

            for (ConfigurableProductData prdData : product.getConfigurableSubProducts()) {

                Product subprd = prdData.getProduct();

                if (subprd.getType().equals(ProductType.SIMPLE)) {

                    Product existingProduct = getBySku(subprd.getSku(), false);
                    if (existingProduct != null) {
                        subprd.setId(existingProduct.getId());
                        prdData.setExistingProduct(existingProduct);
                    }

                    this.save(subprd, existingProduct);
                }

                product.getConfigurableProductsData().put(subprd.getId().toString(),
                        prdData.serializeToApi());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.code.magja.service.product.ProductRemoteService#
     * listAllProductTypes ()
     */
    @Override
    public List<ProductType> listAllProductTypes() throws ServiceException {

        List<ProductType> resultList = new ArrayList<ProductType>();

        List<Map<String, Object>> productTypes;
        try {
            productTypes = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.ProductTypeList, "");
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (productTypes == null)
            return resultList;

        ProductType productType;
        for (Map<String, Object> type : productTypes) {
            for (Map.Entry<String, Object> attribute : type.entrySet()) {
                productType = new ProductType((String) attribute.getValue(), attribute.getKey());
                resultList.add(productType);
            }
        }

        return resultList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#delete(java
     * .lang .Integer)
     */
    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#delete(java
     * .lang .String)
     */
    @Override
    public void delete(String sku) throws ServiceException {
        delete(null, sku);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#deleteAll()
     */
    @Override
    public void deleteAll() throws ServiceException {
        List<Product> products = listAllNoDep();
        for (Product product : products) {
            delete(product.getId());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#getInventoryInfo
     * (java.util.Set)
     */
    @Override
    public void getInventoryInfo(Set<Product> products) throws ServiceException {

        String[] productIds = new String[products.size()];
        int i = 0;
        for (Product product : products)
            productIds[i++] = product.getId().toString();

        List<Object> param = new LinkedList<Object>();
        param.add(productIds);

        List<Map<String, Object>> resultList = null;
        try {
            resultList = (List<Map<String, Object>>) soapClient.call(ResourcePath.ProductStockList,
                    param);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        for (Map<String, Object> iv : resultList) {
            for (Product product : products) {
                if (product.getId().equals(Integer.parseInt((String) iv.get("product_id")))) {
                    if (iv.get("qty") != null || !"".equals(iv.get("qty")))
                        product.setQty(Double.parseDouble((String) iv.get("qty")));
                    if (iv.get("is_in_stock") != null || !"".equals(iv.get("is_in_stock"))) {
                        if (iv.get("is_in_stock").toString().equals("0")
                                || iv.get("is_in_stock").toString().equals("false"))
                            product.setInStock(false);
                        else
                            product.setInStock(true);
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#updateInventory
     * (com.google.code.magja.model.product.Product)
     */
    @Override
    public void updateInventory(Product product) throws ServiceException {

        if (product.getId() == null && product.getSku() == null)
            throw new ServiceException(
                    "The product must have the id or the sku seted for update inventory");

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("qty", product.getQty());

        if (product.getInStock() == null)
            product.setInStock(product.getQty() > 0);

        properties.put("is_in_stock", (product.getInStock() ? "1" : "0"));

        List<Object> param = new LinkedList<Object>();
        param.add((product.getId() != null ? product.getId() : product.getSku()));
        param.add(properties);

        try {
            soapClient.call(ResourcePath.ProductStockUpdate, param);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void setManageStock(Product product) throws ServiceException {

        if (product.getId() == null && product.getSku() == null)
            throw new ServiceException(
                    "The product must have the id or the sku seted for update inventory");

        Map<String, Object> properties = new HashMap<String, Object>();

        properties.put("use_config_manage_stock", "1");
        properties.put("manage_stock", "1");

        List<Object> param = new LinkedList<Object>();
        param.add((product.getId() != null ? product.getId() : product.getSku()));
        param.add(properties);

        try {
            soapClient.call(ResourcePath.ProductStockUpdate, param);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void setManageStock(Product product, boolean manageStock) throws ServiceException {

        if (product.getId() == null && product.getSku() == null)
            throw new ServiceException(
                    "The product must have the id or the sku seted for update inventory");

        Map<String, Object> properties = new HashMap<String, Object>();

        if (manageStock) {
            properties.put("use_config_manage_stock", "1");
            properties.put("manage_stock", "1");
        } else {
            properties.put("use_config_manage_stock", "0");
            properties.put("manage_stock", "0");
        }

        List<Object> param = new LinkedList<Object>();
        param.add((product.getId() != null ? product.getId() : product.getSku()));
        param.add(properties);

        try {
            soapClient.call(ResourcePath.ProductStockUpdate, param);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Get products without category
     * 
     * @return List<Product>
     * @throws ServiceException
     */
    @Override
    public List<Product> getWithoutCategory() throws ServiceException {
        List<Product> withoutCategory = new ArrayList<Product>();

        List<Product> products = listAllNoDep();
        for (Product product : products) {
            if (product.getCategories().isEmpty()) {
                withoutCategory.add(product);
            }
        }

        return withoutCategory;
    }

    @Override
    public List<Product> listUpdatedBetween(Date from, Date to) throws ServiceException {
        Map<String, Object> param = new HashMap<String, Object>();
        List<Product> products = new ArrayList<Product>();
        Map<String, String> values = new HashMap<String, String>();

        values.put("from", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from));
        values.put("to", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to));
        param.put("updated_at", values);

        try {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> productList = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.ProductList, param);
            if (productList != null) {
                for (Map<String, Object> mpp : productList)
                    products.add(buildProduct(mpp, false));
            }
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
}
