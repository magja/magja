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
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/customer_address
 */
package code.google.magja.magento.customer.address;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Address extends Connection {

	/*
	 * constructor
	 */
	public Address() {
		super();
	}

	public Address(String user, String pass, String url) {
		super(user, pass, url);
	}

	/**
	 * Retrieve customer addresses
	 * 
	 * @param customerId
	 *            Customer Id
	 * @return customer addresses
	 */
	public String getList(int customerId) {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.CustomerAddressList, customerId);

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve customer address data
	 * 
	 * @param customerAddressId
	 *            customer address ID
	 * @return customer address data
	 */
	public String getInfo(int customerAddressId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.CustomerAddressInfo, customerAddressId);

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
	public int create(int customerId, String firstname, String lastname, String[] street, String city, String countryId, int regionId, String postcode, String telephone) {
		CustomerAddressProperties cp = new CustomerAddressProperties();
		cp.setFirstname(firstname);
		cp.setLastname(lastname);
		cp.setStreet(street);
		cp.setCity(city);
		cp.setCountryId(countryId);
		cp.setRegionId(regionId);
		cp.setPostcode(postcode);
		cp.setTelephone(telephone);

		return create(customerId, cp);
	}

	/**
	 * Create new customer and return customer id
	 * 
	 * @param CustomerAddressProperties
	 *            CustomerProperties array
	 * @return the created customer id or -1 on error
	 */
	public int create(int customerId, CustomerAddressProperties cap) {
		// create customer address
		List<Object> newProduct = new LinkedList<Object>();
		newProduct.add(customerId);
		newProduct.add(cap.getProperties());

		try {
			return Integer.parseInt((String) client.call(ResourcePath.CustomerAddressCreate, newProduct));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/*
	 * update
	 */
	public boolean update(int addressId, CustomerAddressProperties cap) {
		// update customer address
		List<Object> newProduct = new LinkedList<Object>();
		newProduct.add(addressId);
		newProduct.add(cap.getProperties());

		try {
			client.call(ResourcePath.CustomerAddressUpdate, newProduct);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
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
			client.call(ResourcePath.CustomerAddressDelete, customerId);

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
		Address ca = new Address();

		// get customer address list
		String addressList = ca.getList(10);
		System.out.println("*** DEBUG *** getList:" + addressList);

		// get customer address info
		String addressInfo = ca.getInfo(1);
		System.out.println("*** DEBUG *** getInfo:" + addressInfo);

		// create customer address
		int addressId = ca.create(10, "FirstName", "LastName", new String[]
				{ "Street Line 1", "Street Line 2" }, "New York", "US", 43, "10021",
				"555-555");
		System.out.println("*** DEBUG *** create:" + addressId);

		// update customer address
		CustomerAddressProperties cp = new CustomerAddressProperties();
		cp.setTelephone("555-555-6");
		boolean state = ca.update(1, cp);
		System.out.println("*** DEBUG *** update:" + state);

		// delete customer address
		state = ca.delete(1);
		System.out.println("*** DEBUG *** delete:" + state);

		// logout
		ca.logout();
	}
}
