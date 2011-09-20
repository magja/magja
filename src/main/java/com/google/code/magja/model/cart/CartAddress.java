package com.google.code.magja.model.cart;

import java.util.HashMap;
import java.util.Map;

import com.google.code.magja.model.address.Address;

/**
 * @author schneider
 * 
 */
public class CartAddress extends Address {
	
	private static final long serialVersionUID = -5542075721215076672L;
	
	public Type type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		Map<String, Object> attrs = new HashMap<String, Object>();

		attrs.put("mode", type.getName());

		if (this.getId() != null) {
			attrs.put("address_id", getId());
		}
		attrs.put("firstname", getFirstName());
		attrs.put("lastname", getLastName());
		attrs.put("street", getStreet());
		attrs.put("city", getCity());
		attrs.put("postcode", getPostCode());
		attrs.put("country_id", getCountryCode());
		attrs.put("telephone", getTelephone());

		if (getRegion() != null) {
			attrs.put("region", getRegion());
		}

		if (getCompany() != null) {
			attrs.put("company", getCompany());
		}

		System.err.println(attrs);
		return attrs;
	}

	public static CartAddress fromAttributes(Map<String, Object> attrs) {
		Address result = Address.fromAttributes(new CartAddress(), attrs);
		return (CartAddress) result;
	}

	public Type getType() {
		return type;
	}

	public void setType(CartAddress.Type type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartAddress [city=" + city + ", company=" + company
				+ ", countryCode=" + countryCode + ", fax=" + fax
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", postCode=" + postCode
				+ ", prefix=" + prefix + ", region=" + region + ", street="
				+ street + ", suffix=" + suffix + ", telephone=" + telephone
				+ ", id=" + id + ", properties=" + properties + "]";
	}

	public static enum Type {

		Billing("billing"), Shipping("shipping");

		private String name;

		Type(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
