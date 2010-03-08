/**
 *
 */
package code.google.magja.service.product;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.product.Product;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;

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
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#listAll()}.
	 */
	@Test
	public void testListAll() throws ServiceException {
		List<Product> products  = service.listAll();

		for (Product product : products)
			System.out.println(product.toString());
	}

	/**
	 * Test method for {@link code.google.magja.service.product.ProductRemoteServiceImpl#save(code.google.magja.model.product.Product)}.
	 */
	@Test
	public void testSave() {
		fail("Not yet implemented"); // TODO
	}

}
