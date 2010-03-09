/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/**
 * Product types API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_product_type
 */
package code.google.magja.magento.product.type;

import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class ProductType extends Connection {

	/**
	 * constructor
	 */
	public ProductType() {
		super();
	}

	/**
	 * Retrieve product types
	 */
	@SuppressWarnings("unchecked")
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.ProductTypeList, "");

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}
