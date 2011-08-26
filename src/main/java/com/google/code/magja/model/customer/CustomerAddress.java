/**
 * @author andre
 *
 */
package com.google.code.magja.model.customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.address.Address;

public class CustomerAddress extends Address {

	private static final long serialVersionUID=-7194400140257435078L;

	private Customer customer;

	private Boolean defaultBilling;

	private Boolean defaultShipping;

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {

		Map<String, Object> props = getAllProperties();
		props.remove("customer_address_id");

		List<Object> params = new LinkedList<Object>();

		// if its a update (id != null), put the address id, otherwise (create) put the customer id
		params.add((this.id != null ? this.id : customer.getId()));

		params.add(props);

		return params;
	}

	/**
	 * @return the defaultBilling
	 */
	public Boolean getDefaultBilling() {
		return defaultBilling;
	}

	/**
	 * @param defaultBilling the defaultBilling to set
	 */
	public void setDefaultBilling(Boolean defaultBilling) {
		this.defaultBilling = defaultBilling;
	}

	/**
	 * @return the defaultShipping
	 */
	public Boolean getDefaultShipping() {
		return defaultShipping;
	}

	/**
	 * @param defaultShipping the defaultShipping to set
	 */
	public void setDefaultShipping(Boolean defaultShipping) {
		this.defaultShipping = defaultShipping;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((defaultBilling == null) ? 0 : defaultBilling.hashCode());
		result = prime * result
				+ ((defaultShipping == null) ? 0 : defaultShipping.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		CustomerAddress other = (CustomerAddress) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (defaultBilling == null) {
			if (other.defaultBilling != null)
				return false;
		} else if (!defaultBilling.equals(other.defaultBilling))
			return false;
		if (defaultShipping == null) {
			if (other.defaultShipping != null)
				return false;
		} else if (!defaultShipping.equals(other.defaultShipping))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerAddress [defaultBilling="
				+ defaultBilling + ", defaultShipping=" + defaultShipping
				+ ", city=" + city + ", company=" + company + ", countryCode="
				+ countryCode + ", fax=" + fax + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName
				+ ", postCode=" + postCode + ", prefix=" + prefix + ", region="
				+ region + ", street=" + street + ", suffix=" + suffix
				+ ", telephone=" + telephone + ", id=" + id + ", properties="
				+ properties + "]";
	}
}
