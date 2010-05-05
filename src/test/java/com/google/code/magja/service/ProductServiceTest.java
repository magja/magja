/**
 *
 */
package com.google.code.magja.service;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.CatalogProductCreateEntity;
import com.google.code.magja.utils.MagjaStringUtils;

/**
 * @author andre
 *
 */
public class ProductServiceTest {

	private ProductService productService;

	@Before
	public void setUp() throws Exception {
		productService = new ProductService(new URL(Credentials.URL), Credentials.USER, Credentials.API_KEY);
	}

	@After
    public void cleanUp() throws Exception {
		productService.logout();
    }

	/**
	 * Test method for {@link com.google.code.magja.service.ProductService#save(com.google.code.magja.CatalogProductCreateEntity, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSave() {

		CatalogProductCreateEntity product = generateProduct();

		productService.save(product, "simple", "4", MagjaStringUtils.randomString(3, 10).toUpperCase());

	}

	/**
	 * Support method for create a simple product
	 * @return simple product
	 */
	public static CatalogProductCreateEntity generateProduct() {
		CatalogProductCreateEntity product = new CatalogProductCreateEntity();
		product.setName(MagjaStringUtils.randomString(3, 5) + " Product Test");
		product.setShort_description("this is a short description");
		product.setDescription("this is a description");
		product.setPrice("230.23");
		product.setWeight("0.100");
		String[] websites = { "1" };
		product.setWebsite_ids(websites);

		return product;
	}

}
