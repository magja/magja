/**
 *
 */
package net.magja.service;

import net.magja.soap.SoapClient;

/**
 * Responsible for creating Remote Service instances.
 *
 * @author ceefour
 */
public interface RegistryRemoteService {

  void setSoapClient(SoapClient instance);

}
