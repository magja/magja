/**
 *
 */
package com.google.code.magja.service.category;

import java.util.List;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.product.Product;
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
	
	public abstract Category getTree(Integer id) throws ServiceException;
	
	public abstract void print(Category category) throws Exception;
	
	public abstract List<Category> search(Category category, List<String> childrenNames) throws ServiceException;

	public abstract int save(Category category) throws ServiceException;

	public abstract void delete(Integer id) throws ServiceException;

	public abstract void deleteAllChildren(Integer id) throws ServiceException;

	public abstract Category getDefaultParent() throws ServiceException;

	public abstract Category create(Integer parentId, String categoryName) throws ServiceException;

	public abstract List<Category> create(Integer parentId, List<String> categoryNames) throws ServiceException;
	
	public abstract void assignProduct(Category category, Product product) throws ServiceException;
	
	public abstract Category getMinimalCategory(Integer parentId, String categoryName);

	public abstract Category getRequiredCategory(Integer parentId, String categoryName, String availableSortBy, String defaultSortBy, Boolean active, Boolean anchor);
}
