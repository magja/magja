/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Category attribute API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_category_attribute
 */
package code.google.magja.magento.category.attribute;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class CategoryAttribute extends Connection {

	/**
	 * constructor
	 */
	public CategoryAttribute() {
		super();
	}

	/**
	 * Retrieve category attributes
	 */
	@SuppressWarnings("unchecked")
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.CategoryAttributeList, "");

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Set/Get current store view
	 * 
	 * @param storeView
	 *            Store view ID or code
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public Integer currentStore(String storeView) {
		try {
			return Integer.parseInt((String) client.call(
					ResourcePath.CategoryAttributeCurrentStore, storeView));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Retrieve attribute options
	 * 
	 * @param attributeId
	 *            attribute id or code
	 * @param storeView
	 *            store view id or code
	 * @return attribute options
	 */
	@SuppressWarnings("unchecked")
	public String getOptions(String attributeId, String storeView) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(attributeId);
		newList.add(storeView);

		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.CategoryAttributeOptions, newList);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}