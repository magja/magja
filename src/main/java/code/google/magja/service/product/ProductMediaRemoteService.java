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

	public abstract List<ProductMedia> listByProduct(Product product) throws ServiceException;

	public abstract ProductMedia getByProductAndFile(Product product, String file) throws ServiceException;

	public abstract void save(ProductMedia productMedia) throws ServiceException;

	public abstract void delete(ProductMedia productMedia) throws ServiceException;

}
