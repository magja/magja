/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Shipment API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/sales_order_shipment
 */
package code.google.magja.magento.order.shipment;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Shipment extends Connection {

	/*
	 * constructor
	 */
	public Shipment() {
		super();
	}

	/**
	 * Retrieve list of shipments by filters
	 *
	 * @return list of shipments by filters
	 */
	public String getList() {
		return getList("");
	}

	/**
	 * Retrieve list of shipments by filters
	 *
	 * @param filters
	 *            filters for shipments list
	 * @return list of shipments by filters
	 */
	public String getList(String filters) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.SalesOrderShipmentList, filters);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve shipment information
	 *
	 * @param orderId
	 *            order shipment increment id
	 * @return shipment information
	 */
	public String getInfo(int shipmentId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.SalesOrderShipmentInfo, shipmentId);

			return Utils.dump(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Create new shipment for order
	 *
	 * @param orderId
	 *            order increment id
	 * @param itemsQty
	 *            items qty to ship as associative array
	 * @param comment
	 *            shipment comment
	 * @param email
	 *            send e-mail flag
	 * @param includeComment
	 *            include comment in e-mail flag
	 * @return shipment increment id
	 */
	public int create(int orderId, String[] itemsQty, String comment, int email, int includeComment) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(orderId);
		newList.add(itemsQty); // FIXME fix itemsQty
		newList.add(comment);
		newList.add(email); // FIXME change to boolean
		newList.add(includeComment); // FIXME change to boolean

		try {
			return Integer.parseInt((String) client.call(ResourcePath.SalesOrderShipmentCreate, newList));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Add new comment to shipment
	 *
	 * @param shipmentId
	 *            shipment increment id
	 * @param comment
	 *            shipment comment
	 * @param email
	 *            send e-mail flag
	 * @param includeInEmail
	 *            include comment in e-mail flag
	 * @return boolean
	 */
	public boolean addComment(int shipmentId, String comment, int email, int includeInEmail) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(shipmentId);
		newList.add(comment);
		newList.add(email);
		newList.add(includeInEmail);

		try {
			client.call(ResourcePath.SalesOrderShipmentAddComment, newList);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Add new tracking number
	 *
	 * @param shipmentId
	 *            shipment increment id
	 * @param carrier
	 *            carrier code
	 * @param title
	 *            tracking title
	 * @param trackNumber
	 *            tracking number
	 * @return int
	 */
	public int addTrack(int shipmentId, String carrier, String title, String trackNumber) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(shipmentId);
		newList.add(carrier);
		newList.add(title);
		newList.add(trackNumber);

		try {
			return Integer.parseInt((String) client.call(ResourcePath.SalesOrderShipmentAddTrack, newList));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Remove tracking number
	 *
	 * @param shipmentId
	 *            shipment increment id
	 * @param trackId
	 *            track id
	 * @return boolean
	 */
	public boolean removeTrack(int shipmentId, int trackId) {
		List<Object> newList = new LinkedList<Object>();
		newList.add(shipmentId);
		newList.add(trackId);

		try {
			client.call(ResourcePath.SalesOrderShipmentRemoveTrack, newList);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Retrieve list of allowed carriers for order
	 *
	 * @param orderId
	 *            order increment id
	 * @return list of allowed carriers for order
	 */
	public String getCarriers(int orderId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.SalesOrderShipmentGetCarriers, orderId);

			return Utils.dump(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		// login
		Shipment s = new Shipment();

		// get shipment list
		String list = s.getList();
		System.out.println("*** DEBUG *** getList:" + list);

		// get shipment info
		String info = s.getInfo(100000003);
		System.out.println("*** DEBUG *** getInfo:" + info);

		// create shipment
		int shipmentId = s.create(100000002, new String[] {}, "Shipment Created", 1, 1);
		System.out.println("*** DEBUG *** create:" + shipmentId);

		// add comment
		boolean state = s.addComment(100000002, "Express", 1, 1);
		System.out.println("*** DEBUG *** addComment:" + state);

		// add track
		int track = s.addTrack(100000003, "ups", "Overnight Express", "ID123123-1234");
		System.out.println("*** DEBUG *** addTrack:" + track);

		// remove track
		state = s.removeTrack(100000003, 50);
		System.out.println("*** DEBUG *** removeTrack:" + state);

		// get carriers
		String carriers = s.getCarriers(100000001);
		System.out.println("*** DEBUG *** getCarriers:" + carriers);

	}
}
