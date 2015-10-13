/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductRefMagja;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.model.product.ProductUpdatePrice;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.product.ProductRemoteService.Dependency;

public interface ProductRemoteService extends GeneralService<Product> {
	
	public static enum Dependency {
		CATEGORIES,
		MEDIAS,
		LINKS,
		TYPES,
		ATTRIBUTE_SET,
		INVENTORY
	};

    /**
     * Get the product from Magento with the specified sku
     * 
     * @param sku
     * @return Product
     * @throws ServiceException
     */
    public abstract Product getBySku(String sku) throws ServiceException;

    /**
     * Get the product from Magento with the specified sku
     * 
     * @param sku
     * @param dependencies
     * @return Product
     * @throws ServiceException
     */
    public abstract Product getBySku(String sku, boolean dependencies) throws ServiceException;

	Product getBySku(String sku, Set<String> attributes, boolean dependencies)
			throws ServiceException;

	Product getBySku(String sku, Set<String> attributes)
			throws ServiceException;
	
	public abstract Product getBySku(String sku, Set<String> attributes, Set<Dependency> dependencies) throws ServiceException;
    
    /**
     * Get the product from Magento with the specified id
     * 
     * @param id
     * @return Product
     * @throws ServiceException
     */
    public abstract Product getById(Integer id) throws ServiceException;

    Product getById(Integer id, Set<String> attributes)
			throws ServiceException;

    /**
     * List all products from Magento, just the basic attributes, with their
     * dependencies, low performance
     * 
     * @return list of all products
     * @throws ServiceException
     */
    public abstract List<Product> listAll() throws ServiceException;

    /**
     * Use this to list all product, just the basic attributes, without any
     * dependencies (Categories, Inventory, etc), more performance
     * 
     * @return List<Product>
     * @throws ServiceException
     */
    public abstract List<Product> listAllNoDep() throws ServiceException;

    /**
     * Use this to list all product, just the basic attributes, without any
     * dependencies (Categories, Inventory, etc), more performance.
     * Similar to listAllNoDep() but you can specify manually more attributes to select.
     * Additional attribute values will be saved inside Product.getAttributes() property.
     * 
     * @return List<Product>
     * @throws ServiceException
     */
    public abstract List<Product> listAllPlus(Set<String> attributesToSelect) throws ServiceException;

    /**
     * Create a product to Magento.
     * 
     * @param product
     */
    public abstract void add(Product product) throws ServiceException,
    	NoSuchAlgorithmException;

    /**
     * Create a product to Magento.
     * 
     * @param product
     */
    public abstract void add(Product product, String storeView) throws ServiceException,
    	NoSuchAlgorithmException;

    /**
     * Update a product.
     * 
     * @param product The new product data object.
     * @param existingProduct  The old product data object. Used to compare which data needs to be updated.
     */
    public abstract void update(Product product, Product existingProduct) throws ServiceException,
    	NoSuchAlgorithmException;
    /**
     * Update a product.
     * 
     * @param product The new product data object.
     * @param existingProduct  The old product data object. Used to compare which data needs to be updated.
     */
    public abstract void update(Product product, Product existingProduct, String storeView) throws ServiceException,
    	NoSuchAlgorithmException;

    /**
     * Save a product to the Magento, if the id attribute is null, then will
     * create a new product, otherwise will update the product with that id.
     * If an "existingProduct" is assigned, its categories will be copied to
     * the upserted "product".
     * 
     * @param product
     * @param existingProduct
     * @throws ServiceException
     * @deprecated Use either {@link ProductRemoteService#add(Product)} or {@link ProductRemoteService#update(Product)}.
     */
    @Deprecated
    public abstract void save(Product product, Product existingProduct) throws ServiceException,
            NoSuchAlgorithmException;

    /**
     * Save a product to the Magento, if the id attribute is null, then will
     * create a new product, otherwise will update the product with that id
     * 
     * @param product
     * @param storeView
     * @throws ServiceException
     * @deprecated Use either {@link ProductRemoteService#add(Product)} or {@link ProductRemoteService#update(Product)}.
     */
    @Deprecated
    public abstract void save(Product product, Product existingProduct, String storeView)
            throws ServiceException, NoSuchAlgorithmException;

    /**
     * Remove a product from magento with the specified id
     * 
     * @param id
     * @throws ServiceException
     */
    public abstract void delete(Integer id) throws ServiceException;

    /**
     * remove a product from magento with the specified sku
     * 
     * @param sku
     * @throws ServiceException
     */
    public abstract void delete(String sku) throws ServiceException;

    /**
     * Remove all product from magento
     * 
     * @throws ServiceException
     */
    public abstract void deleteAll() throws ServiceException;

    public abstract void deleteWithEmptyCategory(String sku) throws ServiceException;

    /**
     * @return List of all ProductTypes from magento api
     * @throws ServiceException
     */
    public abstract List<ProductType> listAllProductTypes() throws ServiceException;

    /**
     * Use to get the inventory of the products on the Set specified
     * 
     * @param products
     * @throws ServiceException
     */
    public abstract void getInventoryInfo(Set<Product> products) throws ServiceException;

    /**
     * Update a quantity of the product specified, the product must have at
     * least the id or the sku
     * 
     * @param product
     * @throws ServiceException
     */
    public abstract void updateInventory(Product product) throws ServiceException;

    public abstract List<Product> getWithoutCategory() throws ServiceException;

    void setConfigurableAttributes(String productSku, Map<String, String> attributeNames)
            throws ServiceException;

    void setAssociatedProducts(String productSku, Map<String, String> childProducts)
            throws ServiceException;

    void setManageStock(Product product) throws ServiceException;

    public void setManageStock(Product product, boolean manageStock) throws ServiceException;

    Product getBySkuWithCategories(String sku) throws ServiceException;

    /**
     * Get a list of products updated since the given date
     * 
     * @param date
     * @return
     * @throws ServiceException
     */
    public abstract List<Product> listUpdatedBetween(Date from, Date to) throws ServiceException;

	/**
	 * Retrieve the URL Path, Name, and 50x50 image for
	 * a list of products.
	 * @param array $skus Array of SKUs.
	 * @return array Associative array containing url_path, name, image_50x50, shop_id.
	 */
    public Map<String, ProductRefMagja> getRefs(List<String> skus) throws ServiceException;
    
	/**
	 * Retrieve the URL Path, Name, and 50x50 image for
	 * a list of products.
	 * @param array $skus Array of SKUs.
	 * @return array Associative array containing url_path, name, image_50x50, shop_id.
	 */
	public Map<String, Map<String, String>> getRefsMap(List<String> skus) throws ServiceException;

	void updatePrice(List<ProductUpdatePrice> products) throws ServiceException;

	void update(Product product, Product existingProduct, String storeView,
			Set<Dependency> dependencies) throws ServiceException,
			NoSuchAlgorithmException;

	}
