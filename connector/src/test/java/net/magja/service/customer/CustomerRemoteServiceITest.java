/**
 *
 */
package net.magja.service.customer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.customer.Customer;
import net.magja.model.customer.CustomerGroup;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;
import net.magja.util.ObjectFactory;

/**
 * Test for customer service.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class CustomerRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(CustomerRemoteServiceITest.class);
  private CustomerRemoteService service;
  private Integer customerId;
  private String firstName;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCustomerRemoteService();
  }

  @Test
  public void testSave() throws ServiceException {

    // test creating a new customer
    Customer cust = ObjectFactory.generateCustomer2();
    firstName = cust.getFirstName();

    service.save(cust);
    customerId = cust.getId();
  }

  @Test
  public void testSaveAndUpdate() throws ServiceException {

    // test creating a new customer
    Customer cust = ObjectFactory.generateCustomer2();

    service.save(cust);
    customerId = cust.getId();

    // now update that customer with new data
    cust.setFirstName("John");
    cust.setMiddleName("Mark");
    cust.setLastName("Holland");
    cust.setPassword("abcd123");

    service.save(cust);
  }

  @Test
  public void testDelete() throws ServiceException {
    testSave();
    service.delete(customerId);
  }

  @Test
  public void testGetById() throws ServiceException {
    testSave();
    Customer cust = service.getById(customerId);
    System.out.println(cust.toString());
  }

  @Test
  public void testListCustomer() throws ServiceException {
    testSave();

    Customer filter = new Customer();
    filter.setFirstName(firstName);

    List<Customer> results = service.list(filter);
    assertEquals(results.get(0).getId(), customerId);

  }

  @Test
  public void testList() throws ServiceException {
    List<Customer> results = service.list();
    for (Customer customer : results) {
      log.info("{}", customer);
    }

  }

  @Test
  public void testListGroups() throws ServiceException {
    List<CustomerGroup> results = service.listGroups();
    for (CustomerGroup group : results) {
      log.info("{}", group);
    }
  }
}
