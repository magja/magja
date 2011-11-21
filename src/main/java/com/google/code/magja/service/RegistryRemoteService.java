/**
 * 
 */
package com.google.code.magja.service;

import com.google.code.magja.soap.MagentoSoapClient;

/**
 * Responsible for creating Remote Service instances.
 * @author ceefour
 */
public interface RegistryRemoteService {

	void setSoapClient(MagentoSoapClient instance);

}
