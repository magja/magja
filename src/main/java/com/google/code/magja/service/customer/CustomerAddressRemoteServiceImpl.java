/**
 * @author andre
 *
 */
package com.google.code.magja.service.customer;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerAddressRemoteServiceImpl extends
        GeneralServiceImpl<CustomerAddress> implements
        CustomerAddressRemoteService {

    private static final long serialVersionUID = 2269696861048421719L;

    /**
     * Create a object CustomerAddress from the attributes map
     *
     * @param attributes
     * @return CustomerAddress
     */
    private CustomerAddress buildCustomerAddress(Map<String, Object> attributes) {
        CustomerAddress address = new CustomerAddress();

        for (Map.Entry<String, Object> attr : attributes.entrySet())
            address.set(attr.getKey(), attr.getValue());

        return address;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.customer.CustomerAddressRemoteService#delete
      * (java.lang.Integer)
      */
    @Override
    public void delete(Integer id) throws ServiceException {

        try {
            Boolean success = (Boolean) soapClient.call(
                    ResourcePath.CustomerAddressDelete, id);
            if (!success)
                throw new ServiceException(
                        "Error deleting the Customer Address");
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.customer.CustomerRemoteService#deleteAll()
      */
    @Override
    public void deleteAll(Integer customerId) throws ServiceException {
        List<CustomerAddress> addresses = list(customerId);
        for (CustomerAddress address : addresses) {
            delete(address.getId());
        }
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.customer.CustomerAddressRemoteService#getById
      * (java.lang.Integer)
      */
    @Override
    public CustomerAddress getById(Integer id) throws ServiceException {

        Map<String, Object> remote_result = null;
        try {
            remote_result = (Map<String, Object>) soapClient.call(
                    ResourcePath.CustomerAddressInfo, id);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (remote_result == null)
            return null;
        else
            return buildCustomerAddress(remote_result);

    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.customer.CustomerAddressRemoteService#list(
      * java.lang.Integer)
      */
    @Override
    public List<CustomerAddress> list(Integer customerId)
            throws ServiceException {

        List<CustomerAddress> addresses = new ArrayList<CustomerAddress>();

        List<Map<String, Object>> resultList = null;
        try {
            resultList = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.CustomerAddressList, customerId);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (resultList == null)
            return addresses;

        for (Map<String, Object> add : resultList)
            addresses.add(buildCustomerAddress(add));

        return addresses;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.customer.CustomerAddressRemoteService#create(
      * com.google.code.magja.model.customer.CustomerAddress)
      */
    @Override
    public void save(CustomerAddress customerAddress) throws ServiceException {

        if (customerAddress.getId() == null) {
            try {
                Integer id = Integer.parseInt((String) soapClient.call(
                        ResourcePath.CustomerAddressCreate, customerAddress
                        .serializeToApi()));
                customerAddress.setId(id);
            } catch (NumberFormatException e) {
                if (debug) e.printStackTrace();
                throw new ServiceException(e.getMessage());
            } catch (AxisFault e) {
                if (debug) e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        } else {
            try {
                Boolean success = (Boolean) soapClient.call(
                        ResourcePath.CustomerAddressUpdate, customerAddress
                        .serializeToApi());
                if (!success)
                    throw new ServiceException(
                            "Error updating Customer Address");
            } catch (AxisFault e) {
                if (debug) e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        }

    }

}
