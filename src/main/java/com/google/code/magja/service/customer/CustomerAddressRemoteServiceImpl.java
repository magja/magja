/**
 *
 */
package code.google.magja.service.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.customer.CustomerAddress;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CustomerAddressRemoteServiceImpl extends
		GeneralServiceImpl<CustomerAddress> implements
		CustomerAddressRemoteService {

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
	 * code.google.magja.service.customer.CustomerAddressRemoteService#delete
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
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.customer.CustomerAddressRemoteService#getById
	 * (java.lang.Integer)
	 */
	@Override
	public CustomerAddress getById(Integer id) throws ServiceException {

		Map<String, Object> remote_result = null;
		try {
			remote_result = (Map<String, Object>) soapClient.call(
					ResourcePath.CustomerAddressInfo, id);
		} catch (AxisFault e) {
			e.printStackTrace();
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
	 * code.google.magja.service.customer.CustomerAddressRemoteService#list(
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
			e.printStackTrace();
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
	 * code.google.magja.service.customer.CustomerAddressRemoteService#save(
	 * code.google.magja.model.customer.CustomerAddress)
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
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			} catch (AxisFault e) {
				e.printStackTrace();
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
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

	}

}
