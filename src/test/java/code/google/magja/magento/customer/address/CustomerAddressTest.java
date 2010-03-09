
package code.google.magja.magento.customer.address;

import org.junit.Test;

/**
 * @author andre
 * 
 */
public class CustomerAddressTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerAddress#getList()}.
	 */
	@Test
	public void testGetList() {
		CustomerAddress ca = new CustomerAddress();
		String addressList = ca.getList(10);
		System.out.println("*** DEBUG *** getList:" + addressList);
		ca.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerAddress#getInfo()}.
	 */
	@Test
	public void testGetInfo() {
		CustomerAddress ca = new CustomerAddress();
		String addressInfo = ca.getInfo(1);
		System.out.println("*** DEBUG *** getInfo:" + addressInfo);
		ca.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerAddress#create()}.
	 */
	@Test
	public void testCreate() {
		CustomerAddress ca = new CustomerAddress();
		int addressId = ca.create(10, "FirstName", "LastName", new String[]
				{ "Street Line 1", "Street Line 2" }, "New York", "US", 43, "10021",
				"555-555");
		System.out.println("*** DEBUG *** create:" + addressId);
		ca.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerAddress#update()}.
	 */
	@Test
	public void testUpdate() {
		CustomerAddress ca = new CustomerAddress();
		CustomerAddressProperties cp = new CustomerAddressProperties();
		cp.setTelephone("555-555-6");
		boolean state = ca.update(1, cp);
		System.out.println("*** DEBUG *** update:" + state);
		ca.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.CustomerAddress#delete()}.
	 */
	@Test
	public void testDelete() {
		CustomerAddress ca = new CustomerAddress();
		boolean state = ca.delete(1);
		System.out.println("*** DEBUG *** delete:" + state);
		ca.logout();
	}
}