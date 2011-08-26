/**
 * @author andre
 *
 */
package com.google.code.magja.model.country;

import com.google.code.magja.model.BaseMagentoModel;

public class Country extends BaseMagentoModel {

	private static final long serialVersionUID=7345606611959211286L;

	private String countryId;

	private String name;

	private String iso3Code;

	private String iso2Code;

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iso3Code
	 */
	public String getIso3Code() {
		return iso3Code;
	}

	/**
	 * @param iso3Code the iso3Code to set
	 */
	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}

	/**
	 * @return the iso2Code
	 */
	public String getIso2Code() {
		return iso2Code;
	}

	/**
	 * @param iso2Code the iso2Code to set
	 */
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((countryId == null) ? 0 : countryId.hashCode());
		result = prime * result
				+ ((iso2Code == null) ? 0 : iso2Code.hashCode());
		result = prime * result
				+ ((iso3Code == null) ? 0 : iso3Code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Country other = (Country) obj;
		if (countryId == null) {
			if (other.countryId != null)
				return false;
		} else if (!countryId.equals(other.countryId))
			return false;
		if (iso2Code == null) {
			if (other.iso2Code != null)
				return false;
		} else if (!iso2Code.equals(other.iso2Code))
			return false;
		if (iso3Code == null) {
			if (other.iso3Code != null)
				return false;
		} else if (!iso3Code.equals(other.iso3Code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", iso2Code=" + iso2Code
				+ ", iso3Code=" + iso3Code + ", name=" + name;
	}

}
