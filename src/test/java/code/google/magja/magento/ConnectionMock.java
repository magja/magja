/**
 *
 */
package code.google.magja.magento;

import code.google.magja.soap.SoapClient;

/**
 * @author andre
 *
 */
public class ConnectionMock extends Connection {

	public ConnectionMock() {
		super();
	}

	public ConnectionMock(String user, String pass, String url) {
		super(user, pass, url);
	}

	public SoapClient getClient() {
		return client;
	}

}
