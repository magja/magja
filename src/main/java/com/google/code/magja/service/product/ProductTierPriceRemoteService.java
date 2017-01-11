/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import java.util.List;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductTierPrice;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

/**
 * Provides access to product tier prices.
 * 
 * @author Simon Zambrovski
 */
public interface ProductTierPriceRemoteService extends GeneralService<ProductTierPrice> {

  /**
   * Retrieves the tier prices of a product.
   *
   * @param product
   *          the product to get tier prices for. Must have id and SKU set.
   * @return List<ProductTierPrice> tier prices or empty.
   * @throws ServiceException
   *           on any errors.
   */
  List<ProductTierPrice> getTierPrices(Product product) throws ServiceException;

  /**
   * Updates tier prices for a product.
   * 
   * @param product
   *          product to update with id and SKU set.
   * @param tierPrices
   *          tier prices to set.
   * @return
   * @throws ServiceException
   *           on any errors.
   */
  Boolean update(Product product, List<ProductTierPrice> tierPrices) throws ServiceException;
}
