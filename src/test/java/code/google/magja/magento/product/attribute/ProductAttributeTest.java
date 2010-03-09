/**
 *
 */
package code.google.magja.magento.product.attribute;

import static org.junit.Assert.*;

import org.junit.Test;

import code.google.magja.magento.Utils;

/**
 * @author andre
 *
 */
public class ProductAttributeTest {

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#getList(int)}.
	 */
	@Test
	public void testGetList() {
		ProductAttribute pa = new ProductAttribute();

		// get atribute list
		String[][] attributeList = pa.getList(4); // 9 = Default
		System.out.println("*** DEBUG *** getList:\n" + Utils.viewTable(attributeList));

	}

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#getOptions(int)}.
	 */
	@Test
	public void testGetOptionsInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#getOptions(java.lang.String)}.
	 */
	@Test
	public void testGetOptionsString() {
		ProductAttribute pa = new ProductAttribute();

		String[][] attributeList = pa.getList(26);

		// get atribute options
		String[][] attributeOptionsMatrix = pa.getOptions("color");
		System.out.println("*** DEBUG *** getOptions:\n" + Utils.viewTable(attributeOptionsMatrix));
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#create(java.lang.String, code.google.magja.magento.product.attribute.ProductAttributeProperties)}.
	 */
	@Test
	public void testCreate() {
		ProductAttribute pa = new ProductAttribute();

		// create new product attributes
		ProductAttributeProperties pap = new ProductAttributeProperties();
		pap.createText("Test Attribute 1");
		int newAttribute = pa.create("test_attribute_1", pap);
		System.out.println("*** DEBUG *** create:" + newAttribute);
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#addOptions(int, java.lang.String[])}.
	 */
	@Test
	public void testAddOptions() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.magento.product.attribute.ProductAttribute#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

}
