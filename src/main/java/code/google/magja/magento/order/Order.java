/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Category API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/sales_order
 */
package code.google.magja.magento.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Order extends Connection {

	/*
	 * constructor
	 */
	public Order() {
		super();
	}

	public Order(String user, String pass, String url) {
		super(user, pass, url);
	}

	/**
	 * Retrieve list of orders by filters
	 * 
	 * @return list of orders by filters
	 */
	public String getList() {
		return getList("");
	}

	/**
	 * Retrieve list of orders by filters
	 * 
	 * @param filters
	 *            filters for order list (optional)
	 * @return list of orders by filters
	 */
	public String getList(String filters) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.SalesOrderList, filters);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve order information
	 * 
	 * @param orderId
	 *            order increment id
	 * @return order information
	 */
	public String getInfo(int orderId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.SalesOrderInfo, orderId);

			return Utils.dump(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Add comment to order
	 * 
	 * @param orderId
	 *            order increment id
	 * @param status
	 *            order status
	 * @param comment
	 *            order comment
	 * @param notification
	 *            notification flag
	 * @return boolean
	 */
	public boolean addComment(int orderId, String status, String comment, int notification) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(orderId);
		newList.add(status);
		newList.add(comment);
		newList.add(notification); // todo: change notification to boolean

		try {
			client.call(ResourcePath.SalesOrderAddComment, newList);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Hold order
	 * 
	 * @param orderId
	 *            order increment id
	 * @return boolean
	 */
	public boolean hold(int orderId) {
		try {
			client.call(ResourcePath.SalesOrderHold, orderId);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Unhold order
	 * 
	 * @param orderId
	 *            order increment id
	 * @return boolean
	 */
	public boolean unhold(int orderId) {
		try {
			client.call(ResourcePath.SalesOrderUnhold, orderId);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Cancel order
	 * 
	 * @param orderId
	 *            order increment id
	 * @return boolean
	 */
	public boolean cancel(int orderId) {
		try {
			client.call(ResourcePath.SalesOrderCancel, orderId);

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
		Order o = new Order();

		// get order list
		String list = o.getList();
		System.out.println("*** DEBUG *** getList:" + list);

		// get order info
		String info = o.getInfo(100000001);
		System.out.println("*** DEBUG *** getInfo:" + info);

		// add order comment
		boolean state = o.addComment(100000002, "holded",
				"You order is holded", 1);
		System.out.println("*** DEBUG *** addComment:" + state);

		// set order status to hold
		state = o.hold(100000003);
		System.out.println("*** DEBUG *** hold:" + state);

		// set order status to unhold
		state = o.unhold(100000004);
		System.out.println("*** DEBUG *** unhold:" + state);

		// cancel order
		state = o.cancel(100000005);
		System.out.println("*** DEBUG *** cancel:" + state);

		// logout
		o.logout();
	}
}
