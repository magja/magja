/**
 *
 */
package code.google.magja.magento.product;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class ProductTest extends TestCase {

	/**
	 * Test method for {@link code.google.magja.magento.product.Product}.
	 */
	@Test
	public void testAllProduct() {

		// login
		Product p = new Product();

		// test procuckt list
		//String productList = p.getList();
		//System.out.println("*** DEBUG *** productList:" + productList);

		// get product info
		//String productInfo = p.getInfo("n2610");
		//System.out.println("*** DEBUG *** productInfo:" + productInfo);

		// create product
		//int productId = p.create("test_item_2");
		//System.out.println("*** DEBUG *** createProduct:" + productId);

		// logout
		p.logout();

	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#getList()}.
	 */
	@Test
	public void testGetList() {
		Product p = new Product();
		String productList = p.getList();
		System.out.println("*** DEBUG *** productList:" + productList);
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#getInfo(java.lang.String)}.
	 */
	@Test
	public void testGetInfoString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#getInfo(int)}.
	 */
	@Test
	public void testGetInfoInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#create(java.lang.String)}.
	 */
	@Test
	public void testCreateString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#create(java.lang.String, code.google.magja.magento.product.ProductType, code.google.magja.magento.product.ProductProperties, int)}.
	 */
	@Test
	public void testCreateStringProductTypeProductPropertiesInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#create(java.lang.String, code.google.magja.magento.product.ProductProperties)}.
	 */
	@Test
	public void testCreateStringProductProperties() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#create(java.lang.String, code.google.magja.magento.product.ProductProperties, int)}.
	 */
	@Test
	public void testCreateStringProductPropertiesInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#delete(java.lang.String)}.
	 */
	@Test
	public void testDeleteString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#delete(int)}.
	 */
	@Test
	public void testDeleteInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#getId(java.lang.String)}.
	 */
	@Test
	public void testGetId() {
		fail("Not yet implemented"); // TODO
	}

}
