/**
 *
 */
package com.google.code.magja.magento;

import com.google.code.magja.soap.SoapClient;

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
