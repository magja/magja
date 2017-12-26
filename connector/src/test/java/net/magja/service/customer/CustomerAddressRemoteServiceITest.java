/**
 *
 */
package net.magja.service.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.customer.Customer;
import net.magja.model.customer.CustomerAddress;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.service.order.OrderRemoteService;
import net.magja.soap.MagentoSoapClient;
import net.magja.util.ObjectFactory;

/**
 * Customer Service Test.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class CustomerAddressRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(OrderRemoteService.class);
  private CustomerAddressRemoteService service;
  private CustomerRemoteService customerService;
  private Integer addressId;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCustomerAddressRemoteService();
    customerService = remoteServiceFactory.getCustomerRemoteService();
  }

  @Test
  public void testSave() throws ServiceException {
    // first, create a new Customer
    Customer customer = ObjectFactory.generateCustomer2();

    customerService.save(customer);

    // then create a new Address for that customer
    CustomerAddress address = ObjectFactory.generateAddress2();
    address.setCustomer(customer);

    service.save(address);
    addressId = address.getId();
  }

  @Test
  public void testSaveAndUpdate() throws ServiceException {
    // first, create a new Customer
    Customer customer = ObjectFactory.generateCustomer2();

    customerService.save(customer);

    // then create a new Address for that customer
    CustomerAddress address = ObjectFactory.generateAddress2();
    address.setCustomer(customer);

    service.save(address);
    addressId = address.getId();

    // update that address
    address.setStreet("Av. James Rules, 293");
    address.setPostCode("02931-293");
    address.setTelephone("(19) 3255-0000");
    address.setCompany("Company Bla Bla Bla");

    service.save(address);
  }

  @Test
  public void testDelete() throws ServiceException {
    testSave();
    service.delete(addressId);
  }

  @Test
  public void testGetById() throws ServiceException {
    testSave();
    CustomerAddress address = service.getById(addressId);
    log.info("{}", address.toString());
  }

  @Test
  public void testList() throws ServiceException {
    int count_addresses = 4;
    // first, create a new Customer
    Customer customer = ObjectFactory.generateCustomer2();

    customerService.save(customer);

    for (int i = 0; i < count_addresses; i++) {
      CustomerAddress address = ObjectFactory.generateAddress2();
      address.setCustomer(customer);
      try {
        service.save(address);
      } catch (ServiceException e) {
        fail(e.getMessage());
      }
    }

    List<CustomerAddress> results = service.list(customer.getId());

    for (CustomerAddress customerAddress : results) {
      log.info("{}", customerAddress);
    }

    assertEquals(results.size(), count_addresses);

  }
}
