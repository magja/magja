/**
 *
 */
package code.google.magja.service.product;

import java.util.List;

import code.google.magja.model.product.Product;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;
import code.google.magja.service.category.CategoryRemoteService;

/**
 * @author andre
 *
 */
public interface ProductRemoteService extends GeneralService<Product> {

	/**
	 * List all products from Magento, ATENTION: that list haven't all attributes of each product
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Product> listAll() throws ServiceException;

	public abstract Product save(Product product) throws ServiceException;

	/**
	 * @param categoryRemoteService the categoryRemoteService to set
	 */
	public void setCategoryRemoteService(CategoryRemoteService categoryRemoteService);

}
