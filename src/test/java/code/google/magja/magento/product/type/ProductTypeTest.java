
package code.google.magja.magento.product.type;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class ProductTypeTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductType#getList()}.
	 */
	@Test
	public void testGetList() {
		ProductType pt = new ProductType();
		String list = pt.getList();
		System.out.println("*** DEBUG *** getList:" + list);

	}
}
