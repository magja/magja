
package code.google.magja.magento.category.attribute;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class CategoryAttributeTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CategoryAttribute#currentStore()}.
	 */
	@Test
	public void testCurrentStore() {
		CategoryAttribute ca = new CategoryAttribute();
		int list = ca.currentStore("default");
		System.out.println("*** DEBUG *** currentStore:" + list);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CategoryAttribute#getList()}.
	 */
	@Test
	public void testGetList() {
		CategoryAttribute ca = new CategoryAttribute();
		String list = ca.getList();
		System.out.println("*** DEBUG *** getList:" + list);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CategoryAttribute#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		CategoryAttribute ca = new CategoryAttribute();
		//String list = ca.getOptions("default_sort_by", "default");
		String list = ca.getOptions("custom_design", "default");
		System.out.println("*** DEBUG *** getOptions:" + list);

	}
}
