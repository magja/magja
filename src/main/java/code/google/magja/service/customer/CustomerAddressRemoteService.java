/**
 *
 */
package code.google.magja.service.customer;

import java.util.List;

import code.google.magja.model.customer.CustomerAddress;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
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

}
