/**
 *
 */
package com.google.code.magja.service.cart;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.cart.Cart;
import com.google.code.magja.model.cart.CartAddress;
import com.google.code.magja.model.cart.CartLicense;
import com.google.code.magja.model.cart.CartTotal;
import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.Customer.Gender;
import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.customer.CustomerAddressRemoteService;
import com.google.code.magja.service.customer.CustomerRemoteService;
import com.google.code.magja.utils.MagjaStringUtils;

/**
 * @author schneider
 *
 */
public class CartRemoteServiceTest {

	private CartRemoteService service;

    private CustomerRemoteService customerService;

    private CustomerAddressRemoteService customerAddressService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = RemoteServiceFactory.getCartRemoteService();
        customerService = RemoteServiceFactory.getCustomerRemoteService();
        customerAddressService = RemoteServiceFactory.getCustomerAddressRemoteService();
    }

    @Test
    public void testCreate() {
        try {
            Cart cart = service.create(0);
            if (cart == null) {
                fail("Could not create cart [cart.create]");
            }
            System.out.println("Created cart " + cart);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetCustomer() {
        try {
            Customer customer = generateCustomer();
            customerService.save(customer);

            Cart cart = service.create(0);
            System.out.println("Created cart " + cart);
            cart.setCustomer(customer);
            service.setCustomer(cart);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetLicenseAgreements() {
        try {
            Cart cart = service.create(0);
            System.out.println("Created cart " + cart);
            service.getLicenseAgreements(cart);
            System.out.println("Received licenses: " + cart.getLicenseAgreements().size());
            for (CartLicense item : cart.getLicenseAgreements()) {
                System.out.println("    license " + item);
            }
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetTotals() {
        try {
            Cart cart = service.create(0);
            System.out.println("Created cart " + cart);
            service.getTotals(cart);
            System.out.println("Received totals: " + cart.getLicenseAgreements().size());
            for (CartTotal item : cart.getTotals()) {
                System.out.println("    total " + item);
            }
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetById() {
        try {
            Customer customer = generateCustomer();
            customerService.save(customer);

            Cart cart = service.create(0);
            cart.setCustomer(customer);
            service.setCustomer(cart);

            Cart other = service.getById(cart.getId(), cart.getStoreId());
            Assert.assertEquals(cart.getId(), other.getId());
            Assert.assertEquals(cart.getStoreId(), other.getStoreId());
            Assert.assertEquals(cart.getCustomer().getId(), other.getCustomer().getId());
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateOrderFromCart() {
        try {
            System.out.println("Creating customer");
            Customer customer = generateCustomer();
            customerService.save(customer);

            CustomerAddress shipAddr = generateAddress();
            CustomerAddress billAddr = generateAddress();
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
            System.out.println("Created cart " + cart);

            System.out.println("Setting customer");
            cart.setCustomer(customer);
            service.setCustomer(cart);

            System.out.println("Adding product");
            Product p = new Product();
            p.setSku("NI3TP");      //FIXME
            p.setId(53);            //FIXME
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
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    public static Customer generateCustomer() {
        Customer cust = new Customer();

        cust.setFirstName(MagjaStringUtils.randomString(5, 10));
        cust.setMiddleName(MagjaStringUtils.randomString(5, 10));
        cust.setLastName(MagjaStringUtils.randomString(5, 10));
        cust.setPassword("test12");
        cust.setPrefix("Herr");
        cust.setWebsiteId(1);
        cust.setGroupId(1);
        cust.setGender(Gender.MALE);
        String email = MagjaStringUtils.randomString(4, 5) + "@" + MagjaStringUtils.randomString(4, 5) + ".com";
        cust.setEmail(email.toLowerCase());

        // this include the date of birth on the customer, and it's works, 
        // but, that attribute isn't listed when getting a customer from magento
        cust.set("dob", "1980-08-17 20:53:04");

        return cust;
    }

    public CustomerAddress generateAddress() {
        CustomerAddress address = new CustomerAddress();
        address.setCity("Berlin");
        address.setCompany("Company");
        address.setCountryCode("DE");
        address.setFax("4444-1111");
        address.setPrefix("Herr");
        address.setFirstName(MagjaStringUtils.randomString(5, 10));
        address.setMiddleName(MagjaStringUtils.randomString(5, 10));
        address.setLastName(MagjaStringUtils.randomString(5, 10));
        address.setPostCode("10117");
        address.setTelephone("1111-2222");
        address.setRegion("Berlin");
        address.setStreet(MagjaStringUtils.randomString(5, 10) + " 1");

        return address;
    }
	
}
