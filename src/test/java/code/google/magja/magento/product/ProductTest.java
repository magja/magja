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

	@Test
	public void testAllProduct() {

		// login
		Product p = new Product();

		// test procuckt list
		String productList = p.getList();
		System.out.println("*** DEBUG *** productList:" + productList);

		// get product info
		String productInfo = p.getInfo("n2610");
		System.out.println("*** DEBUG *** productInfo:" + productInfo);

		// create product
		int productId = p.create("test_item_2");
		System.out.println("*** DEBUG *** createProduct:" + productId);

		// logout
		p.logout();

	}

}
