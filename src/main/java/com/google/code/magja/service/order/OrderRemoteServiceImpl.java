/**
 * @author andre
 *
 */
package com.google.code.magja.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.order.*;
import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.Customer.Gender;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;

public class OrderRemoteServiceImpl extends GeneralServiceImpl<Order> implements
        OrderRemoteService {

	private transient Logger log = LoggerFactory.getLogger(OrderRemoteServiceImpl.class);
    private static final long serialVersionUID = 8734041145563577985L;
    
    public OrderRemoteServiceImpl(MagentoSoapClient soapClient) {
		super(soapClient);
	}

	/**
     * Build a object Order
     *
     * @param attributes
     * @return Order
     * @throws ServiceException
     */
    private Order buildOrderObject(Map<String, Object> attributes)
            throws ServiceException {
        Order order = new Order();

        for (Map.Entry<String, Object> att : attributes.entrySet())
            order.set(att.getKey(), att.getValue());

        // customer
        if (attributes.get("customer_id") != null) {
            Customer customer = new Customer();

            customer.setId(new Integer((String) attributes.get("customer_id")));
            if (attributes.get("customer_email") != null)
                customer.setEmail((String) attributes.get("customer_email"));
            if (attributes.get("customer_prefix") != null)
                customer.setPrefix((String) attributes.get("customer_prefix"));
            if (attributes.get("customer_firstname") != null)
                customer.setFirstName((String) attributes
                        .get("customer_firstname"));
            if (attributes.get("customer_middlename") != null)
                customer.setMiddleName((String) attributes
                        .get("customer_middlename"));
            if (attributes.get("customer_lastname") != null)
                customer.setLastName((String) attributes
                        .get("customer_lastname"));
            if (attributes.get("customer_lastname") != null)
                customer.setLastName((String) attributes
                        .get("customer_lastname"));
            if (attributes.get("customer_group_id") != null)
                customer.setGroupId(new Integer((String) attributes
                        .get("customer_group_id")));
            if (attributes.get("customer_gender") != null) {
                Integer gender = new Integer((String) attributes
                        .get("customer_gender"));
                customer.setGender(gender.equals(new Integer(1)) ? Gender.MALE
                        : Gender.FEMALE);
            }

            order.setCustomer(customer);
        }

        // shipping address
        Object saObject = attributes.get("shipping_address");
        if (saObject != null && saObject instanceof Map) {
            OrderAddress shippingAddress = new OrderAddress();

            Map<String, Object> atts = (Map<String, Object>) saObject;
            for (Map.Entry<String, Object> att : atts.entrySet())
                shippingAddress.set(att.getKey(), att.getValue());

            order.setShippingAddress(shippingAddress);
        }

        // billing address
        if (attributes.get("billing_address") != null) {
            OrderAddress billingAddress = new OrderAddress();

            Map<String, Object> atts = (Map<String, Object>) attributes
                    .get("billing_address");
            for (Map.Entry<String, Object> att : atts.entrySet())
                billingAddress.set(att.getKey(), att.getValue());

            order.setBillingAddress(billingAddress);
        }

        // payment
        if (attributes.get("payment") != null) {
            OrderPayment orderPayment = new OrderPayment();

            Map<String, Object> atts = (Map<String, Object>) attributes
                    .get("payment");
            for (Map.Entry<String, Object> att : atts.entrySet())
                orderPayment.set(att.getKey(), att.getValue());

            order.setOrderPayment(orderPayment);
        }

        // items
        if (attributes.get("items") != null) {
            List<Map<String, Object>> res = (List<Map<String, Object>>) attributes
                    .get("items");

            for (Map<String, Object> i : res) {
                OrderItem item = new OrderItem();
                for (Map.Entry<String, Object> att : i.entrySet())
                    item.set(att.getKey(), att.getValue());

                order.getItems().add(item);
            }
        }

        return order;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#addComment(code.google
      * .magja.model.order.Order, java.lang.String, java.lang.String,
      * java.lang.Boolean)
      */
    @Override
    public void addComment(Order order, String status, String comment,
                           Boolean notify) throws ServiceException {
        try {
            soapClient.callArgs(ResourcePath.SalesOrderAddComment, new Object[] {
            		order.getOrderNumber(), status, comment,
            		notify ? 1 : 0 });
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#cancel(code.google
      * .magja.model.order.Order)
      */
    @Override
    public void cancel(Order order) throws ServiceException {

        try {
            soapClient.callSingle(ResourcePath.SalesOrderCancel, order.getOrderNumber());
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#getById(java.lang.
      * Integer)
      */
    @Override
    public Order getById(Integer id) throws ServiceException {

        Map<String, Object> order_remote = null;
        try {
            order_remote = soapClient.callSingle(
                    ResourcePath.SalesOrderInfo, id);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (order_remote == null)
            return null;
        else
            return buildOrderObject(order_remote);
    }

    @Override
    public Order getById(String id) throws ServiceException {

        Map<String, Object> order_remote = null;
        try {
            order_remote = soapClient.callSingle(
                    ResourcePath.SalesOrderInfo, id);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (order_remote == null)
            return null;
        else
            for (Map.Entry<String, Object> att : order_remote.entrySet()) {
                System.out.println("KEY = " + att.getKey() + " VALUE = " + att.getValue());
            }


        return buildOrderObject(order_remote);
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#hold(com.google.code.magja
      * .model.order.Order)
      */
    @Override
    public void hold(Order order) throws ServiceException {
        try {
            soapClient.callSingle(ResourcePath.SalesOrderHold, order.getOrderNumber());
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#list(com.google.code.magja
      * .model.order.Filter)
      */
    @Override
    public List<Order> list(Filter filter) throws ServiceException {

        List<Order> results = new ArrayList<Order>();

        if (filter != null) {
            if (filter.getItems() != null) {
                if (filter.getItems().isEmpty())
                    filter = null;
            } else
                filter = null;
        }

        List<Map<String, Object>> order_list = null;
        try {
            order_list = soapClient.callSingle(
                    ResourcePath.SalesOrderList, (filter != null ? filter
                    .serializeToApi() : ""));
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        for (Map<String, Object> map : order_list)
            results.add(buildOrderObject(map));

        return results;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.order.OrderRemoteService#unhold(code.google
      * .magja.model.order.Order)
      */
    @Override
    public void unhold(Order order) throws ServiceException {

        try {
            soapClient.callSingle(ResourcePath.SalesOrderUnhold, order.getOrderNumber());
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /**
     * Create an {@link Order} from an {@link OrderForm}.
     */
	@Override
	public String create(OrderForm orderForm) throws ServiceException {
        try {
            String result = soapClient.callSingle(ResourcePath.SalesOrderCreate,
            		orderForm.serializeToApi());
            return result;
        } catch (AxisFault e) {
        	log.debug("Error when creating OrderForm " + orderForm, e);
            throw new ServiceException("Error when creating OrderForm " + orderForm, e);
        }
	}

    /**
     * Create an {@link Order} from an {@link OrderForm},
     * with custom addresses.
     */
	@Override
	public String createEx(OrderForm orderForm) throws ServiceException {
        try {
            String result = soapClient.callSingle(ResourcePath.SalesOrderCreateEx,
            		orderForm.serializeToApi());
            return result;  
        } catch (AxisFault e) {
        	log.debug("Error when creating OrderForm " + orderForm, e);
            throw new ServiceException("Error when creating OrderForm " + orderForm, e);
        }
	}

}
