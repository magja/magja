/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/**
 * Inventory API
 * Allows export/import catalog inventory.
 *
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/cataloginventory_stock_item
 */
package code.google.magja.magento.product.stock;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Inventory extends Connection {

	/**
	 * constructor
	 */
	public Inventory() {
		super();
	}

	/**
	 * Retrieve stock data by product ids
	 *
	 * @param productId
	 *            list of products IDs or SKUs
	 * @return stock data by product ids
	 */
	public String getList(String[] productIds) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(productIds);

		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductStockList, newList);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Update product stock data
	 *
	 * @param productId
	 *            product ID or SKU
	 * @param qty
	 *            quantity
	 * @return boolean
	 */
	public boolean update(String productId, int qty) {
		return this.update(productId, qty, qty > 0 ? 1 : 0);
	}

	/**
	 * Update product stock data
	 *
	 * @param productId
	 *            product ID or SKU
	 * @param qty
	 *            quantity
	 * @param atock
	 *            stock availability
	 * @return boolean
	 */
	public boolean update(String productId, int qty, int isInStock) {
		InventoryProperties ip = new InventoryProperties();
		ip.setQty(qty);
		ip.setIsInStock(isInStock);

		List<Object> newList = new LinkedList<Object>();
		newList.add(productId);
		newList.add(ip.getProperties());

		try {
			client.call(ResourcePath.ProductStockUpdate, newList);

			return true;
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
		Inventory i = new Inventory();

		// get shipment list
		String list = i.getList(new String[] { "22", "19", "VTADVLIHWL" });
		System.out.println("*** DEBUG *** getList:" + list);

		// get shipment list
		//boolean state = i.update("22", 50);
		//System.out.println("*** DEBUG *** update:" + state);

	}
}
