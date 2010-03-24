/**
 *
 */
package com.google.code.magja.model.product;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class ProductTest {



	/**
	 * Test the reflection of Product
	 */
	@Test
	public void testReflectionOnProduct() {

		Product product = new Product();

		product.setId(1000);
		product.setSku("Testing Sku One");

		System.out.println("ID: " + product.get("product_id"));
		System.out.println("SKU: " + product.get("sku"));

		product.set("product_id", new Integer(2000));
		product.set("sku", "Updated only by set()");
		System.out.println("invoking get()");
		System.out.println("ID: " + product.get("product_id"));
		System.out.println("SKU: " + product.get("sku_magento"));
		System.out.println("invoking getId() and getSku()");
		System.out.println("ID: " + product.getId());
		System.out.println("SKU: " + product.getSku());
	}

}
