package com.google.code.magja.magento;

import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.soap.SoapClient;

public class Connection {

  protected SoapClient client = null;

  public Connection() {
    client = MagentoSoapClient.getInstance();
  }
}
