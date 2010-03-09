/**
 *
 */
package code.google.magja.service.product;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductAttributeSet;
import code.google.magja.model.product.ProductType;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;
import code.google.magja.utils.MagjaStringUtils;

/**
 * @author andre
 *
 */
public class ProductRemoteServiceTest {

	private ProductRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getProductRemoteService();
	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#save(code.google.magja.model.product.Product)}.
	 */
	@Test
	public void testSave() throws ServiceException {

		Product product = new Product();

		product.setSku(MagjaStringUtils.randomString(3, 10));
		product.setName("Testing save One");
		product.setShortDescription("this is a short description");
		product.setDescription("this is a description");
		product.setPrice(new Double(230.23));
		product.setCost(new Double(120.22));
		product.setEnabled(true);
		product.setWeight(new Double(0.100));
		Integer[] websites = { 1 };
		product.setWebsites(websites);

		// can use like that too (for the properties not mapped):
		product.set("meta_description", "one two tree");
		product.set("enable_googlecheckout", 1);

		// cannot set that properties
		product.set("categories", "[3, 4]");
		product.set("options_container", "container2");

		service.save(product);

	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdAndSku() throws ServiceException {

		Product product = service.getById(4);
		System.out.println(product.toString());

		System.out.println(product.getType());

		//product = service.getBySku("PRDAUTOTEST");
		//System.out.println(product.toString());

	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#listAll()}.
	 */
	@Test
	public void testListAll() throws ServiceException {
		List<Product> products  = service.listAll();

		for (Product product : products)
			System.out.println(product.toString());
	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#listAllProductTypes()}.
	 */
	@Test
	public void testListAllProductTypes() throws ServiceException {
		List<ProductType> types  = service.listAllProductTypes();

		for (ProductType type : types)
			System.out.println(type.toString());
	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#listAllProductAttributeSet()}.
	 */
	@Test
	public void testListAllProductAttributeSet() throws ServiceException {
		List<ProductAttributeSet> sets  = service.listAllProductAttributeSet();

		for (ProductAttributeSet set : sets)
			System.out.println(set.toString());
	}



}
