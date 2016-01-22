/**
 * @author andre
 *
 */
package com.google.code.magja.model.order;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseMagentoModel {

	private static final long serialVersionUID=4199906698069050318L;

    private String orderNumber;
    private String parentOrderNumber;
    private Integer parentOrderId;

	private String storeName;

	private String status;

	private String state;

	private String shippingDescription;

	private String shippingMethod;

	private Double taxAmount;

	private Double shippingAmount;

	private Double discountAmount;

	private Double subtotal;

	private Double grandTotal;

	private Double totalPaid;

	private Double totalRefunded;

	private Double totalQtyOrdered;

	private Double totalCanceled;

	private Double totalInvoiced;

	private Double totalOnlineRefunded;

	private Double totalOfflineRefunded;

	private Double shippingTaxAmount;

	private Double shippingTaxRefunded;

	private Double weight;

	private Boolean emailSent;

	private Boolean customerIsGuest;

	private String customerEmail;

	private Customer customer = new Customer();

	private OrderAddress billingAddress = new OrderAddress();

	private OrderAddress shippingAddress = new OrderAddress();

	private OrderPayment orderPayment = new OrderPayment();

	private List<OrderItem> items = new ArrayList<OrderItem>();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the shippingDescription
	 */
	public String getShippingDescription() {
		return shippingDescription;
	}

	/**
	 * @param shippingDescription
	 *            the shippingDescription to set
	 */
	public void setShippingDescription(String shippingDescription) {
		this.shippingDescription = shippingDescription;
	}

	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @param shippingMethod
	 *            the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @return the taxAmount
	 */
	public Double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount
	 *            the taxAmount to set
	 */
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the shippingAmount
	 */
	public Double getShippingAmount() {
		return shippingAmount;
	}

	/**
	 * @param shippingAmount
	 *            the shippingAmount to set
	 */
	public void setShippingAmount(Double shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public Double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount
	 *            the discountAmount to set
	 */
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the subtotal
	 */
	public Double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal
	 *            the subtotal to set
	 */
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the grandTotal
	 */
	public Double getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal
	 *            the grandTotal to set
	 */
	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	/**
	 * @return the totalPaid
	 */
	public Double getTotalPaid() {
		return totalPaid;
	}

	/**
	 * @param totalPaid
	 *            the totalPaid to set
	 */
	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}

	/**
	 * @return the totalRefunded
	 */
	public Double getTotalRefunded() {
		return totalRefunded;
	}

	/**
	 * @param totalRefunded
	 *            the totalRefunded to set
	 */
	public void setTotalRefunded(Double totalRefunded) {
		this.totalRefunded = totalRefunded;
	}

	/**
	 * @return the totalQtyOrdered
	 */
	public Double getTotalQtyOrdered() {
		return totalQtyOrdered;
	}

	/**
	 * @param totalQtyOrdered
	 *            the totalQtyOrdered to set
	 */
	public void setTotalQtyOrdered(Double totalQtyOrdered) {
		this.totalQtyOrdered = totalQtyOrdered;
	}

	/**
	 * @return the totalCanceled
	 */
	public Double getTotalCanceled() {
		return totalCanceled;
	}

	/**
	 * @param totalCanceled
	 *            the totalCanceled to set
	 */
	public void setTotalCanceled(Double totalCanceled) {
		this.totalCanceled = totalCanceled;
	}

	/**
	 * @return the totalInvoiced
	 */
	public Double getTotalInvoiced() {
		return totalInvoiced;
	}

	/**
	 * @param totalInvoiced
	 *            the totalInvoiced to set
	 */
	public void setTotalInvoiced(Double totalInvoiced) {
		this.totalInvoiced = totalInvoiced;
	}

	/**
	 * @return the totalOnlineRefunded
	 */
	public Double getTotalOnlineRefunded() {
		return totalOnlineRefunded;
	}

	/**
	 * @param totalOnlineRefunded
	 *            the totalOnlineRefunded to set
	 */
	public void setTotalOnlineRefunded(Double totalOnlineRefunded) {
		this.totalOnlineRefunded = totalOnlineRefunded;
	}

	/**
	 * @return the totalOfflineRefunded
	 */
	public Double getTotalOfflineRefunded() {
		return totalOfflineRefunded;
	}

	/**
	 * @param totalOfflineRefunded
	 *            the totalOfflineRefunded to set
	 */
	public void setTotalOfflineRefunded(Double totalOfflineRefunded) {
		this.totalOfflineRefunded = totalOfflineRefunded;
	}

	/**
	 * @return the shippingTaxAmount
	 */
	public Double getShippingTaxAmount() {
		return shippingTaxAmount;
	}

	/**
	 * @param shippingTaxAmount
	 *            the shippingTaxAmount to set
	 */
	public void setShippingTaxAmount(Double shippingTaxAmount) {
		this.shippingTaxAmount = shippingTaxAmount;
	}

	/**
	 * @return the shippingTaxRefunded
	 */
	public Double getShippingTaxRefunded() {
		return shippingTaxRefunded;
	}

	/**
	 * @param shippingTaxRefunded
	 *            the shippingTaxRefunded to set
	 */
	public void setShippingTaxRefunded(Double shippingTaxRefunded) {
		this.shippingTaxRefunded = shippingTaxRefunded;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the emailSent
	 */
	public Boolean getEmailSent() {
		return emailSent;
	}

	/**
	 * @param emailSent
	 *            the emailSent to set
	 */
	public void setEmailSent(Boolean emailSent) {
		this.emailSent = emailSent;
	}

	/**
	 * @return the customerIsGuest
	 */
	public Boolean getCustomerIsGuest() {
		return customerIsGuest;
	}

	/**
	 * @param customerIsGuest
	 *            the customerIsGuest to set
	 */
	public void setCustomerIsGuest(Boolean customerIsGuest) {
		this.customerIsGuest = customerIsGuest;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail
	 *            the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the billingAddress
	 */
	public OrderAddress getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress
	 *            the billingAddress to set
	 */
	public void setBillingAddress(OrderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the shippingAddress
	 */
	public OrderAddress getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress
	 *            the shippingAddress to set
	 */
	public void setShippingAddress(OrderAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the orderPayment
	 */
	public OrderPayment getOrderPayment() {
		return orderPayment;
	}

	/**
	 * @param orderPayment
	 *            the orderPayment to set
	 */
	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}

	/**
	 * @return the items
	 */
	public List<OrderItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getParentOrderNumber() {
        return parentOrderNumber;
    }

    public void setParentOrderNumber(String parentOrderNumber) {
        this.parentOrderNumber = parentOrderNumber;
    }

    public Integer getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Integer parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (billingAddress != null ? !billingAddress.equals(order.billingAddress) : order.billingAddress != null)
            return false;
		if (orderPayment != null ? !orderPayment.equals(order.orderPayment) : order.orderPayment != null)
			return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (customerIsGuest != null ? !customerIsGuest.equals(order.customerIsGuest) : order.customerIsGuest != null)
            return false;
        if (discountAmount != null ? !discountAmount.equals(order.discountAmount) : order.discountAmount != null)
            return false;
        if (emailSent != null ? !emailSent.equals(order.emailSent) : order.emailSent != null) return false;
        if (grandTotal != null ? !grandTotal.equals(order.grandTotal) : order.grandTotal != null) return false;
        if (items != null ? !items.equals(order.items) : order.items != null) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        if (parentOrderId != null ? !parentOrderId.equals(order.parentOrderId) : order.parentOrderId != null)
            return false;
        if (parentOrderNumber != null ? !parentOrderNumber.equals(order.parentOrderNumber) : order.parentOrderNumber != null)
            return false;
        if (shippingAddress != null ? !shippingAddress.equals(order.shippingAddress) : order.shippingAddress != null)
            return false;
        if (shippingAmount != null ? !shippingAmount.equals(order.shippingAmount) : order.shippingAmount != null)
            return false;
        if (shippingDescription != null ? !shippingDescription.equals(order.shippingDescription) : order.shippingDescription != null)
            return false;
        if (shippingMethod != null ? !shippingMethod.equals(order.shippingMethod) : order.shippingMethod != null)
            return false;
        if (shippingTaxAmount != null ? !shippingTaxAmount.equals(order.shippingTaxAmount) : order.shippingTaxAmount != null)
            return false;
        if (shippingTaxRefunded != null ? !shippingTaxRefunded.equals(order.shippingTaxRefunded) : order.shippingTaxRefunded != null)
            return false;
		if (customerEmail != null ? !customerEmail.equals(order.customerEmail) : order.customerEmail != null)
			return false;
		if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (storeName != null ? !storeName.equals(order.storeName) : order.storeName != null) return false;
        if (subtotal != null ? !subtotal.equals(order.subtotal) : order.subtotal != null) return false;
        if (taxAmount != null ? !taxAmount.equals(order.taxAmount) : order.taxAmount != null) return false;
        if (totalCanceled != null ? !totalCanceled.equals(order.totalCanceled) : order.totalCanceled != null)
            return false;
        if (totalInvoiced != null ? !totalInvoiced.equals(order.totalInvoiced) : order.totalInvoiced != null)
            return false;
        if (totalOfflineRefunded != null ? !totalOfflineRefunded.equals(order.totalOfflineRefunded) : order.totalOfflineRefunded != null)
            return false;
        if (totalOnlineRefunded != null ? !totalOnlineRefunded.equals(order.totalOnlineRefunded) : order.totalOnlineRefunded != null)
            return false;
        if (totalPaid != null ? !totalPaid.equals(order.totalPaid) : order.totalPaid != null) return false;
        if (totalQtyOrdered != null ? !totalQtyOrdered.equals(order.totalQtyOrdered) : order.totalQtyOrdered != null)
            return false;
        if (totalRefunded != null ? !totalRefunded.equals(order.totalRefunded) : order.totalRefunded != null)
            return false;
        if (weight != null ? !weight.equals(order.weight) : order.weight != null) return false;

        return true;
    }public String toString() {
    return "Order{" +
            "orderNumber='" + orderNumber + '\'' +
            ", parentOrderNumber='" + parentOrderNumber + '\'' +
            ", parentOrderId=" + parentOrderId +
            ", storeName='" + storeName + '\'' +
            ", status='" + status + '\'' +
            ", state='" + state + '\'' +
            ", shippingDescription='" + shippingDescription + '\'' +
            ", shippingMethod='" + shippingMethod + '\'' +
            ", taxAmount=" + taxAmount +
            ", shippingAmount=" + shippingAmount +
            ", discountAmount=" + discountAmount +
            ", subtotal=" + subtotal +
            ", grandTotal=" + grandTotal +
            ", totalPaid=" + totalPaid +
            ", totalRefunded=" + totalRefunded +
            ", totalQtyOrdered=" + totalQtyOrdered +
            ", totalCanceled=" + totalCanceled +
            ", totalInvoiced=" + totalInvoiced +
            ", totalOnlineRefunded=" + totalOnlineRefunded +
            ", totalOfflineRefunded=" + totalOfflineRefunded +
            ", shippingTaxAmount=" + shippingTaxAmount +
            ", shippingTaxRefunded=" + shippingTaxRefunded +
            ", weight=" + weight +
            ", emailSent=" + emailSent +
            ", customerEmail=" + customerEmail +
            ", customerIsGuest=" + customerIsGuest +
            ", customer=" + customer +
            ", billingAddress=" + billingAddress +
			", orderPayment=" + orderPayment +
            ", shippingAddress=" + shippingAddress +
            ", items=" + items +
            '}';
}


}
