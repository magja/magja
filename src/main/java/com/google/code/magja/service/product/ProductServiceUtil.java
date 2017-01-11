package com.google.code.magja.service.product;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductLink;

/**
 * @author andrefabbro
 * 
 */
public final class ProductServiceUtil {

  /**
   * test if the product is not null, and if the product id or sku is not null
   * 
   * @param product
   * @return
   */
  public final static boolean validateProduct(final Product product) {
    return product != null && (product.getId() != null || product.getSku() != null);
  }

  public final static boolean validateProductLink(final ProductLink link) {
    if (link == null) {
      return false;
    } else if (link.getLinkType() != null && (link.getId() != null || link.getSku() != null)) {
      return true;
    } else {
      return false;
    }
  }

}
