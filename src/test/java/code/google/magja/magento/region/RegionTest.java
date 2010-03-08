
package code.google.magja.magento.region;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class RegionTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Region#getList()}.
	 */
	@Test
	public void testGetList() {
		Region r = new Region();
		String list = r.getList("US");
		System.out.println("*** DEBUG *** getList:" + list);
	}
}
