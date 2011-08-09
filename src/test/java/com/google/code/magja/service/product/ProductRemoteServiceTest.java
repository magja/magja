/**
 *
 */
package com.google.code.magja.service.product;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.media.Media;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.model.product.ProductTypeEnum;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.utils.MagjaFileUtils;
import com.google.code.magja.utils.MagjaStringUtils;

/**
 * @author andre
 *
 */
public class ProductRemoteServiceTest {

	private ProductRemoteService service;

	private String productSku;

	private Integer productId;

	/**
	 * This method will run before the execution of any method
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new RemoteServiceFactory().getProductRemoteService();
	}

	/**
	 * Test method for save a configurable product
	 * This test its just for exemplify the use of Product Configurable
	 * Before run this test, create some Attribute Set in Magento admin and change
	 * the name and id bellow of that to work
	 */
	@Test
	public void testSaveConfigurableProduct() throws ServiceException {

		Product product = new Product();
		product.setSku(MagjaStringUtils.randomString(3, 10).toUpperCase());
		product.setName(MagjaStringUtils.randomString(3, 5) + " Configurable Prod");
		product.setShortDescription("Short description");
		product.setDescription("Some description for that configurable product");
		product.setPrice(new Double(111.23));
		product.setCost(new Double(222.22));
		product.setEnabled(true);
		product.setWeight(new Double(0.100));
		Integer[] websites = { 1 };
		product.setWebsites(websites);

		// here we set the product type as a configurable
		product.setType(ProductTypeEnum.CONFIGURABLE.getProductType());

		/*
		 * here I created a attribute set named "Teste" with id "26" on the magento admin
		 * change the name and id before run this test
		 */
		ProductAttributeSet set = new ProductAttributeSet();
		set.setId(9);
		set.setName("Vestuario");
		product.setAttributeSet(set);

		service.save(product);

		// the problem is: how to associate simple products to that configurable product?
	}


	/**
	 * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#save(com.google.code.magja.model.product.Product)}.
	 */
	@Test
	public void testSave() throws ServiceException {

		Product product = generateProduct();
		service.save(product);

		assertTrue(product.getId() != null);

		// set up the id and sku for use in other methods
		productId = product.getId();
		productSku = product.getSku();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdAndSku() throws ServiceException {

		testSave();

		Product productById = service.getById(productId);
		assertTrue(productById != null);

		Product productBySku = service.getBySku(productSku);
		assertTrue(productBySku != null);

		System.out.println(productById.toString());
	}

	/**
	 * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAll()}.
	 */
	@Test
	public void testListAll() throws ServiceException {
		List<Product> products  = service.listAll();
		for (Product product : products) System.out.println(product.toString());
	}

	/**
	 * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAllNoDep()}.
	 */
	@Test
	public void testListAllNoDep() throws ServiceException {
		List<Product> products  = service.listAllNoDep();
		for (Product product : products) System.out.println(product.toString());
	}

	/**
	 * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAllProductTypes()}.
	 */
	@Test
	public void testListAllProductTypes() throws ServiceException {
		List<ProductType> types  = service.listAllProductTypes();
		for (ProductType type : types) System.out.println(type.toString());
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#delete(java.lang.Integer)}
	 * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() throws ServiceException {
		// first create some product
		testSave();

		// then delete it by id
		service.delete(productId);

		// another product
		testSave();

		// delete it
		service.delete(productSku);
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#getInventoryInfo(java.util.Set)}.
	 */
	@Test
	public void testGetInventoryInfo() throws ServiceException {

		Set<Product> products = new HashSet<Product>(service.listAllNoDep());
		service.getInventoryInfo(products);

		for (Product product : products)
			System.out.println("Product ID: " + product.getId() + " - qty: " + product.getQty() + " - inStock: " + product.getInStock());
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#updateInventory(com.google.code.magja.model.product.Product)}.
	 */
	@Test
	public void testUpdateInventory() throws ServiceException {

		testSave();

		Product product = new Product();
		product.setId(productId);
		product.setSku(productSku);
		product.setQty(new Double(50));

		service.updateInventory(product);
	}

	/**
	 * Support method for create a simple product
	 * @return simple product
	 */
	public static Product generateProduct() {
		Product product = new Product();
		product.setSku(MagjaStringUtils.randomString(3, 10).toUpperCase());
		product.setName(MagjaStringUtils.randomString(3, 5) + " Product Test");
		product.setShortDescription("this is a short description");
		product.setDescription("this is a description");
		product.setPrice(new Double(230.23));
		product.setCost(new Double(120.22));
		product.setEnabled(true);
		product.setWeight(new Double(0.100));
		Integer[] websites = { 1 };
		product.setWebsites(websites);

		// inventory
		product.setQty(new Double(20));
		product.setInStock(true);

		// can use like that too (for the properties not mapped):
		product.set("meta_description", "one two tree");
		product.set("enable_googlecheckout", 1);

		// add category
		List<Category> categorys = new ArrayList<Category>();
		categorys.add(new Category(2));
		product.setCategories(categorys);

		// add media
		try {
			byte[] data = MagjaFileUtils.getBytesFromFileURL("http://code.google.com/images/code_sm.png");
	
			Media image = new Media();
			image.setName("google");
			image.setMime("image/jpeg");
			image.setData(data);
	
			Set<ProductMedia.Type> types = new HashSet<ProductMedia.Type>();
			types.add(ProductMedia.Type.IMAGE);
			types.add(ProductMedia.Type.SMALL_IMAGE);
	
			ProductMedia media = new ProductMedia();
			media.setExclude(false);
			media.setImage(image);
			media.setLabel("Image for Product");
			media.setPosition(1);
			media.setTypes(types);
	
			product.addMedia(media);
		} catch(Exception e) {
			System.err.println("fail to add media to product");
		}

		product.set("options_container", "container2");

		return product;
	}

}
