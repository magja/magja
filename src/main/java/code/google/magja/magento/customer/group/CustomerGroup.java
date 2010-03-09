/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Customer Group API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/customer_group
 */
package code.google.magja.magento.customer.group;

import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class CustomerGroup extends Connection {

	/*
	 * constructor
	 */
	public CustomerGroup() {
		super();
	}

	/**
	 * Retrieve customer groups
	 * 
	 * @return customer groups
	 */
	public String getList() {
		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) client
					.call(ResourcePath.CustomerGroupList, "");

			return Utils.dump(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}
