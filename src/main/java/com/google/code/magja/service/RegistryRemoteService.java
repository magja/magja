/**
 * 
 */
package com.google.code.magja.service;

import com.google.code.magja.soap.SoapClient;

/**
 * Responsible for creating Remote Service instances.
 * 
 * @author ceefour
 */
public interface RegistryRemoteService {

  void setSoapClient(SoapClient instance);

}
