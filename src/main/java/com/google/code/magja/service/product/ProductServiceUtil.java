package com.google.code.magja.service.product;

import com.google.code.magja.model.product.Product;

/**
 * @author andrefabbro
 * 
 */
public class ProductServiceUtil {

	/**
	 * test if the product is not null, and if the product id or sku is not null
	 * 
	 * @param product
	 * @return
	 */
	protected static Boolean validateProduct(Product product) {
		return product != null
				&& (product.getId() != null || product.getSku() != null);
	}

}
