/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import java.util.List;
import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;

public class ConfigurableAttributeData extends BaseMagentoModel {

	private static final long serialVersionUID = -8841796458643254112L;

	private String label;

	private Integer position;

	private Integer attributeId;

	private String attributeCode;

	private String frontendLabel;

	private String htmlId;

	private List<ConfigurableData> values;

	@Override
	public Object serializeToApi() {

		Map<String, Object> props = getAllProperties();
		props.remove(values);

		return props;
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
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
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
	 * @return the attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}

	/**
	 * @param attributeCode the attributeCode to set
	 */
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	/**
	 * @return the frontendLabel
	 */
	public String getFrontendLabel() {
		return frontendLabel;
	}

	/**
	 * @param frontendLabel the frontendLabel to set
	 */
	public void setFrontendLabel(String frontendLabel) {
		this.frontendLabel = frontendLabel;
	}

	/**
	 * @return the htmlId
	 */
	public String getHtmlId() {
		return htmlId;
	}

	/**
	 * @param htmlId the htmlId to set
	 */
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}

	/**
	 * @return the values
	 */
	public List<ConfigurableData> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<ConfigurableData> values) {
		this.values = values;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((attributeCode == null) ? 0 : attributeCode.hashCode());
		result = prime * result
				+ ((attributeId == null) ? 0 : attributeId.hashCode());
		result = prime * result
				+ ((frontendLabel == null) ? 0 : frontendLabel.hashCode());
		result = prime * result + ((htmlId == null) ? 0 : htmlId.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		ConfigurableAttributeData other = (ConfigurableAttributeData) obj;
		if (attributeCode == null) {
			if (other.attributeCode != null)
				return false;
		} else if (!attributeCode.equals(other.attributeCode))
			return false;
		if (attributeId == null) {
			if (other.attributeId != null)
				return false;
		} else if (!attributeId.equals(other.attributeId))
			return false;
		if (frontendLabel == null) {
			if (other.frontendLabel != null)
				return false;
		} else if (!frontendLabel.equals(other.frontendLabel))
			return false;
		if (htmlId == null) {
			if (other.htmlId != null)
				return false;
		} else if (!htmlId.equals(other.htmlId))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigurableAttributeData [label=" + label + ", position="
				+ position + ", attributeId=" + attributeId
				+ ", attributeCode=" + attributeCode + ", frontendLabel="
				+ frontendLabel + ", htmlId=" + htmlId + ", values=" + values
				+ "]";
	}

}
