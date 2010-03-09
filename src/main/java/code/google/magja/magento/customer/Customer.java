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
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/customer
 */
package code.google.magja.magento.customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Customer extends Connection {

	/*
	 * constructor
	 */
	public Customer() {
		super();
	}

	/*
	 * get customer list
	 */
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.CustomerList, "");

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/*
	 * get customer info
	 */
	public String getInfo(int categoryId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.CustomerInfo, categoryId);

			return Utils.dump(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Create new customer and return customer id
	 *
	 * @param firstname
	 *            firstname
	 * @param lastname
	 *            lastname
	 * @param email
	 *            email
	 * @param password_hash
	 *            password_hash
	 * @return the created customer id or -1 on error
	 */
	public int create(String firstname, String lastname, String email, String password) {
		CustomerProperties cp = new CustomerProperties();
		cp.setFirstname(firstname);
		cp.setLastname(lastname);
		cp.setEmail(email);
		cp.setPassword_hash(Utils.getMd5Hash(password));
		cp.setWebsite_id(1);

		return create(cp);
	}

	/**
	 * Create new customer and return customer id
	 *
	 * @param CustomerProperties
	 *            CustomerProperties array
	 * @return the created customer id or -1 on error
	 */
	public int create(CustomerProperties cp) {
		// todo: check if an email address already exists

		// create customer
		List<Object> newProduct = new LinkedList<Object>();
		newProduct.add(cp.getProperties());

		try {
			return Integer.parseInt((String) client.call(ResourcePath.CustomerCreate, newProduct));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Delete customer
	 *
	 * @param customerId
	 *            customer ID
	 * @return true on successful delete or false on error
	 */
	public boolean delete(int customerId) {
		try {
			client.call(ResourcePath.CustomerDelete, customerId); // returns
			// false on
			// successful
			// delete

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
		Customer c = new Customer();

		// get customer list
		String customerList = c.getList();
		System.out.println("*** DEBUG *** getList:" + customerList);

		// // get customer info
		String customerInfo = c.getInfo(1);
		System.out.println("*** DEBUG *** getInfo:" + customerInfo);

		// create customer
		int customerId = c.create("First", "Last", "test@example.com",
				"password");
		System.out.println("*** DEBUG *** createCustomer:" + customerId);

		// delete customer
		boolean state = c.delete(9);
		System.out.println("*** DEBUG *** deleteCustomer:" + state);

	}
}
