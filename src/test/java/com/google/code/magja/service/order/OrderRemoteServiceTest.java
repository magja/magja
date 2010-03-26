/**
 *
 */
package com.google.code.magja.service.order;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.order.Order;
import com.google.code.magja.model.order.OrderFilter;
import com.google.code.magja.model.order.OrderFilterItem;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 * make sure to have some orders in Magento before run this tests
 *
 */
public class OrderRemoteServiceTest {

	private OrderRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getOrderRemoteService();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#addComment(com.google.code.magja.model.order.Order, java.lang.String, java.lang.String, java.lang.Boolean)}.
	 */
	@Test
	public void testAddComment() {

		Order order = new Order();
		order.setId(100000001);

		try {
			service.addComment(order, "pending", "Hello World", false);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#cancel(com.google.code.magja.model.order.Order)}.
	 */
	@Test
	public void testCancel() {

		Order order = new Order();
		order.setId(100000001);

		try {
			service.cancel(order);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {

		try {
			Order order = service.getById(100000001);
			//System.out.println(order.toString());

			//System.out.println(order.getCustomer().toString());
			//System.out.println(order.getShippingAddress().toString());
			//System.out.println(order.getBillingAddress().toString());

			//for (OrderItem item : order.getItems())
			//	System.out.println(item.toString());

			assertTrue(order != null);

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#hold(com.google.code.magja.model.order.Order)}.
	 */
	@Test
	public void testHold() {
		/*
		Order order = new Order();
		order.setId(100000003);

		try {
			service.hold(order);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
		*/
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#unhold(com.google.code.magja.model.order.Order)}.
	 */
	@Test
	public void testUnhold() {
		/*
		Order order = new Order();
		order.setId(100000003);

		try {
			service.unhold(order);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
		*/
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#list(com.google.code.magja.model.order.OrderFilter)}.
	 */
	@Test
	public void testList() {
		try {

			List<Order> list = service.list(null);
			for (Order order : list)
				System.out.println(order.toString());

			// TODO: is not working tha find with filter
			// make sure to have a order with billing_name = Joao da Silva
			OrderFilter filter = new OrderFilter();
			filter.getItems().add(new OrderFilterItem("billing_name", "like", "%Silva%"));
			//filter.getItems().add(new OrderFilterItem("billing_lastname", "=", "Martins"));

			List<Order> filtered = service.list(filter);
			for (Order order : filtered)
				System.out.println(order.toString());


		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}



}
