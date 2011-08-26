/**
 * @author andre
 *
 */
package com.google.code.magja.model.order;

import java.util.ArrayList;
import java.util.List;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.customer.Customer;

public class Order extends BaseMagentoModel {

	private static final long serialVersionUID=4199906698069050318L;

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

	private Customer customer = new Customer();

	private OrderAddress billingAddress = new OrderAddress();

	private OrderAddress shippingAddress = new OrderAddress();

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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((customerIsGuest == null) ? 0 : customerIsGuest.hashCode());
		result = prime * result
				+ ((discountAmount == null) ? 0 : discountAmount.hashCode());
		result = prime * result
				+ ((emailSent == null) ? 0 : emailSent.hashCode());
		result = prime * result
				+ ((grandTotal == null) ? 0 : grandTotal.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result
				+ ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result
				+ ((shippingAmount == null) ? 0 : shippingAmount.hashCode());
		result = prime
				* result
				+ ((shippingDescription == null) ? 0 : shippingDescription
						.hashCode());
		result = prime * result
				+ ((shippingMethod == null) ? 0 : shippingMethod.hashCode());
		result = prime
				* result
				+ ((shippingTaxAmount == null) ? 0 : shippingTaxAmount
						.hashCode());
		result = prime
				* result
				+ ((shippingTaxRefunded == null) ? 0 : shippingTaxRefunded
						.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result
				+ ((subtotal == null) ? 0 : subtotal.hashCode());
		result = prime * result
				+ ((taxAmount == null) ? 0 : taxAmount.hashCode());
		result = prime * result
				+ ((totalCanceled == null) ? 0 : totalCanceled.hashCode());
		result = prime * result
				+ ((totalInvoiced == null) ? 0 : totalInvoiced.hashCode());
		result = prime
				* result
				+ ((totalOfflineRefunded == null) ? 0 : totalOfflineRefunded
						.hashCode());
		result = prime
				* result
				+ ((totalOnlineRefunded == null) ? 0 : totalOnlineRefunded
						.hashCode());
		result = prime * result
				+ ((totalPaid == null) ? 0 : totalPaid.hashCode());
		result = prime * result
				+ ((totalQtyOrdered == null) ? 0 : totalQtyOrdered.hashCode());
		result = prime * result
				+ ((totalRefunded == null) ? 0 : totalRefunded.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (customerIsGuest == null) {
			if (other.customerIsGuest != null)
				return false;
		} else if (!customerIsGuest.equals(other.customerIsGuest))
			return false;
		if (discountAmount == null) {
			if (other.discountAmount != null)
				return false;
		} else if (!discountAmount.equals(other.discountAmount))
			return false;
		if (emailSent == null) {
			if (other.emailSent != null)
				return false;
		} else if (!emailSent.equals(other.emailSent))
			return false;
		if (grandTotal == null) {
			if (other.grandTotal != null)
				return false;
		} else if (!grandTotal.equals(other.grandTotal))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingAmount == null) {
			if (other.shippingAmount != null)
				return false;
		} else if (!shippingAmount.equals(other.shippingAmount))
			return false;
		if (shippingDescription == null) {
			if (other.shippingDescription != null)
				return false;
		} else if (!shippingDescription.equals(other.shippingDescription))
			return false;
		if (shippingMethod == null) {
			if (other.shippingMethod != null)
				return false;
		} else if (!shippingMethod.equals(other.shippingMethod))
			return false;
		if (shippingTaxAmount == null) {
			if (other.shippingTaxAmount != null)
				return false;
		} else if (!shippingTaxAmount.equals(other.shippingTaxAmount))
			return false;
		if (shippingTaxRefunded == null) {
			if (other.shippingTaxRefunded != null)
				return false;
		} else if (!shippingTaxRefunded.equals(other.shippingTaxRefunded))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (subtotal == null) {
			if (other.subtotal != null)
				return false;
		} else if (!subtotal.equals(other.subtotal))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		if (totalCanceled == null) {
			if (other.totalCanceled != null)
				return false;
		} else if (!totalCanceled.equals(other.totalCanceled))
			return false;
		if (totalInvoiced == null) {
			if (other.totalInvoiced != null)
				return false;
		} else if (!totalInvoiced.equals(other.totalInvoiced))
			return false;
		if (totalOfflineRefunded == null) {
			if (other.totalOfflineRefunded != null)
				return false;
		} else if (!totalOfflineRefunded.equals(other.totalOfflineRefunded))
			return false;
		if (totalOnlineRefunded == null) {
			if (other.totalOnlineRefunded != null)
				return false;
		} else if (!totalOnlineRefunded.equals(other.totalOnlineRefunded))
			return false;
		if (totalPaid == null) {
			if (other.totalPaid != null)
				return false;
		} else if (!totalPaid.equals(other.totalPaid))
			return false;
		if (totalQtyOrdered == null) {
			if (other.totalQtyOrdered != null)
				return false;
		} else if (!totalQtyOrdered.equals(other.totalQtyOrdered))
			return false;
		if (totalRefunded == null) {
			if (other.totalRefunded != null)
				return false;
		} else if (!totalRefunded.equals(other.totalRefunded))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [billingAddress=" + billingAddress + ", customer="
				+ customer + ", customerIsGuest=" + customerIsGuest
				+ ", discountAmount=" + discountAmount + ", emailSent="
				+ emailSent + ", grandTotal=" + grandTotal + ", items=" + items
				+ ", shippingAddress=" + shippingAddress + ", shippingAmount="
				+ shippingAmount + ", shippingDescription="
				+ shippingDescription + ", shippingMethod=" + shippingMethod
				+ ", shippingTaxAmount=" + shippingTaxAmount
				+ ", shippingTaxRefunded=" + shippingTaxRefunded + ", state="
				+ state + ", status=" + status + ", storeName=" + storeName
				+ ", subtotal=" + subtotal + ", taxAmount=" + taxAmount
				+ ", totalCanceled=" + totalCanceled + ", totalInvoiced="
				+ totalInvoiced + ", totalOfflineRefunded="
				+ totalOfflineRefunded + ", totalOnlineRefunded="
				+ totalOnlineRefunded + ", totalPaid=" + totalPaid
				+ ", totalQtyOrdered=" + totalQtyOrdered + ", totalRefunded="
				+ totalRefunded + ", weight=" + weight + ", id=" + id
				+ ", properties=" + properties + "]";
	}

}
