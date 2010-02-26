/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Product attributes API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_product_attribute
 */
package Magento.Product.Attribute;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Magento.Connection;
import Magento.ResourcePath;
import Magento.Utils;

public class ProductAttribute extends Connection {

	/*
	 * constructor
	 */
	public ProductAttribute() {
		super();
	}
	
	public ProductAttribute(String user, String pass, String url) {
		super(user, pass, url);
	}

	/**
	 * Retrieve attribute list
	 * 
	 * @param setId
	 *            - attribute set ID
	 * @return array
	 */
	public String[][] getList(int setId) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductAttributeList, setId);

			System.out.println(productList);
			return Utils.getTable(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new String[0][0];
	}

	/**
	 * Retrieve attribute options
	 * 
	 * @param attributeId
	 *            - attribute ID
	 * @return array
	 */
	public String[][] getOptions(int attributeId) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductAttributeOptions, attributeId);

			return Utils.getTable(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new String[0][0];
	}

	/**
	 * Retrieve attribute options
	 * 
	 * @param attributeId
	 *            - attribute name
	 * @return array
	 */
	public String[][] getOptions(String attributeName) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductAttributeOptions, attributeName);

			return Utils.getTable(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new String[0][0];
	}

	/**
	 * Create product attribute <font color=red>Currently there is no method to
	 * create a attribute with Magento default soap, you must first path your
	 * Magento installation to be able to use it. See:
	 * http://www.panticz.de/Extend-Magento-Product-Attribute-Api</font>
	 * 
	 * @param attributeName
	 *            - attribute Name
	 * @param attributeProperties
	 *            - attribute properties
	 * @return int
	 */
	public int create(String attributeName, ProductAttributeProperties attributeProperties) {
		List<Object> list = new LinkedList<Object>();
		list.add(attributeName);
		list.add(attributeProperties.getProperties());

		try {
			return Integer.parseInt((String) client.call(ResourcePath.ProductAttributeCreate, list));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Create product attribute options <font color=red>Currently there is no
	 * method to create a attribute with Magento default soap, you must first
	 * path your Magento installation to be able to use it. See:
	 * http://www.panticz.de/Extend-Magento-Product-Attribute-Api</font>
	 * 
	 * @deprecated ToDo: create attribute options with create attribute function
	 * @param attributeId
	 *            - attribute ID
	 * @param attributeOptions
	 *            - attribute options
	 * @return int
	 */
	public Boolean addOptions(int attributeId, String[] attributeOptions) {
		List<Object> list = new LinkedList<Object>();
		list.add(attributeId);
		list.add(attributeOptions);

		try {
			return (Boolean) client.call(ResourcePath.ProductAttributeAddOptions, list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/**
	 * Detete product attribute <font color=red>Currently there is no method to
	 * delete a attribute with Magento default soap, you must first path your
	 * Magento installation to be able to use it. See:
	 * http://www.panticz.de/Extend-Magento-Product-Attribute-Api</font>
	 * 
	 * @param attributeName
	 *            - attribute Name
	 * @return boolean
	 */
	public boolean delete(String attributeName) {
		try {
			return (Boolean) client.call(ResourcePath.ProductAttributeDelete, attributeName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		// login
		ProductAttribute pa = new ProductAttribute();

		// get atribute list
		String[][] attributeList = pa.getList(9); // 9 = Default
		System.out.println("*** DEBUG *** getList:\n" + Utils.viewTable(attributeList));

		// get atribute options
		String[][] attributeOptionsMatrix = pa.getOptions("color");
		System.out.println("*** DEBUG *** getOptions:\n" + Utils.viewTable(attributeOptionsMatrix));

		// create new product attributes
		ProductAttributeProperties pap = new ProductAttributeProperties();
		pap.createText("Test Attribute 1");
		int newAttribute = pa.create("test_attribute_1", pap);
		System.out.println("*** DEBUG *** create:" + newAttribute);

		// create new product attributes with options
		pap.createSelect("Material");
		int materialAttributeId = pa.create("test_material", pap);
		pa.addOptions(materialAttributeId, new String[] { "Titanium", "Silver", "Gold" });

		// logout
		pa.logout();
	}
}
