/**
 * 
 */
package com.google.code.magja.service;

import com.google.code.magja.soap.MagentoSoapClient;

/**
 * @author ceefour
 *
 */
public class RegistryRemoteServiceImpl implements RegistryRemoteService {

	private MagentoSoapClient soapClient;

	/**
	 * @return the soapClient
	 */
	public MagentoSoapClient getSoapClient() {
		return soapClient;
	}

	/**
	 * @param soapClient the soapClient to set
	 */
	public void setSoapClient(MagentoSoapClient soapClient) {
		this.soapClient = soapClient;
	}
	
}
