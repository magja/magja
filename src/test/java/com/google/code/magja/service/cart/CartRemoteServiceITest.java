package com.google.code.magja.service.cart;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.cart.Cart;
import com.google.code.magja.model.cart.CartAddress;
import com.google.code.magja.model.cart.CartLicense;
import com.google.code.magja.model.cart.CartTotal;
import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.customer.CustomerAddressRemoteService;
import com.google.code.magja.service.customer.CustomerRemoteService;
import com.google.code.magja.service.region.RegionRemoteServiceITest;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.util.ObjectFactory;

/**
 * Cart service test.
 * 
 * @author schneider
 * @author Simon Zambrovski
 */
public class CartRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(RegionRemoteServiceITest.class);
  private CartRemoteService service;
  private CustomerRemoteService customerService;
  private CustomerAddressRemoteService customerAddressService;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCartRemoteService();
    customerService = remoteServiceFactory.getCustomerRemoteService();
    customerAddressService = remoteServiceFactory.getCustomerAddressRemoteService();
  }

  @Test
  public void testCreate() throws ServiceException {
    Cart cart = service.create(0);
    if (cart == null) {
      fail("Could not create cart [cart.create]");
    }
    log.info("Created cart {}", cart);
  }

  @Test
  public void testSetCustomer() throws ServiceException {
    Customer customer = ObjectFactory.generateCustomer();
    customerService.save(customer);

    Cart cart = service.create(0);
    log.info("Created cart {}", cart);
    cart.setCustomer(customer);
    service.setCustomer(cart);
  }

  @Test
  public void testGetLicenseAgreements() throws ServiceException {
    Cart cart = service.create(0);
    log.info("Created cart {}", cart);
    service.getLicenseAgreements(cart);
    System.out.println("Received licenses: " + cart.getLicenseAgreements().size());
    for (CartLicense item : cart.getLicenseAgreements()) {
      System.out.println("    license " + item);
    }
  }

  @Test
  public void testGetTotals() throws ServiceException {
    Cart cart = service.create(0);
    log.info("Created cart {}", cart);
    service.getTotals(cart);
    System.out.println("Received totals: " + cart.getLicenseAgreements().size());
    for (CartTotal item : cart.getTotals()) {
      System.out.println("    total " + item);
    }
  }

  @Test
  public void testGetById() throws ServiceException {
    Customer customer = ObjectFactory.generateCustomer();
    customerService.save(customer);

    Cart cart = service.create(0);
    cart.setCustomer(customer);
    service.setCustomer(cart);

    Cart other = service.getById(cart.getId(), cart.getStoreId());
    Assert.assertEquals(cart.getId(), other.getId());
    Assert.assertEquals(cart.getStoreId(), other.getStoreId());
    Assert.assertEquals(cart.getCustomer().getId(), other.getCustomer().getId());
  }

  @Test
  public void testCreateOrderFromCart() throws ServiceException {
    System.out.println("Creating customer");
    Customer customer = ObjectFactory.generateCustomer();
    customerService.save(customer);

    CustomerAddress shipAddr = ObjectFactory.generateAddress();
    CustomerAddress billAddr = ObjectFactory.generateAddress();
    shipAddr.setCustomer(customer);
    shipAddr.setDefaultBilling(false);
    shipAddr.setDefaultShipping(true);
    billAddr.setCustomer(customer);
    billAddr.setDefaultBilling(true);
    billAddr.setDefaultShipping(false);

    System.out.println("Creating default ship addr");
    customerAddressService.save(shipAddr);
    System.out.println("Creating default bill addr");
    customerAddressService.save(billAddr);

    System.out.println("Creating cart");
    Cart cart = service.create(0);
    log.info("Created cart {}", cart);

    System.out.println("Setting customer");
    cart.setCustomer(customer);
    service.setCustomer(cart);

    System.out.println("Adding product");
    Product p = new Product();
    p.setSku("NI3TP"); // FIXME
    p.setId(53); // FIXME
    service.addProduct(cart, p, 1);

    System.out.println("Setting cart addresses");
    System.err.println(shipAddr.getAllProperties());
    CartAddress cartShipAddr = CartAddress.fromAttributes(shipAddr.getAllProperties());
    CartAddress cartBillAddr = CartAddress.fromAttributes(billAddr.getAllProperties());
    cart.setBillingaddress(cartBillAddr);
    cart.setShippingAddress(cartShipAddr);
    service.setAddresses(cart);

    System.out.println("Creating order");
    service.order(cart);
  }

}
