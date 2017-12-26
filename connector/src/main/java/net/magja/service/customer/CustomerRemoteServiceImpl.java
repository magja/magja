package net.magja.service.customer;

import net.magja.magento.ResourcePath;
import net.magja.model.customer.Customer;
import net.magja.model.customer.Customer.Gender;
import net.magja.model.customer.CustomerFilter;
import net.magja.model.customer.CustomerGroup;
import net.magja.service.GeneralServiceImpl;
import net.magja.service.ServiceException;
import net.magja.soap.SoapClient;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Customer service implementation.
 * @author andre
 * @author Simon Zambrovski
 */
public class CustomerRemoteServiceImpl extends GeneralServiceImpl<Customer> implements CustomerRemoteService {

  private static final long serialVersionUID = 2048559265203182037L;

  public CustomerRemoteServiceImpl(SoapClient soapClient) {
    super(soapClient);
  }

  /**
   * Create a object customer from the attributes map
   *
   * @param attributes
   * @return Customer
   */
  private Customer buildCustomer(Map<String, Object> attributes) {
    Customer customer = new Customer();

    for (Map.Entry<String, Object> attr : attributes.entrySet())
      customer.set(attr.getKey(), attr.getValue());

    if (attributes.get("gender") != null) {
      Integer gender = new Integer((String) attributes.get("gender"));
      customer.setGender((gender.equals(new Integer(1)) ? Gender.MALE : Gender.FEMALE));
    }

    return customer;
  }

  /**
   * Create a customer group object from the specified attribute map
   *
   * @param attributes
   * @return
   */
  private CustomerGroup buildCustomerGroup(Map<String, Object> attributes) {
    CustomerGroup group = new CustomerGroup();
    for (Map.Entry<String, Object> attr : attributes.entrySet())
      group.set(attr.getKey(), attr.getValue());
    return group;
  }

  @Override
  public void delete(Integer id) throws ServiceException {

    try {
      Boolean success = (Boolean) soapClient.callSingle(ResourcePath.CustomerDelete, id);
      if (!success)
        throw new ServiceException("Error deleting the Customer");
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

  }

  @Override
  public void deleteAll() throws ServiceException {
    List<Customer> customers = list();
    for (Customer customer : customers) {
      delete(customer.getId());
    }
  }

  @Override
  public Customer getById(Integer id) throws ServiceException {

    Map<String, Object> remote_result = null;
    try {
      remote_result = (Map<String, Object>) soapClient.callSingle(ResourcePath.CustomerInfo, id);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (remote_result == null)
      return null;
    else
      return buildCustomer(remote_result);
  }

  @Override
  public List<Customer> list(Customer filter) throws ServiceException {

    List<Customer> customers = new ArrayList<Customer>();

    Object params = null;

    if (filter == null) {
      params = new String("*");
    } else if (filter.getId() != null) {
      customers.add(getById(filter.getId()));
      return customers;
    }

    if (filter != null)
      params = filter.serializeToApi();

    List<Map<String, Object>> resultList = null;
    try {
      resultList = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CustomerList, params);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (resultList == null)
      return customers;

    for (Map<String, Object> custo : resultList)
      customers.add(buildCustomer(custo));

    return customers;
  }

  @Override
  public List<Customer> list2(CustomerFilter filter) throws ServiceException {

    List<Customer> customers = new ArrayList<Customer>();

    Object params = null;

    if (filter == null) {
      params = new String("*");
    } else {
      params = filter.serializeToApi();
    }

    List<Map<String, Object>> resultList = null;
    try {
      resultList = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CustomerList, params);
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (resultList == null)
      return customers;

    for (Map<String, Object> custo : resultList)
      customers.add(buildCustomer(custo));

    return customers;
  }

  @Override
  public List<Customer> list() throws ServiceException {
    return list(null);
  }

  @Override
  public void save(Customer customer) throws ServiceException {

    if (customer.getId() == null) {
      try {
        Integer id = Integer.parseInt((String) soapClient.callSingle(ResourcePath.CustomerCreate, customer.serializeToApi()));
        customer.setId(id);
      } catch (NumberFormatException e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      } catch (AxisFault e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      }
    } else {
      try {
        Boolean success = (Boolean) soapClient.callSingle(ResourcePath.CustomerUpdate, customer.serializeToApi());
        if (!success)
          throw new ServiceException("Error updating Customer");
      } catch (AxisFault e) {
        if (debug)
          e.printStackTrace();
        throw new ServiceException(e.getMessage());
      }
    }
  }

  @Override
  public List<CustomerGroup> listGroups() throws ServiceException {

    List<CustomerGroup> groups = new ArrayList<CustomerGroup>();

    List<Map<String, Object>> list = null;
    try {
      list = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.CustomerGroupList, "");
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (list == null)
      return groups;

    for (Map<String, Object> map : list)
      groups.add(buildCustomerGroup(map));

    return groups;
  }

}
