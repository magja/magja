/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

import java.util.List;

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
     * Get MD5 for a file
     * This is not a default Magento function! You have to extend your Magento API.
     * See: http://code.google.com/p/magja/downloads/detail?name=media.md5.api.xml.diff
     * and http://code.google.com/p/magja/downloads/detail?name=media.md5.api.xml.diff
     *
     * @param file
     * @return MD5 sum
     * @throws ServiceException
     */
    public abstract String getMd5(String file) throws ServiceException;

    /**
     * Save a new ProductMedia to the database, the object must have the product
     * id specified
     *
     * @param productMedia
     * @throws ServiceException
     */
    public abstract void create(ProductMedia productMedia)
            throws ServiceException;

    /**
     * Delete a Product Media from magento, the object must have the product id
     * or sku specified
     *
     * @param productMedia
     * @throws ServiceException
     */
    public abstract void delete(ProductMedia productMedia)
            throws ServiceException;

    Boolean update(ProductMedia productMedia) throws ServiceException;
}
