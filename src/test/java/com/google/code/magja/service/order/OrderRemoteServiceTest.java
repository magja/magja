package com.google.code.magja.service.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.address.BasicAddress;
import com.google.code.magja.model.order.Order;
import com.google.code.magja.model.order.OrderFilter;
import com.google.code.magja.model.order.OrderFilterItem;
import com.google.code.magja.model.order.OrderForm;
import com.google.code.magja.model.order.OrderFormItem;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.common.collect.ImmutableList;

/**
 * @author andre
 * make sure to have some orders in Magento before run this tests
 *
 */
public class OrderRemoteServiceTest {
	private transient Logger log = LoggerFactory.getLogger(OrderRemoteService.class);
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
		order.setId(100000017);

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
		
		Order order = new Order();
		order.setId(100000003);

		try {
			service.hold(order);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
		
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

	@Test
	public void createValidOrderFormShouldSucceed() throws ServiceException {
		ImmutableList<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(167L, 1.0),
				new OrderFormItem(168L, 1.0));
		long customerId = 2L;
		OrderForm orderForm = new OrderForm(customerId, items);
		Object order = service.create(orderForm);
		System.out.println("Order Id "+ order);
		assertNotNull(order);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createWithNullCustomerIdShouldFail() throws ServiceException {
		ImmutableList<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(194L, 1.0),
				new OrderFormItem(195L, 3.0));
		OrderForm orderForm = new OrderForm(null, items);
		Object order = service.create(orderForm);
		assertNull(order);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createWithNoItemsShouldFail() throws ServiceException {
		List<OrderFormItem> items = new ArrayList<OrderFormItem>();
		OrderForm orderForm = new OrderForm(3L, items);
		Object order = service.create(orderForm);
		assertNull(order);
	}
	
	@Test
	public void createNewOrderWithCustomAddress() throws ServiceException {
		/*
		 * set address
		 */
		BasicAddress customerAddress = new BasicAddress();
		customerAddress.setFirstName("Atang");
		customerAddress.setLastName("Sutisna");
		customerAddress.setStreet("Jl. Setiabudi Bandung No.6c");
		customerAddress.setCity("Bandung");
		customerAddress.setRegion("Jawa Barat");
		customerAddress.setPostCode("41123");
		customerAddress.setCountryCode("ID");
		customerAddress.setTelephone("022-09898989898");
		customerAddress.setCompany("Rachmart Family");
		
		log.info("customer address {}", customerAddress);
		ImmutableList<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(999L, 1.0),
				new OrderFormItem(998L, 1.0));
		OrderForm orderForm = new OrderForm(4L, items, customerAddress, customerAddress);
		
		log.info("orderform {}", orderForm);
		log.info("shippingAddress {}", orderForm.getShippingAddress());
		log.info("billingAddress {}", orderForm.getBillingAddress());
		Object order = service.createEx(orderForm);
		assertNotNull(order);
	}


}
