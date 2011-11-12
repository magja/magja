package com.google.code.magja.soap;

import java.io.Serializable;
import java.util.Properties;

public class SoapConfig implements Serializable {

	private static final long serialVersionUID = 6393846699827611865L;

	public static final String MAGENTO_API_PASSWORD = "magento-api-password";

	public static final String MAGENTO_API_URL = "magento-api-url";

	public static final String MAGENTO_API_USERNAME = "magento-api-username";

	public static final String DEFAULT_ATTRIBUTE_SET_ID = "default-attribute-set-id";

	public static final String DEFAULT_ROOT_CATEGORY_ID = "default-root-category-id";

	private String apiUser;

	private String apiKey;

	private String remoteHost;

	private Integer defaultAttributeSetId;

	private Integer defaultRootCategoryId;

	public SoapConfig(String apiUser, String apiKey, String remoteHost) {
		this.apiUser = apiUser;
		this.apiKey = apiKey;
		this.remoteHost = remoteHost;
		this.defaultAttributeSetId = 4;
		this.defaultRootCategoryId = 2;
	}

    public SoapConfig() {
    }

    public SoapConfig(Properties properties) {
		this.apiUser = properties.getProperty(MAGENTO_API_USERNAME);
		this.apiKey = properties.getProperty(MAGENTO_API_PASSWORD);
		this.remoteHost = properties.getProperty(MAGENTO_API_URL);
		this.defaultAttributeSetId = Integer.parseInt(properties
				.getProperty(DEFAULT_ATTRIBUTE_SET_ID));
		this.defaultRootCategoryId = Integer.parseInt(properties
				.getProperty(DEFAULT_ROOT_CATEGORY_ID));
	}

	/**
	 * @return the apiUser
	 */
	public String getApiUser() {
		return apiUser;
	}

	/**
	 * @param apiUser
	 *            the apiUser to set
	 */
	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey
	 *            the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @param remoteHost
	 *            the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * @return the defaultAttributeSetId
	 */
	public Integer getDefaultAttributeSetId() {
		return defaultAttributeSetId;
	}

	/**
	 * @param defaultAttributeSetId
	 *            the defaultAttributeSetId to set
	 */
	public void setDefaultAttributeSetId(Integer defaultAttributeSetId) {
		this.defaultAttributeSetId = defaultAttributeSetId;
	}

	/**
	 * @return the defaultRootCategoryId
	 */
	public Integer getDefaultRootCategoryId() {
		return defaultRootCategoryId;
	}

	/**
	 * @param defaultRootCategoryId
	 *            the defaultRootCategoryId to set
	 */
	public void setDefaultRootCategoryId(Integer defaultRootCategoryId) {
		this.defaultRootCategoryId = defaultRootCategoryId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiKey == null) ? 0 : apiKey.hashCode());
		result = prime * result + ((apiUser == null) ? 0 : apiUser.hashCode());
		result = prime
				* result
				+ ((defaultAttributeSetId == null) ? 0 : defaultAttributeSetId
						.hashCode());
		result = prime
				* result
				+ ((defaultRootCategoryId == null) ? 0 : defaultRootCategoryId
						.hashCode());
		result = prime * result
				+ ((remoteHost == null) ? 0 : remoteHost.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoapConfig other = (SoapConfig) obj;
		if (apiKey == null) {
			if (other.apiKey != null)
				return false;
		} else if (!apiKey.equals(other.apiKey))
			return false;
		if (apiUser == null) {
			if (other.apiUser != null)
				return false;
		} else if (!apiUser.equals(other.apiUser))
			return false;
		if (defaultAttributeSetId == null) {
			if (other.defaultAttributeSetId != null)
				return false;
		} else if (!defaultAttributeSetId.equals(other.defaultAttributeSetId))
			return false;
		if (defaultRootCategoryId == null) {
			if (other.defaultRootCategoryId != null)
				return false;
		} else if (!defaultRootCategoryId.equals(other.defaultRootCategoryId))
			return false;
		if (remoteHost == null) {
			if (other.remoteHost != null)
				return false;
		} else if (!remoteHost.equals(other.remoteHost))
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
		return "SoapConfig [apiUser=" + apiUser + ", apiKey=" + apiKey
				+ ", remoteHost=" + remoteHost + ", defaultAttributeSetId="
				+ defaultAttributeSetId + ", defaultRootCategoryId="
				+ defaultRootCategoryId + "]";
	}
}
