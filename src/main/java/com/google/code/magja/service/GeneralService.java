package com.google.code.magja.service;

import java.io.Serializable;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.soap.SoapClient;

/**
 * General 
 * @author andre
 * @author Simon Zambrovski
 */
@SuppressWarnings("rawtypes")
public interface GeneralService<T extends BaseMagentoModel> extends Serializable {

  /**
   * Retrieves the SOAP client.
   * 
   * @return SOAP client for this service.
   */
  SoapClient getSoapClient();

  /**
   * Set debugging for this service.
   * 
   * @param debug
   *          if true, debugging is enabled.
   */
  void setDebug(Boolean debug);
}
