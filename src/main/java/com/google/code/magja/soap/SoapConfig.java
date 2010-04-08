package com.google.code.magja.soap;

import java.util.Properties;

public class SoapConfig {

	private static final String MAGENTO_API_PASSWORD = "magento-api-password";

	private static final String MAGENTO_API_URL = "magento-api-url";

	private static final String MAGENTO_API_USERNAME = "magento-api-username";

	public static final String DEFAULT_ATTRIBUTE_SET_ID = "default-attribute-set-id";

	private String apiUser;

	private String apiKey;

	private String remoteHost;

	private Integer defaultAttributeSetId;

	public SoapConfig(String apiUser, String apiKey, String remoteHost) {
		this.apiUser = apiUser;
		this.apiKey = apiKey;
		this.remoteHost = remoteHost;
	}

	public SoapConfig(Properties properties) {
		this.apiUser = properties.getProperty(MAGENTO_API_USERNAME);
		this.apiKey = properties.getProperty(MAGENTO_API_PASSWORD);
		this.remoteHost = properties.getProperty(MAGENTO_API_URL);
		this.defaultAttributeSetId = Integer.parseInt(properties.getProperty(DEFAULT_ATTRIBUTE_SET_ID));
	}

	/**
	 * @return the apiUser
	 */
	public String getApiUser() {
		return apiUser;
	}

	/**
	 * @param apiUser the apiUser to set
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
	 * @param apiKey the apiKey to set
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
	 * @param remoteHost the remoteHost to set
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
	 * @param defaultAttributeSetId the defaultAttributeSetId to set
	 */
	public void setDefaultAttributeSetId(Integer defaultAttributeSetId) {
		this.defaultAttributeSetId = defaultAttributeSetId;
	}
}
