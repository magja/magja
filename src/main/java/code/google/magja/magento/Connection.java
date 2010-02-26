/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Magento Core API
 * http://www.magentocommerce.com/support/magento_core_api
 */
package code.google.magja.magento;

import code.google.magja.soap.MagentoSoapClient;
import code.google.magja.soap.SoapCallFactory;
import code.google.magja.soap.SoapClient;
import code.google.magja.soap.SoapConfig;
import code.google.magja.soap.SoapReturnParser;
import code.google.magja.utils.PropertyLoader;

public class Connection {

	private static final String MAGENTO_API_PASSWORD = "magento-api-password";

	private static final String MAGENTO_API_URL = "magento-api-url";

	private static final String MAGENTO_API_USERNAME = "magento-api-username";

	protected SoapConfig config = null;

	protected SoapClient client = null;

	/*
	 * constructor
	 */
	public Connection() {

		java.util.Properties magentoapi = PropertyLoader
				.loadProperties("magento-api");

		config = new SoapConfig(magentoapi.getProperty(MAGENTO_API_USERNAME),
				magentoapi.getProperty(MAGENTO_API_PASSWORD), magentoapi
						.getProperty(MAGENTO_API_URL));

		client = new MagentoSoapClient(new SoapCallFactory(),
				new SoapReturnParser(), config);

		login();
	}

	public Connection(String user, String pass, String url) {

		config = new SoapConfig(user, pass, url);

		client = new MagentoSoapClient(new SoapCallFactory(),
				new SoapReturnParser(), config);

		login();
	}

	/*
	 * login
	 */
	protected void login() {
		try {
			client.login();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	/*
	 * logout
	 */
	protected void logout() {
		try {
			client.logout();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
