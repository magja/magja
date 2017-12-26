package net.magja.magento;

import net.magja.soap.MagentoSoapClient;
import net.magja.soap.SoapClient;

public class Connection {

  protected SoapClient client = null;

  public Connection() {
    client = MagentoSoapClient.getInstance();
  }
}
