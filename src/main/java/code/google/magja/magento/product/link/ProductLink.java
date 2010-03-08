/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Product link API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_product_link
 */
package code.google.magja.magento.product.link;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class ProductLink extends Connection {

	/*
	 * constructor
	 */
	public ProductLink() {
		super();
	}

	/**
	 * Retrieve linked products
	 * 
	 * @param type
	 *            link type (cross_sell, up_sell, related, grouped)
	 * @param productId
	 *            product ID or SKU
	 * @return array
	 */
	@SuppressWarnings("unchecked")
	public String getList(String type, String productId) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(type);
		newList.add(productId);

		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.ProductLinkList, newList);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Assign multiple product links
	 * 
	 * @param type
	 *            link type (up_sell, cross_sell, related, grouped)
	 * @param productId
	 *            product ID or SKU
	 * @param linkedProducts
	 *            product ID or SKU for link
	 * @param data
	 *            link data (position, qty, etc ...)
	 * @return boolean
	 */
	public boolean assign(String type, String productId, String[] linkedProducts, ProductLinkProperties lp) {
		for(String linkedProductId : linkedProducts) {
			assign(type, productId, linkedProductId, lp);
		}

		return true;
	}
	
	/**
	 * Assign product link
	 * 
	 * @param type
	 *            link type (up_sell, cross_sell, related, grouped)
	 * @param productId
	 *            product ID or SKU
	 * @param linkedProduct
	 *            product ID or SKU for link
	 * @param data
	 *            link data (position, qty, etc ...)
	 * @return boolean
	 */
	public boolean assign(String type, String productId, String linkedProduct, ProductLinkProperties lp) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(type);
		newList.add(productId);
		newList.add(linkedProduct);
		newList.add(lp.getProperties());

		try {
			return (Boolean) client.call(ResourcePath.ProductLinkAssign, newList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Update product link
	 * 
	 * @param type
	 *            link type (up_sell, cross_sell, related, grouped)
	 * @param productId
	 *            product ID or SKU
	 * @param linkedProduct
	 *            product ID or SKU for link
	 * @return boolean
	 */
	public boolean update(String type, String productId, String linkedProduct, ProductLinkProperties lp) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(type);
		newList.add(productId);
		newList.add(linkedProduct);
		newList.add(lp.getProperties());

		try {
			return (Boolean) client.call(ResourcePath.ProductLinkUpdate, newList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Remove product link
	 * 
	 * @param type
	 *            link type (up_sell, cross_sell, related, grouped)
	 * @param productId
	 *            product ID or SKU
	 * @param linkedProduct
	 *            product ID or SKU for link
	 * @return boolean
	 */
	public boolean remove(String type, String productId, String linkedProduct) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(type);
		newList.add(productId);
		newList.add(linkedProduct);

		try {
			return (Boolean) client.call(ResourcePath.ProductLinkRemove, newList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Retrieve product link types
	 * 
	 * @return array
	 */
	@SuppressWarnings("unchecked")
	public String getTypes() {
		try {
			List<String> list = (List<String>) client.call(ResourcePath.ProductLinkTypes, "");

			return Utils.viewTable(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve product link type attributes
	 * 
	 * @param type
	 *            link type (cross_sell, up_sell, related, grouped)
	 * @return array
	 */
	@SuppressWarnings("unchecked")
	public String getAttributes(String type) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.ProductLinkAttributes, type);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}