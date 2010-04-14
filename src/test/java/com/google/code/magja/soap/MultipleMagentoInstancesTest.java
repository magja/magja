/**
 *
 */
package com.google.code.magja.soap;

import org.junit.Test;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.product.ProductRemoteService;
import com.google.code.magja.service.product.ProductRemoteServiceTest;

/**
 * @author Andre Fabbro This tests the multiple calls to multiple instances of
 *         magento
 */
public class MultipleMagentoInstancesTest {

	@Test
	public void testSaveProducts() throws Exception {

		// create the configs for each instance

		SoapConfig config_instance_one = new SoapConfig("soap_instance1",
				"test123",
				"http://localhost.blackhawk/magento_instance1/index.php/api/soap/");

		SoapConfig config_instance_two = new SoapConfig("soap_instance2",
				"test123",
				"http://localhost.blackhawk/magento_instance2/index.php/api/soap/");

		// get the services for each instance
		//ProductRemoteService prd_service_default = new RemoteServiceFactory().getProductRemoteService();
		ProductRemoteService prd_service_one = new RemoteServiceFactory(config_instance_one).getProductRemoteService();
		ProductRemoteService prd_service_two = new RemoteServiceFactory(config_instance_two).getProductRemoteService();

		// generate some product
		Product product;

		// save the product to each instance

		//product = ProductRemoteServiceTest.generateProduct();
		//prd_service_default.save(product);

		product = ProductRemoteServiceTest.generateProduct();
		prd_service_one.save(product);

		product = ProductRemoteServiceTest.generateProduct();
		prd_service_two.save(product);

	}

	@Test
	public void testSave1() throws Exception {
		testSaveProducts();
	}

	@Test
	public void testSave2() throws Exception {
		testSaveProducts();
	}

	@Test
	public void testSave3() throws Exception {
		testSaveProducts();
	}

	@Test
	public void testSave4() throws Exception {
		testSaveProducts();
	}

	@Test
	public void testSave5() throws Exception {
		testSaveProducts();
	}

}
