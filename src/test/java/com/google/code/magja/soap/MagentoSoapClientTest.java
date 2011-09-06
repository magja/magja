/**
 * 
 */
package com.google.code.magja.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.google.code.magja.magento.ResourcePath;

/**
 * @author andrefabbro
 * 
 */
public class MagentoSoapClientTest {

	private static final String HOST_SERVER_ONE = "http://localhost.blackhawk/magento/index.php/api/soap/";
	private static final String HOST_SERVER_TWO = "http://localhost.blackhawk/rockstore/index.php/api/soap/";
	private static final String HOST_SERVER_THREE = "http://localhost.blackhawk/magento/index.php/api/soap/";

	/**
	 * In order to run this test, you have to setup three magento instances, and
	 * fill the api key and user and host as show in soap config This tests if
	 * the magentoSoapClient instances its exactly the same instances as created
	 * before
	 * 
	 * @throws Exception
	 */
	@Test @Ignore
	public void testInstances() throws Exception {

		SoapConfig soapConfig1 = new SoapConfig("soap", "test123",
				HOST_SERVER_ONE);
		SoapConfig soapConfig2 = new SoapConfig("soap", "test123",
				HOST_SERVER_TWO);
		SoapConfig soapConfig3 = new SoapConfig("soap", "test123",
				HOST_SERVER_THREE);

		MagentoSoapClient client_default_one = MagentoSoapClient.getInstance();

		MagentoSoapClient client_one = MagentoSoapClient
				.getInstance(soapConfig1);

		MagentoSoapClient client_two_one = MagentoSoapClient
				.getInstance(soapConfig2);
		MagentoSoapClient client_two_two = MagentoSoapClient
				.getInstance(soapConfig2);
		MagentoSoapClient client_two_three = MagentoSoapClient
				.getInstance(soapConfig2);

		MagentoSoapClient client_three_one = MagentoSoapClient
				.getInstance(soapConfig3);
		MagentoSoapClient client_three_two = MagentoSoapClient
				.getInstance(soapConfig3);
		MagentoSoapClient client_three_three = MagentoSoapClient
				.getInstance(soapConfig3);

		assertTrue(client_one == client_default_one);
		assertTrue(client_two_one == client_two_two);
		assertTrue(client_two_one == client_two_three);
		assertTrue(client_three_one == client_three_two);
		assertTrue(client_three_one == client_three_three);

		MagentoSoapClient client_default_two = MagentoSoapClient.getInstance();
		assertTrue(client_one == client_default_two);

		assertEquals(client_default_one.getConfig().getRemoteHost(),
				HOST_SERVER_ONE);
		assertEquals(client_default_two.getConfig().getRemoteHost(),
				HOST_SERVER_ONE);
		assertEquals(client_one.getConfig().getRemoteHost(), HOST_SERVER_ONE);
		assertEquals(client_two_one.getConfig().getRemoteHost(),
				HOST_SERVER_TWO);
		assertEquals(client_two_two.getConfig().getRemoteHost(),
				HOST_SERVER_TWO);
		assertEquals(client_two_three.getConfig().getRemoteHost(),
				HOST_SERVER_TWO);
		assertEquals(client_three_one.getConfig().getRemoteHost(),
				HOST_SERVER_THREE);
		assertEquals(client_three_two.getConfig().getRemoteHost(),
				HOST_SERVER_THREE);
		assertEquals(client_three_three.getConfig().getRemoteHost(),
				HOST_SERVER_THREE);
	}

	@Test @Ignore
	public void testTimeOutSession() throws Exception {

		System.out.println("Start - " + System.currentTimeMillis());

		MagentoSoapClient soapClient = MagentoSoapClient.getInstance();

		List<Map<String, Object>> remote_list = (List<Map<String, Object>>) soapClient.call(ResourcePath.CountryList, "");
		
		System.out.println(remote_list);
		
		System.out.println("Wait 1 min - " + System.currentTimeMillis());
		Thread.sleep(60000);
		
		List<Map<String, Object>> remote_list2 = (List<Map<String, Object>>) soapClient.call(ResourcePath.CountryList, "");
		System.out.println(remote_list2);
		
		System.out.println("Finished - " + System.currentTimeMillis());
	}

}
