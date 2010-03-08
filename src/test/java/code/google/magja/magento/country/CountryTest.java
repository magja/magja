
package code.google.magja.magento.country;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class CountryTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.Country#getList()}.
	 */
	@Test
	public void testGetList() {
		Country c = new Country();
		String list = c.getList();
		System.out.println("*** DEBUG *** getList:" + list);
	}
}
