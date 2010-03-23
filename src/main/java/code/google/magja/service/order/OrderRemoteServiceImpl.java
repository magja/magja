/**
 *
 */
package code.google.magja.service.order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.order.Order;
import code.google.magja.model.order.OrderFilter;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class OrderRemoteServiceImpl extends GeneralServiceImpl<Order> implements
		OrderRemoteService {

	private Order buildOrderObject(Map<String, Object> attributes)
			throws ServiceException {
		Order order = new Order();

		for (Map.Entry<String, Object> att : attributes.entrySet())
			order.set(att.getKey(), att.getValue());

		// TODO: customer, items, billing and shipping address

		return order;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#addComment(code.google
	 * .magja.model.order.Order, java.lang.String, java.lang.String,
	 * java.lang.Boolean)
	 */
	@Override
	public void addComment(Order order, String status, String comment,
			Boolean notify) throws ServiceException {

		List<Object> params = new LinkedList<Object>();
		params.add(order.getId());
		params.add(status);
		params.add(comment);
		params.add((notify ? new Integer(1) : new Integer(0)));

		try {
			soapClient.call(ResourcePath.SalesOrderAddComment, params);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#cancel(code.google
	 * .magja.model.order.Order)
	 */
	@Override
	public void cancel(Order order) throws ServiceException {

		try {
			soapClient.call(ResourcePath.SalesOrderCancel, order.getId());
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#getById(java.lang.
	 * Integer)
	 */
	@Override
	public Order getById(Integer id) throws ServiceException {

		Map<String, Object> order_remote = null;
		try {
			order_remote = (Map<String, Object>) soapClient.call(
					ResourcePath.SalesOrderInfo, id);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (order_remote == null)
			return null;
		else
			return buildOrderObject(order_remote);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#hold(code.google.magja
	 * .model.order.Order)
	 */
	@Override
	public void hold(Order order) throws ServiceException {

		try {
			soapClient.call(ResourcePath.SalesOrderHold, order.getId());
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#list(code.google.magja
	 * .model.order.OrderFilter)
	 */
	@Override
	public List<Order> list(OrderFilter filter) throws ServiceException {

		List<Order> results = new ArrayList<Order>();

		if (filter != null) {
			if (filter.getItems() != null) {
				if (filter.getItems().isEmpty())
					filter = null;
			} else
				filter = null;
		}

		List<Map<String, Object>> order_list = null;
		try {
			order_list = (List<Map<String, Object>>) soapClient.call(
					ResourcePath.SalesOrderList, (filter != null ? filter
							.serializeToApi() : ""));
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		for (Map<String, Object> map : order_list)
			results.add(buildOrderObject(map));

		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.order.OrderRemoteService#unhold(code.google
	 * .magja.model.order.Order)
	 */
	@Override
	public void unhold(Order order) throws ServiceException {

		try {
			soapClient.call(ResourcePath.SalesOrderUnhold, order.getId());
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
