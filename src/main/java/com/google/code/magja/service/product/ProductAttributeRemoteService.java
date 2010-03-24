/**
 *
 */
package code.google.magja.service.product;

import java.util.List;

import code.google.magja.model.product.ProductAttribute;
import code.google.magja.model.product.ProductAttributeSet;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface ProductAttributeRemoteService extends
		GeneralService<ProductAttribute> {

	/**
	 * Delete a product attribute with the name specified
	 *
	 * @param attributeName
	 * @throws ServiceException
	 */
	public abstract void delete(String attributeName) throws ServiceException;

	/**
	 * Populate the options attribute of a ProductAttribute specified, the id or
	 * the code of that ProductAttribute must not be null.
	 *
	 * @param productAttribute
	 * @throws ServiceException
	 */
	public abstract void getOptions(ProductAttribute productAttribute)
			throws ServiceException;

	/**
	 * @return List of all ProductAttributeSet from magento api
	 * @throws ServiceException
	 */
	public abstract List<ProductAttributeSet> listAllProductAttributeSet()
			throws ServiceException;

	/**
	 * List all product attributes of a attribute set from magento
	 *
	 * @param set
	 *            - the attribute set
	 * @return List<ProductAttribute>
	 * @throws ServiceException
	 */
	public abstract List<ProductAttribute> listByAttributeSet(
			ProductAttributeSet set) throws ServiceException;

	/**
	 * Save a new Product Attribute to the magento api
	 *
	 * @param productAttribute
	 * @throws ServiceException
	 */
	public abstract void save(ProductAttribute productAttribute)
			throws ServiceException;

}
