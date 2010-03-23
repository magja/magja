/**
 *
 */
package code.google.magja.model.customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.model.address.Address;

/**
 * @author andre
 *
 */
public class CustomerAddress extends Address {

	private Customer customer;

	private String prefix;

	private String middleName;

	private String suffix;

	private Boolean defaultBilling;

	private Boolean defaultShipping;

	/* (non-Javadoc)
	 * @see code.google.magja.model.BaseMagentoModel#serializeToApi()
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
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
				+ ((defaultBilling == null) ? 0 : defaultBilling.hashCode());
		result = prime * result
				+ ((defaultShipping == null) ? 0 : defaultShipping.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
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
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerAddress [defaultBilling=" + defaultBilling
				+ ", defaultShipping=" + defaultShipping + ", middleName="
				+ middleName + ", prefix=" + prefix + ", suffix=" + suffix
				+ ", city=" + city + ", company=" + company + ", countryCode="
				+ countryCode + ", fax=" + fax + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", postCode=" + postCode
				+ ", region=" + region + ", street=" + street + ", telephone="
				+ telephone + ", id=" + id + ", properties=" + properties + "]";
	}

}
