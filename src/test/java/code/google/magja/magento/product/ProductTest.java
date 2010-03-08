package code.google.magja.magento.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProductTest extends ProductAbstractTest {

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

		productId = p.create(SKU_PRODUCT, ProductType.SIMPLE, mpp, 4);
		assertFalse((new Integer(-1)).equals(productId));
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

	}

}
