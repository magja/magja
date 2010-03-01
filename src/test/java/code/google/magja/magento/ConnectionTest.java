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
	public void testConnectionLogin() {

		ConnectionMock conn = new ConnectionMock();

		SoapClient client = conn.getClient();

		try {
			assertTrue(client.login());
		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListProductTypes() {

		ConnectionMock conn = new ConnectionMock();

		SoapClient client = conn.getClient();

		try {
			assertTrue(client.login());

			Object obj = client.call(ResourcePath.ProductTypeList, "");

			System.out.println(obj);


		} catch (AxisFault e) {
			fail(e.getMessage());
		}
	}

}
