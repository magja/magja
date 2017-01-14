package com.google.code.magja.service.category;

import java.util.List;
import java.util.Map;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;
/**
 * Product category service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface CategoryRemoteService extends GeneralService<Category> {

    Category getByIdClean(Integer id) throws ServiceException;

    Category getByIdWithParent(Integer id) throws ServiceException;

    Category getByIdWithChildren(Integer id) throws ServiceException;

    Category getByIdWithParentAndChildren(Integer id) throws ServiceException;

    /**
     * Get all categories with sub-category by id
     *
     * @param id
     * @throws ServiceException
     */
    Category getTree(Integer id) throws ServiceException;

    void print(Category category) throws Exception;

    List<Category> search(Category category, List<String> childrenNames) throws ServiceException;

    int save(Category category) throws ServiceException;

    int save(Category category, String storeView) throws ServiceException;

    Category searchChild(Category category, Category search) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void deleteAllChildren(Integer id) throws ServiceException;

    Category getDefaultParent() throws ServiceException;

    Category linkCategory(List<Category> categories) throws ServiceException;

    Category create(Integer parentId, String categoryName) throws ServiceException;

    List<Category> create(Integer parentId, List<String> categoryNames) throws ServiceException;

    void assignProduct(Category category, Product product) throws ServiceException;

    List<Category> create(Integer parentId, Category category) throws ServiceException;

    Category getMinimalCategory(Integer parentId, String categoryName);

    Category getRequiredCategory(Integer parentId, String categoryName, String availableSortBy, String defaultSortBy, Boolean active, Boolean anchor);

    List<Product> getProducts(Category category) throws ServiceException;

    List<Product> getProducts(Category category, Integer storeID, boolean dependencies) throws ServiceException;

    List<Category> getLastCategories(Category categoryTree);

    List<Category> findEmpty(Integer id) throws ServiceException;

    Integer deleteEmpty(Integer id) throws ServiceException;

    Boolean isEmpty(Category category) throws ServiceException;

    void deleteEmptyRecursive(Category category) throws ServiceException;

    void assignProductWithPosition(Category category, Product product, Integer position)
            throws ServiceException;

    void removeProduct(Category category, Product product)
            throws ServiceException;

    /**
	 * Return map of categories where the key
	 * is category URL path (e.g. "accessories/bb-pouch")
	 * and the value is a map of [id, name]. 
     */
	public Map<String, Category> listPaths() throws ServiceException;

}
