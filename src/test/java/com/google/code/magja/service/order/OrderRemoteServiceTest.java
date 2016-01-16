package com.google.code.magja.service.order;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import com.google.code.magja.model.order.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.address.BasicAddress;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.common.collect.ImmutableList;

/**
 * @author andre
 * make sure to have some orders in Magento before run this tests
 *
 */
public class OrderRemoteServiceTest {
	private static Logger log = LoggerFactory.getLogger(OrderRemoteService.class);
	private OrderRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getSingleton().getOrderRemoteService();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#addComment(com.google.code.magja.model.order.Order, java.lang.String, java.lang.String, java.lang.Boolean)}.
	 */
	@Test public void addCommentToOrder() {

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
	@Test public void cancelOrder() {

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
	 * @throws ServiceException 
	 */
	@Test public void getById() throws ServiceException {
		final Order order = service.getById(100000081);
		log.info("Order {}: {}", order);
		assertNotNull(order);

		//System.out.println(order.getCustomer().toString());
		//System.out.println(order.getShippingAddress().toString());
		//System.out.println(order.getBillingAddress().toString());

		for (final OrderItem item : order.getItems())
			log.info("Item #{}: {}", item.getId(), item);
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#hold(com.google.code.magja.model.order.Order)}.
	 */
	@Test public void holdOrder() {
		
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
	@Test public void unholdOrder() {
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
	 * Test method for {@link com.google.code.magja.service.order.OrderRemoteServiceImpl#list(com.google.code.magja.model.order.Filter)}.
	 */
	@Test
	public void testList() {
		try {

			List<Order> list = service.list(null);
			for (Order order : list)
				System.out.println(order.toString());

			// TODO: is not working tha find with filter
			// make sure to have a order with billing_name = Joao da Silva
			Filter filter = new Filter();
			filter.getItems().add(new FilterItem("billing_name", "like", "%Silva%"));
			//filter.getItems().add(new FilterItem("billing_lastname", "=", "Martins"));

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
				new OrderFormItem(1566L, 1.0),
				new OrderFormItem(1556L, 1.0));
		long customerId = 3L;
		OrderForm orderForm = new OrderForm(customerId, "IDR", items);
		Object order = service.create(orderForm);
		System.out.println("Order Id "+ order);
		assertNotNull(order);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createWithNullCustomerIdShouldFail() throws ServiceException {
		ImmutableList<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(194L, 1.0),
				new OrderFormItem(195L, 3.0));
		OrderForm orderForm = new OrderForm(null, "IDR", items);
		Object order = service.create(orderForm);
		assertNull(order);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createWithNoItemsShouldFail() throws ServiceException {
		List<OrderFormItem> items = new ArrayList<OrderFormItem>();
		OrderForm orderForm = new OrderForm(3L, "IDR", items);
		Object order = service.create(orderForm);
		assertNull(order);
	}
	
	@Test public void createNewOrderWithCustomAddress() throws ServiceException {
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
		List<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(1223L, 1.0) //,
				//new OrderFormItem(1221L, 1.0)
				);
		OrderForm orderForm = new OrderForm(2L, "IDR", items, customerAddress, customerAddress);
		
		log.info("orderform {}", orderForm);
		log.info("shippingAddress {}", orderForm.getShippingAddress());
		log.info("billingAddress {}", orderForm.getBillingAddress());
		Object order = service.createEx(orderForm);
		assertNotNull(order);
	}

	@Test public void createNewOrderWithCustomAddressAndShippingAmount() throws ServiceException {
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
		List<OrderFormItem> items = ImmutableList.of(
				new OrderFormItem(1222L, 1.0) //,
				//new OrderFormItem(1221L, 1.0)
				);
		OrderForm orderForm = new OrderForm(2L, "IDR", items, customerAddress, customerAddress);
		orderForm.setPaymentMethod("banktransfer");
		orderForm.setShippingMethod("flatrate_flatrate");
		orderForm.setShippingDescription("JNE Reguler");
		orderForm.setShippingAmount(15000.0);
		
		log.info("orderform {}", orderForm);
		log.info("shippingAddress {}", orderForm.getShippingAddress());
		log.info("billingAddress {}", orderForm.getBillingAddress());
		Object order = service.createEx(orderForm);
		log.info("Created order {}", order);
		assertNotNull(order);
	}

}
