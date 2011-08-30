/**
 *
 */
package com.google.code.magja.service.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.Customer.Gender;
import com.google.code.magja.model.customer.CustomerGroup;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.utils.MagjaStringUtils;

/**
 * @author andre
 *
 */
public class CustomerRemoteServiceTest {

	private CustomerRemoteService service;

	private Integer customer_id;

	private String first_name;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getCustomerRemoteService();
	}

	public static Customer generateCustomer() {
		Customer cust = new Customer();

		cust.setFirstName(MagjaStringUtils.randomString(5, 10));
		cust.setMiddleName(MagjaStringUtils.randomString(5, 10));
		cust.setLastName(MagjaStringUtils.randomString(5, 10));
		cust.setPassword("test12");
		cust.setPrefix("Mr.");
		cust.setWebsiteId(1);
		cust.setGroupId(1);
		cust.setGender(Gender.MALE);
		String email = MagjaStringUtils.randomString(4, 5) + "@" + MagjaStringUtils.randomString(4, 5) + ".com";
		cust.setEmail(email.toLowerCase());
		
		// this include the date of birth on the customer, and it's works, 
		// but, that attribute isn't listed when getting a customer from magento
		cust.set("dob", "1980-08-17 20:53:04");

		return cust;
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#save(com.google.code.magja.model.customer.Customer)}.
	 */
	@Test
	public void testSave() {

		// test creating a new customer
		Customer cust = generateCustomer();
		first_name = cust.getFirstName();

		try {
			service.save(cust);
			customer_id = cust.getId();
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#save(com.google.code.magja.model.customer.Customer)}.
	 */
	@Test
	public void testSaveAndUpdate() {

		// test creating a new customer
		Customer cust = generateCustomer();

		try {
			service.save(cust);
			customer_id = cust.getId();
		} catch (ServiceException e) {
			fail(e.getMessage());
		}

		// now update that customer with new data
		cust.setFirstName("John");
		cust.setMiddleName("Mark");
		cust.setLastName("Holland");
		cust.setPassword("abcd123");

		try {
			service.save(cust);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() {
		testSave();
		try {
			service.delete(customer_id);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {
		testSave();
		try {
			Customer cust = service.getById(customer_id);
			System.out.println(cust.toString());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#list(com.google.code.magja.model.customer.Customer)}.
	 */
	@Test
	public void testListCustomer() {
		testSave();

		Customer filter = new Customer();
		filter.setFirstName(first_name);

		try {
			List<Customer> results = service.list(filter);
			assertEquals(results.get(0).getId(), customer_id);

		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#list()}.
	 */
	@Test
	public void testList() {
		try {
			List<Customer> results = service.list();
			for (Customer customer : results)
				System.out.println(customer.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.customer.CustomerRemoteServiceImpl#listGroups()}.
	 */
	@Test
	public void testListGroups() {
		try {
			List<CustomerGroup> results = service.listGroups();
			for (CustomerGroup group : results)
				System.out.println(group.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

}
