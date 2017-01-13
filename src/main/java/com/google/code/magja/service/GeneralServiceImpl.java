/**
 * @author andre
 *
 */
package com.google.code.magja.service;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.soap.MagentoSoapClient;

@SuppressWarnings("rawtypes")
public abstract class GeneralServiceImpl<T extends BaseMagentoModel> implements GeneralService<T> {

  private static final long serialVersionUID = -7262909756654576277L;

  protected Boolean debug = false;

  protected MagentoSoapClient soapClient;

  public GeneralServiceImpl(MagentoSoapClient soapClient) {
    super();
    this.soapClient = soapClient;
  }

  @Override
  public MagentoSoapClient getSoapClient() {
    return soapClient;
  }

  /**
   * disable or enable debug informations
   *
   * @param Boolean
   * @return
   */
  public void setDebug(Boolean b) {
    this.debug = b;
  }

}
