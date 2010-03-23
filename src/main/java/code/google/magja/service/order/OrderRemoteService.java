/**
 *
 */
package code.google.magja.service.order;

import java.util.List;

import code.google.magja.model.order.Order;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface OrderRemoteService extends GeneralService<Order> {

	public abstract List<Order> list(Order filter) throws ServiceException;

	public abstract Order getById(Integer id) throws ServiceException;

	public abstract void addComment(Order order, String status, String comment, Boolean notify) throws ServiceException;

	public abstract void hold(Order order) throws ServiceException;

	public abstract void unhold(Order order) throws ServiceException;

	public abstract void cancel(Order order) throws ServiceException;

}
