/**
 *
 */
package code.google.magja.service.customer;

import java.util.List;

import code.google.magja.model.customer.Customer;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
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

}
