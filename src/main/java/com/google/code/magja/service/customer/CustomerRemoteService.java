/**
 * @author andre
 *
 */
package com.google.code.magja.service.customer;

import java.util.List;

import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.CustomerFilter;
import com.google.code.magja.model.customer.CustomerGroup;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface CustomerRemoteService extends GeneralService<Customer> {

	/**
	 * Save (id == null) or Update (id != null) a Customer
	 *
	 * @param customer
	 * @throws ServiceException
	 */
	public abstract void save(Customer customer) throws ServiceException;

	/**
	 * Get a Customer by your id
	 *
	 * @param id
	 * @return Customer
	 * @throws ServiceException
	 */
	public abstract Customer getById(Integer id) throws ServiceException;

	/**
	 * List customers based of the filter specified
	 *
	 * @param filter
	 * @return List<Customer>
	 * @throws ServiceException
	 */
	public abstract List<Customer> list(Customer filter)
			throws ServiceException;

	/**
	 * List customers based of the filter specified
	 *
	 * @param filter
	 * @return List<Customer>
	 * @throws ServiceException
	 */	public abstract List<Customer> list2(CustomerFilter filter) 
			throws ServiceException;
	
	/**
	 * List all customers on magento
	 *
	 * @return List<Customer>
	 * @throws ServiceException
	 */
	public abstract List<Customer> list() throws ServiceException;

	/**
	 * Delete a customer with the specified id
	 *
	 * @param id
	 * @throws ServiceException
	 */
	public abstract void delete(Integer id) throws ServiceException;

	/**
	 * Delete all customer
	 *
	 * @throws ServiceException
	 */
	public void deleteAll() throws ServiceException;

	/**
	 * @return list of all customer groups on magento
	 * @throws ServiceException
	 */
	public abstract List<CustomerGroup> listGroups() throws ServiceException;

}
