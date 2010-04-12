/**
 *
 */
package com.google.code.magja.model.product;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.product.ProductAttributeRemoteService;

/**
 * @author andre
 * 
 */
public class ProductAttributeRemoteServiceTest {

	private ProductAttributeRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new RemoteServiceFactory().getProductAttributeRemoteService();
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.product.ProductAttributeRemoteServiceImpl#getOptions(com.google.code.magja.model.product.ProductAttribute)}
	 * .
	 */
	@Test
	public void testGetOptions() throws Exception {
		ProductAttribute productAttribute = new ProductAttribute();
		productAttribute.setCode("color");

		// load options
		service.getOptions(productAttribute);

		// view options
		Map<Integer, String> options = productAttribute.getOptions();
		for (Integer key : options.keySet()) {
			System.out.println(key + " -> " + options.get(key));
		}
	}
	
}
