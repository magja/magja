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

public class CustomerAddress extends Connection {

	/*
	 * constructor
	 */
	public CustomerAddress() {
		super();
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
}
