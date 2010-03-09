/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/**
 * Country Api
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/directory_country
 */
package code.google.magja.magento.country;

import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Country extends Connection {

	/**
	 * constructor
	 */
	public Country() {
		super();
	}

	/**
	 * Retrieve list of countries.
	 */
	@SuppressWarnings("unchecked")
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.CountryList, "");

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}
