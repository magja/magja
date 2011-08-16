/**
 *
 */
package com.google.code.magja.service.product;

import java.util.List;
import java.util.Set;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.category.CategoryRemoteService;

/**
 * @author andre
 *
 */
public interface ProductRemoteService extends GeneralService<Product> {

	/**
	 * @param categoryRemoteService
	 *            the categoryRemoteService to set
	 */
	public abstract void setCategoryRemoteService(
			CategoryRemoteService categoryRemoteService);

	/**
	 * @param productMediaRemoteService
	 *            the productMediaRemoteService to set
	 */
	public abstract void setProductMediaRemoteService(
			ProductMediaRemoteService productMediaRemoteService);

	/**
	 * @param productLinkRemoteService
	 *            the productLinkRemoteService to set
	 */
	public abstract void setProductLinkRemoteService(
			ProductLinkRemoteService productLinkRemoteService);

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

	/**
	 * Get the product from Magento with the specified id
	 *
	 * @param id
	 * @return Product
	 * @throws ServiceException
	 */
	public abstract Product getById(Integer id) throws ServiceException;

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
	 * Save a product to the Magento, if the id attribute is null, then will
	 * create a new product, otherwise will update the product with that id
	 *
	 * @param product
	 * @throws ServiceException
	 */
	public abstract void save(Product product) throws ServiceException;
	
	/**
	 * Save a product to the Magento, if the id attribute is null, then will
	 * create a new product, otherwise will update the product with that id
	 *
	 * @param product
	 * @param storeView
	 * @throws ServiceException
	 */
	public abstract void save(Product product, String storeView) throws ServiceException;

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
	public abstract List<ProductType> listAllProductTypes()
			throws ServiceException;

	/**
	 * Use to get the inventory of the products on the Set specified
	 *
	 * @param products
	 * @throws ServiceException
	 */
	public abstract void getInventoryInfo(Set<Product> products)
			throws ServiceException;

	/**
	 * Update a quantity of the product specified, the product must have at
	 * least the id or the sku
	 *
	 * @param product
	 * @throws ServiceException
	 */
	public abstract void updateInventory(Product product)
			throws ServiceException;
	
	public abstract List<Product> getWithoutCategory() throws ServiceException;
}
