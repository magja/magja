/**
 *
 */
package code.google.magja.service.product;

import java.util.List;

import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductMedia;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface ProductMediaRemoteService extends GeneralService<ProductMedia> {

	/**
	 * List the product medias for the product specified, the product just must
	 * to have the id or sku attributes setted
	 *
	 * @param product
	 * @return List<ProductMedia>
	 * @throws ServiceException
	 */
	public abstract List<ProductMedia> listByProduct(Product product)
			throws ServiceException;

	/**
	 * Get the info of the product media by the product and file name specified,
	 * the product just must to have the id or sku specified
	 *
	 * @param product
	 * @param file
	 * @return
	 * @throws ServiceException
	 */
	public abstract ProductMedia getByProductAndFile(Product product,
			String file) throws ServiceException;

	/**
	 * Save a new ProductMedia to the database, the object must have the product
	 * id specified
	 *
	 * @param productMedia
	 * @throws ServiceException
	 */
	public abstract void save(ProductMedia productMedia)
			throws ServiceException;

	public abstract void delete(ProductMedia productMedia)
			throws ServiceException;

}
