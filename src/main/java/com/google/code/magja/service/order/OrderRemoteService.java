package com.google.code.magja.service.order;

import com.google.code.magja.model.order.Filter;
import com.google.code.magja.model.order.Order;
import com.google.code.magja.model.order.OrderForm;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

import java.util.List;

/**
 * Service handling customer orders.
 * 
 * @author andre
 * @author Simon Zambrovski
 */
public interface OrderRemoteService extends GeneralService<Order> {

  List<Order> list(Filter filter) throws ServiceException;

  Order getById(Integer id) throws ServiceException;

  void addComment(Order order, String status, String comment, Boolean notify) throws ServiceException;

  void hold(Order order) throws ServiceException;

  void unhold(Order order) throws ServiceException;

  void cancel(Order order) throws ServiceException;

  Order getById(String id) throws ServiceException;

  /**
   * Create an {@link Order} from an {@link OrderForm}, with address from
   * Customer data.
   */
  String create(OrderForm orderForm) throws ServiceException;

  /**
   * Create an {@link Order} from an {@link OrderForm}, with custom addresses.
   */
  String createEx(OrderForm orderForm) throws ServiceException;

}
