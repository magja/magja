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
package Magento;

import Soap.MagentoSoapClient;
import Soap.SoapCallFactory;
import Soap.SoapClient;
import Soap.SoapConfig;
import Soap.SoapReturnParser;

public class Connection {

	protected SoapConfig config = null;
	protected SoapClient client = null;

	/*
	 * constructor
	 */
	public Connection() {
		this("soap", "test123", "http://192.168.1.88/magento/index.php/api/");
	}

	public Connection(String user, String pass, String url) {
		// init connection
		config = new SoapConfig(user, pass, url);
		client = new MagentoSoapClient(new SoapCallFactory(), new SoapReturnParser(), config);

		// login
		login();
	}

	/*
	 * logout
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
