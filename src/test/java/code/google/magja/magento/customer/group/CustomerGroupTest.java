
package code.google.magja.magento.customer.group;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class CustomerGroupTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerGroup#getList()}.
	 */
	@Test
	public void testGetList() {
		CustomerGroup cg = new CustomerGroup();
		String list = cg.getList();
		System.out.println("*** DEBUG *** getList:" + list);
	}
}
