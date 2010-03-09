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
package code.google.magja.magento.region;

import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Region extends Connection {

	/**
	 * constructor
	 */
	public Region() {
		super();
	}

	/**
	 * List of regions in specified country
	 *
	 * @param countryId
	 *            Country code in ISO2 or ISO3
	 * @return array
	 */
	@SuppressWarnings("unchecked")
	public String getList(String countryId) {
		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) client
					.call(ResourcePath.RegionList, countryId);

			return Utils.dump(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}
