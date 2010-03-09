/**
 * 
 */
package code.google.magja.magento.product.attribute.set;

import org.junit.Test;

import code.google.magja.magento.Utils;

/**
 * @author andre
 *
 */
public class ProductAttributeSetTest {

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.set.ProductAttributeSet#getList()}.
	 */
	@Test
	public void testGetList() {
		
		ProductAttributeSet pas = new ProductAttributeSet();

		// test procuckt atribute list
		String[][] attributeSetList = pas.getList();
		System.out.println("*** DEBUG *** getList:\n" + Utils.viewTable(attributeSetList));

		// logout
		pas.logout();
	}

}
