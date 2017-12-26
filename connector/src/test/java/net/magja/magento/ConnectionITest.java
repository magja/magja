/**
 *
 */
package net.magja.magento;

import java.util.List;
import java.util.Map;

import net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;
import org.junit.Test;

import net.magja.soap.SoapClient;

/**
 * @author andre
 *
 */
public class ConnectionITest {

  @SuppressWarnings("unchecked")
  @Test
  public void testProductType() throws Exception {
    ConnectionMock conn = new ConnectionMock();
    SoapClient client = conn.getClient();
    List<Map<String, Object>> productList = (List<Map<String, Object>>) client.callSingle(ResourcePath.ProductTypeList, "");

    for (Map<String, Object> map : productList) {
      System.out.println(map.toString());
    }
  }

  @Test
  public void testConnectionRepeatCallsInSameSession() throws AxisFault {
    ConnectionMock conn = new ConnectionMock();
    SoapClient client = conn.getClient();

    System.out.println("*** DEBUG *** Perform test 1");
    Object res1 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 1: " + res1.toString());

    System.out.println("*** DEBUG *** Perform test 2");
    Object res2 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 2: " + res2.toString());

    System.out.println("*** DEBUG *** Perform test 3");
    Object res3 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 3: " + res3.toString());

    System.out.println("*** DEBUG *** Perform test 4");
    Object res4 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 4: " + res4.toString());

  }

  @Test
  public void testConnectionRepeatCallsWithLoginAndLogout() throws AxisFault {

    ConnectionMock conn = new ConnectionMock();
    SoapClient client = conn.getClient();

    System.out.println("*** DEBUG *** Perform test 1");
    Object res1 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 1: " + res1.toString());

    System.out.println("*** DEBUG *** Perform test 2");
    Object res2 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 2: " + res2.toString());

    System.out.println("*** DEBUG *** Perform test 3");
    Object res3 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 3: " + res3.toString());

    System.out.println("*** DEBUG *** Perform test 4");
    Object res4 = client.callSingle(ResourcePath.ProductList, "");
    System.out.println("*** DEBUG *** result 4: " + res4.toString());

  }

}
