/**
 *
 */
package code.google.magja.service.category;

import code.google.magja.model.category.Category;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface CategoryRemoteService extends GeneralService<Category> {

	public abstract Category getByIdClean(Integer id) throws ServiceException;

	public abstract Category getByIdWithParent(Integer id) throws ServiceException;

	public abstract Category getByIdWithChildren(Integer id) throws ServiceException;

	public abstract Category getByIdWithParentAndChildren(Integer id) throws ServiceException;

}
