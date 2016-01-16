/**
 * @author andre
 *
 */
package com.google.code.magja.service.order;

import com.google.code.magja.model.order.Filter;
import com.google.code.magja.model.order.Order;
import com.google.code.magja.model.order.OrderForm;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

import java.util.List;

public interface OrderRemoteService extends GeneralService<Order> {

    public abstract List<Order> list(Filter filter) throws ServiceException;

    public abstract Order getById(Integer id) throws ServiceException;

    public abstract void addComment(Order order, String status, String comment, Boolean notify) throws ServiceException;

    public abstract void hold(Order order) throws ServiceException;

    public abstract void unhold(Order order) throws ServiceException;

    public abstract void cancel(Order order) throws ServiceException;

    Order getById(String id) throws ServiceException;

    /**
     * Create an {@link Order} from an {@link OrderForm},
     * with address from Customer data.
     */
	public abstract String create(OrderForm orderForm) throws ServiceException;
	
    /**
     * Create an {@link Order} from an {@link OrderForm},
     * with custom addresses.
     */
	public abstract String createEx(OrderForm orderForm) throws ServiceException;
	
}
