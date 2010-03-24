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

	public SoapClient getClient() {
		return client;
	}

}
