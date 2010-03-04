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
public class ProductTestTwo extends TestCase {

	/**
	 * Test method for {@link code.google.magja.magento.product.Product#getList()}.
	 */
	@Test
	public void testGetList() {

		System.out.println("*** DEBUG *** Perform test 1");
		Product p1 = new Product();
		String productList1 = p1.getList();
		System.out.println("*** DEBUG *** productList 1:" + productList1);
		p1.logout();

		System.out.println("*** DEBUG *** Perform test 2");
		Product p2 = new Product();
		String productList2 = p2.getList();
		System.out.println("*** DEBUG *** productList 2:" + productList2);
		p2.logout();

		// TODO: Why just here throws ConnectionPoolTimeoutException: Timeout waiting for connection ???
		System.out.println("*** DEBUG *** Perform test 3");
		Product p3 = new Product();
		String productList3 = p3.getList();
		System.out.println("*** DEBUG *** productList 3:" + productList3);
		p3.logout();

		System.out.println("*** DEBUG *** Perform test 4");
		Product p4 = new Product();
		String productList4 = p4.getList();
		System.out.println("*** DEBUG *** productList 4:" + productList4);
		p4.logout();



	}

}
