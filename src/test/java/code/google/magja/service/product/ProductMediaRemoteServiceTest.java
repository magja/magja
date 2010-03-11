package code.google.magja.service.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.media.Media;
import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductMedia;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;
import code.google.magja.utils.MagjaFileUtils;

public class ProductMediaRemoteServiceTest {

	private ProductMediaRemoteService service;

	private ProductRemoteService productService;

	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getProductMediaRemoteService();
		productService = RemoteServiceFactory.getProductRemoteService();
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

	/**
	 * Test method for
	 * {@link code.google.magja.service.product.ProductMediaRemoteServiceImpl#save(code.google.magja.model.product.ProductMedia)
	 */
	@Test
	public void testSave() {

		try {
			// first create a product to add the image to
			Product product = ProductRemoteServiceTest.generateProduct();
			productService.save(product);

			// get some image from internet
			byte[] data = MagjaFileUtils.getBytesFromFileURL("http://www.rockstore.com.br/store/catalog/hp-177_foto.jpg");

			// create the media contents
			Media image = new Media();
			image.setName("guevara");
			image.setMime("image/jpeg");
			image.setData(data);

			// options for the product media
			Set<ProductMedia.Type> types = new HashSet<ProductMedia.Type>();
			types.add(ProductMedia.Type.IMAGE);

			// finally, creates the product media and persists
			ProductMedia prd_media = new ProductMedia();
			prd_media.setExclude(false);
			prd_media.setImage(image);
			prd_media.setLabel("Testing that");
			prd_media.setPosition(1);
			prd_media.setTypes(types);
			prd_media.setProduct(product);

			service.save(prd_media);

			assertTrue(prd_media.getFile() != null);

		} catch (IOException e1) {
			fail(e1.getMessage());
		} catch (ServiceException e2) {
			fail(e2.getMessage());
		}

	}

}
