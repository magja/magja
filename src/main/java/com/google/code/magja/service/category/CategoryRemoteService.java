/**
 * @author andre
 *
 */
package com.google.code.magja.service.category;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.product.ProductRemoteService;

import java.util.List;

public interface CategoryRemoteService extends GeneralService<Category> {

    public abstract Category getByIdClean(Integer id) throws ServiceException;

    public abstract Category getByIdWithParent(Integer id) throws ServiceException;

    public abstract Category getByIdWithChildren(Integer id) throws ServiceException;

    public abstract Category getByIdWithParentAndChildren(Integer id) throws ServiceException;

    public abstract Category getTree(Integer id) throws ServiceException;

    public abstract void print(Category category) throws Exception;

    public abstract List<Category> search(Category category, List<String> childrenNames) throws ServiceException;

    public abstract int save(Category category) throws ServiceException;

    public abstract int save(Category category, String storeView) throws ServiceException;

    public abstract Category searchChild(Category category, Category search) throws ServiceException;

    public abstract void delete(Integer id) throws ServiceException;

    public abstract void deleteAllChildren(Integer id) throws ServiceException;

    public abstract Category getDefaultParent() throws ServiceException;

    public abstract Category linkCategory(List<Category> categories) throws ServiceException;

    public abstract Category create(Integer parentId, String categoryName) throws ServiceException;

    public abstract List<Category> create(Integer parentId, List<String> categoryNames) throws ServiceException;

    public abstract void assignProduct(Category category, Product product) throws ServiceException;

    public abstract List<Category> create(Integer parentId, Category category) throws ServiceException;

    public abstract Category getMinimalCategory(Integer parentId, String categoryName);

    public abstract Category getRequiredCategory(Integer parentId, String categoryName, String availableSortBy, String defaultSortBy, Boolean active, Boolean anchor);

    public abstract List<Product> getProducts(Category category) throws ServiceException;

    public abstract List<Product> getProducts(Category category, Integer storeID, boolean dependencies) throws ServiceException;

    public abstract List<Category> getLastCategories(Category categoryTree);

    public abstract List<Category> findEmpty(Integer id) throws ServiceException;

    public abstract Integer deleteEmpty(Integer id) throws ServiceException;

    /**
     * @param productRemoteService the productRemoteService to set
     */
    public abstract void setProductRemoteService(ProductRemoteService productRemoteService);

    public abstract Boolean isEmpty(Category category) throws ServiceException;

    public abstract void deleteEmptyRecursive(Category category) throws ServiceException;

    void assignProductWithPosition(Category category, Product product, Integer position)
            throws ServiceException;

    void removeProduct(Category category, Product product)
            throws ServiceException;
}
