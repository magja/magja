/**
 *
 */
package com.google.code.magja.service.category;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface CategoryRemoteService extends GeneralService<Category> {

	public abstract Category getByIdClean(Integer id) throws ServiceException;

	public abstract Category getByIdWithParent(Integer id) throws ServiceException;

	public abstract Category getByIdWithChildren(Integer id) throws ServiceException;

	public abstract Category getByIdWithParentAndChildren(Integer id) throws ServiceException;

	public abstract int save(Category category) throws ServiceException;

	public abstract void delete(Integer id) throws ServiceException;

	public abstract void deleteAllChildren(Integer id) throws ServiceException;

	public abstract Category getDefaultParent() throws ServiceException;

	public abstract Category create(Integer parentId, String categoryName) throws ServiceException;

	public abstract Category create(Integer parentId, String[] categoryNames) throws ServiceException;
}
