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

	@Test
	public void testConnectionRepeatCallsInSameSession() {

		ConnectionMock conn = new ConnectionMock();

		SoapClient client = conn.getClient();

		try {

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


		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testConnectionRepeatCallsWithLoginAndLogout() {

		ConnectionMock conn = new ConnectionMock();

		SoapClient client = conn.getClient();

		try {

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

		} catch (AxisFault e) {
			fail(e.getMessage());
		}


	}

}
