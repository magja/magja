/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import com.google.code.magja.model.BaseMagentoModel;

public class ConfigurableData extends BaseMagentoModel {

	private static final long serialVersionUID = -6615350392582486229L;

	private Integer attributeId;

	private String label;

	private Integer valueIndex;

	private Boolean isPercent;

	private Double pricingValue;

	@Override
	public Object serializeToApi() {
		return getAllProperties();
	}

	/**
	 * @return the attributeId
	 */
	public Integer getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId the attributeId to set
	 */
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the valueIndex
	 */
	public Integer getValueIndex() {
		return valueIndex;
	}

	/**
	 * @param valueIndex the valueIndex to set
	 */
	public void setValueIndex(Integer valueIndex) {
		this.valueIndex = valueIndex;
	}

	/**
	 * @return the isPercent
	 */
	public Boolean getIsPercent() {
		return isPercent;
	}

	/**
	 * @param isPercent the isPercent to set
	 */
	public void setIsPercent(Boolean isPercent) {
		this.isPercent = isPercent;
	}

	/**
	 * @return the pricingValue
	 */
	public Double getPricingValue() {
		return pricingValue;
	}

	/**
	 * @param pricingValue the pricingValue to set
	 */
	public void setPricingValue(Double pricingValue) {
		this.pricingValue = pricingValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result
				+ ((isPercent == null) ? 0 : isPercent.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((pricingValue == null) ? 0 : pricingValue.hashCode());
		result = prime * result
				+ ((valueIndex == null) ? 0 : valueIndex.hashCode());
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
		ConfigurableData other = (ConfigurableData) obj;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (isPercent == null) {
			if (other.isPercent != null)
				return false;
		} else if (!isPercent.equals(other.isPercent))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (pricingValue == null) {
			if (other.pricingValue != null)
				return false;
		} else if (!pricingValue.equals(other.pricingValue))
			return false;
		if (valueIndex == null) {
			if (other.valueIndex != null)
				return false;
		} else if (!valueIndex.equals(other.valueIndex))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigurableData [attributeId=" + attributeId
				+ ", label=" + label + ", valueIndex=" + valueIndex
				+ ", isPercent=" + isPercent + ", pricingValue=" + pricingValue
				+ "]";
	}

}
