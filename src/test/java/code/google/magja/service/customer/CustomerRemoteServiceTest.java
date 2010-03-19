/**
 *
 */
package code.google.magja.service.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.customer.Customer;
import code.google.magja.model.customer.Customer.Gender;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;
import code.google.magja.utils.MagjaStringUtils;

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

	public Customer generateCustomer() {
		Customer cust = new Customer();

		first_name = MagjaStringUtils.randomString(5, 10);
		cust.setFirstName(first_name);
		cust.setMiddleName(MagjaStringUtils.randomString(5, 10));
		cust.setLastName(MagjaStringUtils.randomString(5, 10));
		cust.setPassword("test");
		cust.setPrefix("Mr.");
		cust.setWebsiteId(1);
		cust.setGroupId(1);
		cust.setGender(Gender.MALE);
		String email = MagjaStringUtils.randomString(4, 5) + "@" + MagjaStringUtils.randomString(4, 5) + ".com";
		cust.setEmail(email.toLowerCase());

		return cust;
	}

	/**
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#save(code.google.magja.model.customer.Customer)}.
	 */
	@Test
	public void testSave() {

		// test creating a new customer
		Customer cust = generateCustomer();

		try {
			service.save(cust);
			customer_id = cust.getId();
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#save(code.google.magja.model.customer.Customer)}.
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
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#delete(java.lang.Integer)}.
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
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#getById(java.lang.Integer)}.
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
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#list(code.google.magja.model.customer.Customer)}.
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
	 * Test method for {@link code.google.magja.service.customer.CustomerRemoteServiceImpl#list()}.
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

}
