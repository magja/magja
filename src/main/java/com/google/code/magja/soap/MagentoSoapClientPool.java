/**
 *
 */
package com.google.code.magja.soap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andre
 *
 */
public class MagentoSoapClientPool {

	private List<MagentoSoapClient> clients = new ArrayList<MagentoSoapClient>();

	private MagentoSoapClientPool() {
	}

	private static class MagentoSoapClientPoolHolder {
		private static final MagentoSoapClientPool INSTANCE = new MagentoSoapClientPool();
	}

	public static MagentoSoapClientPool getInstance() {
		return MagentoSoapClientPoolHolder.INSTANCE;
	}

	public MagentoSoapClient getClient(SoapConfig soapConfig) {

		if(!clients.isEmpty()) {
			for (MagentoSoapClient client : clients) {
				if(soapConfig.equals(client.getConfig())) return client;
			}
		}

		MagentoSoapClient client = new MagentoSoapClient(soapConfig);
		clients.add(client);

		return client;
	}

}
