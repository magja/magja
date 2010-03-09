/**
 *
 */
package code.google.magja.magento;

import junit.framework.TestCase;

import org.apache.axis2.AxisFault;
import org.junit.Test;

import code.google.magja.soap.SoapClient;

/**
 * @author andre
 *
 */
public class ConnectionTest extends TestCase {

	private static final String MAGENTO_API_PASSWORD = "test123";

	private static final String MAGENTO_API_URL = "http://localhost.blackhawk/magento/index.php/api/soap/";

	private static final String MAGENTO_API_USERNAME = "soap";


	@Test
	public void testConnectionLogin() {

		ConnectionMock conn = new ConnectionMock(MAGENTO_API_USERNAME, MAGENTO_API_PASSWORD, MAGENTO_API_URL);

		SoapClient client = conn.getClient();

		try {
			assertTrue(client.login());

			assertTrue(client.logout());

		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testConnectionRepeatCallsInSameSession() {

		ConnectionMock conn = new ConnectionMock(MAGENTO_API_USERNAME, MAGENTO_API_PASSWORD, MAGENTO_API_URL);

		SoapClient client = conn.getClient();

		try {
			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 1");
			Object res1 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 1: " + res1.toString());

			System.out.println("*** DEBUG *** Perform test 2");
			Object res2 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 2: " + res2.toString());

			System.out.println("*** DEBUG *** Perform test 3");
			Object res3 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 3: " + res3.toString());

			System.out.println("*** DEBUG *** Perform test 4");
			Object res4 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 4: " + res4.toString());

			assertTrue(client.logout());

		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testConnectionRepeatCallsWithLoginAndLogout() {

		ConnectionMock conn = new ConnectionMock(MAGENTO_API_USERNAME, MAGENTO_API_PASSWORD, MAGENTO_API_URL);

		SoapClient client = conn.getClient();

		try {
			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 1");
			Object res1 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 1: " + res1.toString());

			assertTrue(client.logout());

			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 2");
			Object res2 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 2: " + res2.toString());

			assertTrue(client.logout());

			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 3");
			Object res3 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 3: " + res3.toString());

			assertTrue(client.logout());

			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 4");
			Object res4 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 4: " + res4.toString());

			assertTrue(client.logout());

		} catch (AxisFault e) {
			fail(e.getMessage());
		}

		try {
			assertTrue(client.login());

			System.out.println("*** DEBUG *** Perform test 1");
			Object res1 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 1: " + res1.toString());

			System.out.println("*** DEBUG *** Perform test 2");
			Object res2 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 2: " + res2.toString());

			System.out.println("*** DEBUG *** Perform test 3");
			Object res3 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 3: " + res3.toString());

			System.out.println("*** DEBUG *** Perform test 4");
			Object res4 = client.call(ResourcePath.ProductList, "");
			System.out.println("*** DEBUG *** result 4: " + res4.toString());

			assertTrue(client.logout());

		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

}
