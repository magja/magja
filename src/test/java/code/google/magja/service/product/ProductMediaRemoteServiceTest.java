package code.google.magja.service.product;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductMedia;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;

public class ProductMediaRemoteServiceTest {

	private ProductMediaRemoteService service;

	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getProductMediaRemoteService();
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetByProductAndFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link code.google.magja.service.product.ProductMediaRemoteServiceImpl#listByProduct(code.google.magja.model.product.Product)}
	 * .
	 */
	@Test
	public void testListByProduct() {
		try {

			Product product = new Product();
			product.setId(new Integer(29));

			List<ProductMedia> medias = service.listByProduct(product);

			for (ProductMedia media : medias)
				System.out.println(media.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSave() {
		fail("Not yet implemented"); // TODO
	}

}
