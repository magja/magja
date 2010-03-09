/**
 *
 */
package code.google.magja.magento.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class ProductTest {

	private static final String SKU_PRODUCT = "PRDAUTOTEST";

	private Integer productId;

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#create(java.lang.String)}
	 * .
	 */
	@Test
	public void testCreateProduct() {
		Product p = new Product();
		productId = p.create(SKU_PRODUCT);
		assertFalse((new Integer(-1)).equals(productId));
		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#getList()}.
	 */
	@Test
	public void testGetList() {
		Product p = new Product();
		String productList = p.getList();
		System.out.println("*** DEBUG *** productList:" + productList);
		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#getInfo(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetInfoProductBySku() {
		Product p = new Product();
		String productInfo = p.getInfo(SKU_PRODUCT);
		System.out.println("*** DEBUG *** productInfo:" + productInfo);
		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#getId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetProductIdBySkuAndInfoById() {
		Product p = new Product();
		productId = p.getId(SKU_PRODUCT);
		assertFalse((new Integer(-1)).equals(productId));

		String productInfo = p.getInfo(productId);
		System.out.println("*** DEBUG *** productInfo:" + productInfo);

		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#create(java.lang.String, code.google.magja.magento.product.ProductType, code.google.magja.magento.product.ProductProperties, int)}
	 * .
	 */
	@Test
	public void testCreateStringProductTypeProductPropertiesInt() {
		Product p = new Product();
		ProductProperties mpp = generateProductProperties();

		productId = p.create(SKU_PRODUCT, ProductType.SIMPLE, mpp, 1);
		assertFalse((new Integer(-1)).equals(productId));
		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#delete(java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteString() {
		Product p = new Product();

		// first create the product
		productId = p.create(SKU_PRODUCT, ProductType.SIMPLE,
				generateProductProperties(), 1);

		// then delete created
		assertTrue(p.delete(SKU_PRODUCT));
		p.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Product#delete(int)}.
	 */
	@Test
	public void testDeleteInt() {
		Product p = new Product();

		// first create the product
		productId = p.create(SKU_PRODUCT, ProductType.SIMPLE,
				generateProductProperties(), 1);

		// then delete created
		assertTrue(p.delete(productId));
		p.logout();
	}

	private ProductProperties generateProductProperties() {
		ProductProperties mpp = new ProductProperties();
		mpp.setName("Remote Testing Inserting Product");
		mpp.setDescription("This is a Description of remote " +
				"testing to insert a new Product");
		mpp.setShort_description("This is a Short Description of " +
				"remote testing to insert a new Product");

		mpp.setPrice(new Double(120.34));
		mpp.set("cost", new Double(64.68));

		mpp.setStatus(1);
		mpp.setWeight(new Double(0.300));
		int[] websites = { 1 };
		mpp.setWebsites(websites);
		mpp.setTax_class_id(1);

		return mpp;
	}

}
