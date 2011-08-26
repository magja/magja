/**
 * @author andre
 *
 */
package com.google.code.magja.service.customer;

import java.util.List;

import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface CustomerAddressRemoteService extends
		GeneralService<CustomerAddress> {

	/**
	 * List the address of a customer with the specified id
	 *
	 * @param customerId
	 * @return List<CustomerAddress>
	 * @throws ServiceException
	 */
	public abstract List<CustomerAddress> list(Integer customerId)
			throws ServiceException;

	/**
	 * Save (id == null) or Update (id != null) a Customer Address
	 *
	 * @param customerAddress
	 * @throws ServiceException
	 */
	public abstract void save(CustomerAddress customerAddress)
			throws ServiceException;

	/**
	 * Get a Customer Address by your id
	 *
	 * @param id
	 * @return CustomerAddress
	 * @throws ServiceException
	 */
	public abstract CustomerAddress getById(Integer id) throws ServiceException;

	/**
	 * Delete a customer address by its id
	 *
	 * @param id
	 * @throws ServiceException
	 */
	public abstract void delete(Integer id) throws ServiceException;

	/**
	 * Delete all customer address by its id
	 *
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteAll(Integer customerId) throws ServiceException;
}
