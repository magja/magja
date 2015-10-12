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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.google.code.magja.model.product.ProductRefMagja;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.model.product.ProductUpdatePrice;
import com.google.code.magja.model.product.Visibility;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ProductRemoteServiceImpl extends GeneralServiceImpl<Product> implements
        ProductRemoteService {

    private static final long serialVersionUID = -3943518467672208326L;
    private transient final Logger log = LoggerFactory.getLogger(ProductRemoteServiceImpl.class);
    
    private RemoteServiceFactory serviceFactory;
    
    public ProductRemoteServiceImpl(MagentoSoapClient soapClient,
			RemoteServiceFactory serviceFactory) {
		super(soapClient);
		this.serviceFactory = serviceFactory;
	}

	/**
     * Cache that caches all objects for 10 minutes after last access
     */
    private Cache<String, ProductAttributeSet> cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).build();
 
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

    private Product buildProduct(Map<String, Object> mpp, Set<String> attributes, boolean dependencies)
            throws ServiceException {
        if (dependencies) {
            return buildProduct(mpp, attributes, ImmutableSet.of(
            		Dependency.CATEGORIES, Dependency.MEDIAS, Dependency.LINKS, Dependency.TYPES, Dependency.ATTRIBUTE_SET,
            		Dependency.INVENTORY));
        } else {
            return buildProduct(mpp, attributes, ImmutableSet.<Dependency>of());
        }
    }
    
    private Product buildProductWithCategories(Map<String, Object> mpp) throws ServiceException {
        return buildProduct(mpp, ImmutableSet.<String>of(),
        		ImmutableSet.of(Dependency.CATEGORIES));
    }

    /**
     * Build the object Product with your dependencies, for the queries
     * 
     * @param mpp
     * @return Product
     * @throws ServiceException
     */
    //boolean loadCategories,
    //boolean loadMedia, boolean loadLinks, boolean loadTypes, boolean loadAttributeSet,
    //boolean loadInventory
    private Product buildProduct(Map<String, Object> mpp, Set<String> attributes,
    		Set<Dependency> dependencies) throws ServiceException {

        Product product = buildProductBasic(mpp);
        
        // attribute values
		Map<String, Object> attributeValues = new HashMap<String, Object>();
		for (String attrCode : attributes) {
			Object attrValue = mpp.get(attrCode);
			attributeValues.put(attrCode, attrValue);
		}
		product.setAttributes(attributeValues);

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

            if (type == null && dependencies.contains(Dependency.TYPES)) {
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

        // set full attributeSet if loadAttributeSet is requested
        if (mpp.get("set") != null && dependencies.contains(Dependency.ATTRIBUTE_SET)) {
            product.setAttributeSet( getAttributeSet((String) mpp.get("set")) );
        } else {
        	// if loadAttributeSet is not requested, only provide the attribute set ID
        	final ProductAttributeSet attributeSet = new ProductAttributeSet(Integer.valueOf((String) mpp.get("set")), null);
			product.setAttributeSet( attributeSet );
        }

        // categories - dont get the full tree, only basic info of categories
        if (mpp.get("categories") != null)

        {
            if (dependencies.contains(Dependency.CATEGORIES)) {
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
        if (dependencies.contains(Dependency.INVENTORY)) {
            Set<Product> products = new HashSet<Product>();
            products.add(product);
            getInventoryInfo(products);
        }

        // medias
        if (dependencies.contains(Dependency.MEDIAS))
            product.setMedias(serviceFactory.getProductMediaRemoteService().listByProduct(product));

        // product links
        if (dependencies.contains(Dependency.LINKS))
            product.setLinks(serviceFactory.getProductLinkRemoteService().list(product));

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
            Category category = serviceFactory.getCategoryRemoteService().getByIdClean(id);
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
    	ProductAttributeSet prdAttSet = cache.getIfPresent(cacheKey);
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
	                setList = (List<Map<String, Object>>) soapClient.callSingle(
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
                success = soapClient.callSingle(ResourcePath.ProductDelete, id);
            } else if (sku != null) {
                success = soapClient.callSingle(ResourcePath.ProductDelete, sku);
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
                serviceFactory.getCategoryRemoteService().deleteEmptyRecursive(category);
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
            productList = soapClient.callNoArgs(ResourcePath.ProductList);
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (productList == null)
            return products;

        for (Map<String, Object> mpp : productList)
            products.add(buildProduct(mpp, ImmutableSet.<String>of(), dependencies));

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
    public Product getBySku(String sku, Set<String> attributes) throws ServiceException {
        return getBySku(sku, attributes, false);
    }
    
    @Override
    public Product getBySkuWithCategories(String sku) throws ServiceException {
        Map<String, Object> mpp = loadBaseProduct(sku, ImmutableSet.<String>of());

        if (mpp == null) {
            return null;
        } else {
            return buildProductWithCategories(mpp);
        }
    }

    @Override
    public Product getBySku(String sku, boolean dependencies) throws ServiceException {
    	return getBySku(sku, ImmutableSet.<String>of(), dependencies);
    }
    
    public Product getBySku(String sku, Set<String> attributes, Set<Dependency> dependencies) throws ServiceException {
        Map<String, Object> mpp = loadBaseProduct(sku, attributes);

        if (mpp == null) {
            return null;
        } else {
            return buildProduct(mpp, attributes, dependencies);
        }
    }
    
    @Override
    public Product getBySku(String sku, Set<String> attributes, boolean dependencies) throws ServiceException {
        if (dependencies) {
            return getBySku(sku, attributes, ImmutableSet.of(
            		Dependency.CATEGORIES, Dependency.MEDIAS, Dependency.LINKS, Dependency.TYPES, Dependency.ATTRIBUTE_SET,
            		Dependency.INVENTORY));
        } else {
            return getBySku(sku, attributes, ImmutableSet.<Dependency>of());
        }
    }

    private Map<String, Object> loadBaseProduct(String sku, Set<String> attributes) throws ServiceException {
        Map<String, Object> mpp;
        try {
            // There's a bug in Magento not interpreting properly numeric SKUs.
            // Implementing proposed workaround on:
            // http://stackoverflow.com/questions/6748142/magento-1-5-numeric-skus-and-productidentifiertype/10915276#10915276
            // Consisting on adding a whitespace at the end that will be finally trimmed with Magento.
            if (NumberUtils.isNumber(sku)) sku = sku + " ";
            mpp = soapClient.callArgs(ResourcePath.ProductInfo, new Object[] { sku, null, attributes });
        } catch (AxisFault e) {
        	log.error("Error calling product.info with sku=" + sku + ", attributes=" + attributes, e);
            if (e.getMessage().indexOf("Product not exists") >= 0) {
                mpp = null;
            } else {
                if (debug)
                    e.printStackTrace();
                throw new ServiceException("Error calling product.info with sku=" + sku + ", attributes=" + attributes, e);
            }
        }

        return mpp;
    }
    
    @Override
	public void updatePrice(List<ProductUpdatePrice> products) throws ServiceException {
    	log.info("Updating products price for {}.", products);
    	
        try {
        	soapClient.callSingle(ResourcePath.ProductUpdatePrice, products);
        } catch (AxisFault e) {
        	log.error("Cannot call product.ProductUpdatePrice for " + products, e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Cannot call product.ProductUpdatePrice for " + products, e);
        }
    }

    @Override
	public Map<String, Map<String, String>> getRefsMap(List<String> skus) throws ServiceException {
        Map<String, Map<String, String>> mpp;
        try {
            mpp = soapClient.callSingle(ResourcePath.ProductGetRefs, skus);
        } catch (AxisFault e) {
        	log.error("Cannot call product.get_refs for " + skus, e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Cannot call product.get_refs for " + skus, e);
        }

        return mpp;
    }

    @Override
	public Map<String, ProductRefMagja> getRefs(List<String> skus) throws ServiceException {
    	Map<String, Map<String, String>> refsMap = getRefsMap(skus);
    	Map<String, ProductRefMagja> refs = Maps.transformEntries(refsMap, new Maps.EntryTransformer<String, Map<String, String>, ProductRefMagja>() {
    		@Override
    		public ProductRefMagja transformEntry(String key,
    				Map<String, String> value) {
    			return new ProductRefMagja(key, value.get("url_path"), value.get("name"),
    					value.get("image_50x50"), value.get("shop_id"));
    		}
    	});
        return refs;
    }
      
    @Override
    public Product getById(Integer id) throws ServiceException {
    	return getById(id, ImmutableSet.<String>of());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#getById(java
     * .lang .Integer)
     */
    @Override
    public Product getById(Integer id, Set<String> attributes) throws ServiceException {
        Map<String, Object> mpp;
        try {
            mpp = soapClient.callArgs(ResourcePath.ProductInfo, new Object[] { id, null, attributes });
        } catch (AxisFault e) {
        	log.error("Error calling product.info with id=" + id + ", attributes=" + attributes, e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Error calling product.info with id=" + id + ", attributes=" + attributes, e);
        }

        if (mpp == null)
            return null;
        else
            return buildProduct(mpp, attributes, true);
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
    
    public List<Product> listAllPlus(final Set<String> attributesToSelect) throws ServiceException {
        try {
        	final List<Map<String, Object>> productListPlusResult = soapClient.callArgs(ResourcePath.ProductListPlus,
        			new Object[] { null, null, attributesToSelect.toArray(new String[] {}) });
			List<Map<String, Object>> productMapList = Optional.fromNullable(productListPlusResult)
        			.or(new ArrayList<Map<String, Object>>());
            List<Product> products = Lists.transform(productMapList, new Function<Map<String, Object>, Product>() {
            	@Override
            	public Product apply(Map<String, Object> mpp) {
                	try {
						Product product = buildProduct(mpp, attributesToSelect, false);
						return product;
					} catch (ServiceException e) {
						log.error("Cannot map to Product: " + mpp, e);
						throw new RuntimeException("Cannot map to Product: " + mpp, e);
					}
            	}
            });
            return products;
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            log.error("listAllPlus error " + attributesToSelect, e);
            throw new ServiceException("listAllPlus error " + attributesToSelect, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.code.magja.service.product.ProductRemoteService#create(code.
     * google .magja.model.product.Product)
     */
    @Override
    @Deprecated
    public void save(Product product, Product existingProduct) throws ServiceException,
            NoSuchAlgorithmException {
        save(product, existingProduct, "");
    }

    @Override
    @Deprecated
    public void save(Product product, Product existingProduct, String storeView)
            throws ServiceException, NoSuchAlgorithmException {
        if (product.getId() != null && product.getId() > 0) {
            // means its a existing product
        	update(product, existingProduct, storeView);
        } else {
            // means its a new product
        	add(product, storeView);
        }
    }

    private void assignCategories(Product product, Product existingProduct) throws ServiceException {
        doAssignCategories(product, existingProduct != null ? existingProduct.getCategories() : ImmutableList.<Category>of());

        List<Category> toBeDeleted = new ArrayList<Category>();
        if (existingProduct != null) {
            for (Category category : existingProduct.getCategories()) {
                boolean found = false;
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
                serviceFactory.getCategoryRemoteService().removeProduct(category, product);
            }
        }
    }

	/**
	 * @param product
	 * @param existingProduct
	 * @throws ServiceException
	 */
	protected void doAssignCategories(Product product, List<Category> existingCategories)
			throws ServiceException {
		for (Category cat : product.getCategories()) {
			boolean found = false;
            if (existingCategories != null) {
                for (Category category : existingCategories) {
                    if (cat.getId().equals(category.getId())) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                log.debug("Adding '{}' to category #{} ({}) with position {}", new Object[] {
                    	product.getSku(), cat.getId(), cat.getName(), cat.getPosition() });
                serviceFactory.getCategoryRemoteService().assignProductWithPosition(cat, product, cat.getPosition());
            } else {
                log.debug("Updating '{}' to category #{} ({}) with position {}", new Object[] {
                	product.getSku(), cat.getId(), cat.getName(), cat.getPosition() });
                serviceFactory.getCategoryRemoteService().assignProductWithPosition(cat, product, cat.getPosition());
            }
        }
	}

    private void assignProductMedias(Product product) throws ServiceException,
            NoSuchAlgorithmException {
        // if have media, create it too
        List<String> mediaFound = new ArrayList<String>();
        List<ProductMedia> toBeDeleted = new ArrayList<ProductMedia>();
        List<ProductMedia> existingMedias = serviceFactory.getProductMediaRemoteService().listByProduct(product);

        doAssignProductMedias(product, existingMedias);

        for (ProductMedia existingMedia : existingMedias) {
            boolean found = false;
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
            serviceFactory.getProductMediaRemoteService().delete(existingMedia);
        }

    }

	/**
	 * @param product
	 * @param found
	 * @param existingMedias
	 * @throws ServiceException
	 */
	protected void doAssignProductMedias(Product product,
			List<ProductMedia> existingMedias) throws ServiceException {
		if (product.getMedias() != null) {
            if (!product.getMedias().isEmpty()) {
                for (ProductMedia media : product.getMedias()) {
                	if (media.getImage() == null) {
                		log.debug("Skipping media '{}' from product #{} ({}) because image is empty", new Object[] {
                				media.getLabel(), product.getId(), product.getSku() });
                		continue;
                	}
                	
                    boolean found = false;
                    for (ProductMedia existingMedia : existingMedias) {
                        if (existingMedia.getLabel().equals(media.getLabel())) {
                            found = true;
                            existingMedia.setTypes(media.getTypes());
                            existingMedia.setImage(media.getImage());
                            log.info("Updating media '{}' in product #{} ({}) ", new Object[] {
                            		existingMedia.getLabel(), product.getId(), product.getSku() });
                            serviceFactory.getProductMediaRemoteService().update(existingMedia);
                            break;
                        }
                    }

                    if (!found) {
                        if (media.getImage() != null && media.getImage().getData() != null)
                            log.info("Adding media '{}' to product #{} ({}) ", new Object[] {
                            		media.getLabel(), product.getId(), product.getSku() });
                        serviceFactory.getProductMediaRemoteService().create(media);
                    }
                }
            }
        }
	}

    protected void assignProductLinks(Product product) throws ServiceException {
        List<ProductLink> linksToBeDeleted = new ArrayList<ProductLink>();
        Set<ProductLink> existingLinks = serviceFactory.getProductLinkRemoteService().list(product);

        doAssignProductLinks(product, existingLinks);

        for (ProductLink existingLink : existingLinks) {
            boolean found = false;
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
            serviceFactory.getProductLinkRemoteService().remove(product, link);
        }
    }

	/**
	 * @param product
	 * @param existingLinks
	 * @throws ServiceException
	 */
	protected void doAssignProductLinks(Product product,
			Set<ProductLink> existingLinks) throws ServiceException {
		if (product.getLinks() != null) {
            if (!product.getLinks().isEmpty()) {
                for (ProductLink link : product.getLinks()) {

                    boolean found = false;
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
                        serviceFactory.getProductLinkRemoteService().assign(product, link);
                    }

                }
            }
        }
	}

    @Override
    public void setConfigurableAttributes(String productSku, Map<String, String> attributeNames)
            throws ServiceException {

        try {
            String results = soapClient.callArgs(ResourcePath.ProductConfigurableAttributes,
                    new Object[] { productSku, attributeNames });
        } catch (AxisFault e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void setAssociatedProducts(String productSku, Map<String, String> childProducts)
            throws ServiceException {

        try {
            String results = soapClient.callArgs(ResourcePath.ProductAssociateChildren,
            		new Object[] { productSku, childProducts });
            log.debug("setAssociated products {} returned {}", productSku, results);
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
            productTypes = soapClient.callNoArgs(ResourcePath.ProductTypeList);
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
    	List<Integer> productIds = ImmutableList.copyOf( Iterables.transform(products, new Function<Product, Integer>() {
    		@Override
    		public Integer apply(Product input) {
    			return input.getId();
    		}
		}) );

        List<Map<String, Object>> resultList = null;
        try {
            resultList = soapClient.callSingle(ResourcePath.ProductStockList, productIds);
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
                    if (iv.get("manage_stock") != null)
                    	product.setManageStock(iv.get("manage_stock").toString().equals("1"));
                    else
                    	product.setManageStock(null);
                    if (iv.get("use_config_manage_stock") != null)
                    	product.setUseConfigManageStock(iv.get("use_config_manage_stock").toString().equals("1"));
                    else
                    	product.setUseConfigManageStock(null);
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

        if (product.getInStock() == null)
            product.setInStock(product.getQty() > 0);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("qty", product.getQty());
        properties.put("is_in_stock", (product.getInStock() ? "1" : "0"));
        if (product.getManageStock() != null) {
        	properties.put("manage_stock", product.getManageStock() ? "1" : 0);
        }
        if (product.getUseConfigManageStock() != null) {
        	properties.put("use_config_manage_stock", product.getUseConfigManageStock() ? "1" : 0);
        }
        try {
            soapClient.callArgs(ResourcePath.ProductStockUpdate, new Object[] {
        		product.getId() != null ? product.getId() : product.getSku(),
				properties });
        } catch (AxisFault e) {
        	log.error("Cannot update inventory for product " + product.getId(), e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Cannot update inventory for product " + product.getId(), e);
        }
    }

    @Override
    public void setManageStock(Product product) throws ServiceException {
        if (product.getId() == null && product.getSku() == null)
            throw new ServiceException(
                    "The product must have the id or the sku seted for update inventory");

        Map<String, String> properties = ImmutableMap.of(
        		"use_config_manage_stock", "1",
        		"manage_stock", "1");
        try {
            soapClient.callArgs(ResourcePath.ProductStockUpdate, new Object[] {
            		product.getId() != null ? product.getId() : product.getSku(),
            				properties });
        } catch (AxisFault e) {
        	log.error("Cannot set 'Manage Stock' for product " + product.getId(), e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Cannot set 'Manage Stock' for product " + product.getId(), e);
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

        try {
            soapClient.callArgs(ResourcePath.ProductStockUpdate, new Object[] {
            		product.getId() != null ? product.getId() : product.getSku(),
    				properties });
        } catch (AxisFault e) {
        	log.error("Cannot set 'Manage Stock' for product " + product.getId(), e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Cannot set 'Manage Stock' for product " + product.getId(), e);
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
            List<Map<String, Object>> productList = (List<Map<String, Object>>) soapClient.callSingle(
                    ResourcePath.ProductList, param);
            if (productList != null) {
                for (Map<String, Object> mpp : productList)
                    products.add(buildProduct(mpp, ImmutableSet.<String>of(), false));
            }
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return products;
    }

	@Override
	public void add(Product product) throws ServiceException,
			NoSuchAlgorithmException {
		add(product, "");
	}

	@Override
	public void add(Product product, String storeView) throws ServiceException,
			NoSuchAlgorithmException {
        // if is a configurable product, call the proper handle
        if (product.getType().equals(ProductType.CONFIGURABLE))
            handleConfigurableForNewProducts(product);

        try {
            Object[] newProductArgs = product.serializeToApi();

            log.info("Creating '" + product.getSku() + "'");
            int id = Integer.parseInt((String) soapClient.callArgs(ResourcePath.ProductCreate,
                    newProductArgs));
            log.debug("{} {} returned {}", new Object[] { ResourcePath.ProductCreate,
                	product.getSku(), id });
            if (id > 0)
                product.setId(id);
            else
                throw new ServiceException("Error adding Product " + product.getSku() + ": " + product.getName() +", returned Product ID is empty");
        } catch (Exception e) {
        	log.error("Error adding Product " + product.getSku() + ": " + product.getName() + " cause: " + e.getCause(), e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Error adding Product " + product.getSku() + ": " + product.getName() + " cause: " + e.getCause(), e);
        }

        // inventory
        if (product.getQty() != null)
            updateInventory(product);

        doAssignProductMedias(product, ImmutableList.<ProductMedia>of());
        doAssignProductLinks(product, ImmutableSet.<ProductLink>of());
        doAssignCategories(product, ImmutableList.<Category>of());
	}

	@Override
	public void update(Product product, Product existingProduct) throws ServiceException,
			NoSuchAlgorithmException {
		update(product, existingProduct, "");
	}
	
	@Override
	public void update(Product product, Product existingProduct, String storeView) throws ServiceException, NoSuchAlgorithmException {
		update(product, existingProduct, storeView, ImmutableSet.of(
				Dependency.INVENTORY, Dependency.MEDIAS, Dependency.LINKS, Dependency.CATEGORIES) );
	}
	
	@Override
	public void update(Product product, Product existingProduct, String storeView,
			Set<Dependency> dependencies) throws ServiceException,
			NoSuchAlgorithmException {
        try {
            Map<String, Object> productData = new HashMap<String, Object>();
            // add custom attributes
            productData.putAll(product.getAttributes());
            // add/override static attribute values
            productData.putAll(product.getAllProperties());
            if (product.getVisibility() != null)
                productData.put("visibility", product.getVisibility().getValue());
            
            Object[] newProductArgs = new Object[] {
            	product.getId(),
            	productData,
            	!storeView.isEmpty() ? storeView : null };

            log.info("Updating '{}'", product.getSku());

            Object callResult = soapClient.callArgs(ResourcePath.ProductUpdate, newProductArgs);
            log.debug("{} {} returned {}", new Object[] { ResourcePath.ProductUpdate,
            	product.getId(), callResult });

            if (product.getType().equals(ProductType.CONFIGURABLE))
                handleConfigurableForNewProducts(product);
        } catch (Exception e) {
        	log.error("Error updating Product " + product.getId() + " cause: " + e.getCause(), e);
            if (debug)
                e.printStackTrace();
            throw new ServiceException("Error updating Product " + product.getId() + " cause: " + e.getCause(), e);
        }
        
        // inventory
        if (dependencies.contains(Dependency.INVENTORY)) {
        	if (product.getQty() != null)
        		updateInventory(product);
        }
        if (dependencies.contains(Dependency.MEDIAS)) {
        	assignProductMedias(product);
        }
        if (dependencies.contains(Dependency.LINKS)) {
        	assignProductLinks(product);
        }
        if (dependencies.contains(Dependency.CATEGORIES)) {
        	assignCategories(product, existingProduct);
        }
	}

}
