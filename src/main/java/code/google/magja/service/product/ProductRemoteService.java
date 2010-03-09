/**
 *
 */
package code.google.magja.service.product;

import java.util.List;

import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductAttributeSet;
import code.google.magja.model.product.ProductType;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;
import code.google.magja.service.category.CategoryRemoteService;

/**
 * @author andre
 *
 */
public interface ProductRemoteService extends GeneralService<Product> {

	/**
	 * @param categoryRemoteService the categoryRemoteService to set
	 */
	public void setCategoryRemoteService(CategoryRemoteService categoryRemoteService);

	/**
	 * Get the product from Magento with the specified sku
	 * @param sku
	 * @return Product
	 * @throws ServiceException
	 */
	public abstract Product getBySku(String sku) throws ServiceException;

	/**
	 * Get the product from Magento with the specified id
	 * @param id
	 * @return Product
	 * @throws ServiceException
	 */
	public abstract Product getById(Integer id) throws ServiceException;

	/**
	 * List all products from Magento, that list haven't all attributes
	 * of each product, just the basics (for performance purposes)
	 * @return list of all products
	 * @throws ServiceException
	 */
	public abstract List<Product> listAll() throws ServiceException;

	/**
	 * Save a product to the Magento, if the id attribute is null, then will create
	 * a new product, otherwise will update the product with that id
	 * @param product
	 * @return the product inserted or updated
	 * @throws ServiceException
	 */
	public abstract Product save(Product product) throws ServiceException;

	/**
	 * @return List of all ProductTypes from magento api
	 * @throws ServiceException
	 */
	public abstract List<ProductType> listAllProductTypes() throws ServiceException;

	/**
	 * @return List of all ProductAttributeSet from magento api
	 * @throws ServiceException
	 */
	public abstract List<ProductAttributeSet> listAllProductAttributeSet() throws ServiceException;


}
