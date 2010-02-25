/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Product attribute sets API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_product_attribute_set
 */
package Magento.Product.Attribute.Set;

import java.util.List;
import java.util.Map;

import Magento.Connection;
import Magento.ResourcePath;
import Magento.Utils;

public class ProductAttributeSet extends Connection {

	/*
	 * constructor
	 */
	public ProductAttributeSet(String user, String pass, String url) {
		super(user, pass, url);
	}

	/**
	 * Retrieve product attribute sets
	 * 
	 * @return array
	 */
	public String[][] getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductAttributeSetList, "");

			return Utils.getTable(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new String[0][0];
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		// login
		ProductAttributeSet pas = new ProductAttributeSet("soap", "test123", "http://192.168.1.88/magento/index.php/api/");

		// test procuckt atribute list
		String[][] attributeSetList = pas.getList();
		System.out.println("*** DEBUG *** getList:\n" + Utils.viewTable(attributeSetList));

		// logout
		pas.logout();
	}
}
