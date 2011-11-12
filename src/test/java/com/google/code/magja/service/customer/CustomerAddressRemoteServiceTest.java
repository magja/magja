/**
 *
 */
package com.google.code.magja.service.customer;

import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.utils.MagjaStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author andre
 */
public class CustomerAddressRemoteServiceTest {

    private CustomerAddressRemoteService service;

    private CustomerRemoteService customerService;

    private Integer address_id;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = RemoteServiceFactory.getCustomerAddressRemoteService();
        customerService = RemoteServiceFactory.getCustomerRemoteService();
    }

    public static CustomerAddress generateAddress() {

        CustomerAddress address = new CustomerAddress();

        address.setCity(MagjaStringUtils.randomString(5, 10));
        address.setCompany(MagjaStringUtils.randomString(5, 10));
        address.setCountryCode("BR");
        address.setDefaultBilling(true);
        address.setDefaultShipping(false);
        address.setFax("(19) 4444-1111");
        address.setFirstName(MagjaStringUtils.randomString(5, 10));
        address.setMiddleName(MagjaStringUtils.randomString(5, 10));
        address.setLastName(MagjaStringUtils.randomString(5, 10));
        address.setPostCode("13000-002");
        address.setTelephone("(19) 1111-2222");
        address.setRegion("SP");
        address.setStreet(MagjaStringUtils.randomString(5, 10));

        return address;
    }

    /**
     * Test method for {@link com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl#save(com.google.code.magja.model.customer.CustomerAddress)}.
     */
    @Test
    public void testSave() {
        // first, create a new Customer
        Customer customer = CustomerRemoteServiceTest.generateCustomer();

        try {
            customerService.save(customer);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }

        // then create a new Address for that customer
        CustomerAddress address = generateAddress();
        address.setCustomer(customer);

        try {
            service.save(address);
            address_id = address.getId();
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl#save(com.google.code.magja.model.customer.CustomerAddress)}.
     */
    @Test
    public void testSaveAndUpdate() {
        // first, create a new Customer
        Customer customer = CustomerRemoteServiceTest.generateCustomer();

        try {
            customerService.save(customer);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }

        // then create a new Address for that customer
        CustomerAddress address = generateAddress();
        address.setCustomer(customer);

        try {
            service.save(address);
            address_id = address.getId();
        } catch (ServiceException e) {
            fail(e.getMessage());
        }

        // update that address
        address.setStreet("Av. James Rules, 293");
        address.setPostCode("02931-293");
        address.setTelephone("(19) 3255-0000");
        address.setCompany("Company Bla Bla Bla");

        try {
            service.save(address);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl#delete(java.lang.Integer)}.
     */
    @Test
    public void testDelete() {
        testSave();
        try {
            service.delete(address_id);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl#getById(java.lang.Integer)}.
     */
    @Test
    public void testGetById() {
        testSave();
        try {
            CustomerAddress address = service.getById(address_id);
            System.out.println(address.toString());
        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl#list(java.lang.Integer)}.
     */
    @Test
    public void testList() {

        int count_addresses = 4;

        // first, create a new Customer
        Customer customer = CustomerRemoteServiceTest.generateCustomer();

        try {
            customerService.save(customer);
        } catch (ServiceException e) {
            fail(e.getMessage());
        }

        for (int i = 0; i < count_addresses; i++) {
            CustomerAddress address = generateAddress();
            address.setCustomer(customer);
            try {
                service.save(address);
            } catch (ServiceException e) {
                fail(e.getMessage());
            }
        }

        try {
            List<CustomerAddress> results = service.list(customer.getId());

            for (CustomerAddress customerAddress : results)
                System.out.println(customerAddress.toString());

            assertEquals(results.size(), count_addresses);

        } catch (ServiceException e) {
            fail(e.getMessage());
        }


    }

}
