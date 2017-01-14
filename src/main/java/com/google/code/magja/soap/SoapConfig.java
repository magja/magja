package com.google.code.magja.soap;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.BooleanUtils;

/**
 * Configuration of the SOAP Client.
 * 
 * @author Simon Zambrovski
 */
public class SoapConfig implements Serializable {

  public static final String MAGENTO_API_PASSWORD = "magento-api-password";
  public static final String MAGENTO_API_URL = "magento-api-url";
  public static final String MAGENTO_API_USERNAME = "magento-api-username";
  public static final String DEFAULT_ATTRIBUTE_SET_ID = "default-attribute-set-id";
  public static final String DEFAULT_ROOT_CATEGORY_ID = "default-root-category-id";

  public static final String HTTP_PROXY_HOST = "http-proxy-host";
  public static final String HTTP_PROXY_PORT = "http-proxy-port";
  public static final String HTTP_PROXY_USERNAME = "http-proxy-username";
  public static final String HTTP_PROXY_PASSWORD = "http-proxy-password";
  public static final String HTTP_PROXY_ENABLED = "http-proxy-enabled";
  public static final String HTTP_PROXY_AUTH_ENABLED = "http-proxy-auth-enabled";

  private static final long serialVersionUID = 1L;

  private String apiUser;
  private String apiKey;
  private String remoteHost;
  private Integer defaultAttributeSetId;
  private Integer defaultRootCategoryId;
  private boolean httpProxyEnabled;
  private String httpProxyHost;
  private Short httpProxyPort;
  private boolean httpProxyAuthEnabled;
  private String httpProxyPassword;
  private String httpProxyUsername;

  private HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();

  public SoapConfig() {

  }

  public SoapConfig(String apiUser, String apiKey, String remoteHost) {
    this.apiUser = apiUser;
    this.apiKey = apiKey;
    this.remoteHost = remoteHost;
    this.defaultAttributeSetId = 4;
    this.defaultRootCategoryId = 2;
  }

  public SoapConfig(final Properties properties) {
    this.apiUser = properties.getProperty(MAGENTO_API_USERNAME);
    this.apiKey = properties.getProperty(MAGENTO_API_PASSWORD);
    this.remoteHost = properties.getProperty(MAGENTO_API_URL);

    this.defaultAttributeSetId = Integer.parseInt(properties.getProperty(DEFAULT_ATTRIBUTE_SET_ID, "4"));
    this.defaultRootCategoryId = Integer.parseInt(properties.getProperty(DEFAULT_ROOT_CATEGORY_ID, "2"));

    this.httpProxyEnabled = BooleanUtils.toBoolean(properties.getProperty(HTTP_PROXY_ENABLED, "false"));
    this.httpProxyHost = properties.getProperty(HTTP_PROXY_HOST, "localhost");
    this.httpProxyPort = Short.parseShort(properties.getProperty(DEFAULT_ATTRIBUTE_SET_ID, "8080"));

    this.httpProxyAuthEnabled = BooleanUtils.toBoolean(properties.getProperty(HTTP_PROXY_AUTH_ENABLED, "false"));
    this.httpProxyUsername = properties.getProperty(HTTP_PROXY_USERNAME);
    this.httpProxyPassword = properties.getProperty(HTTP_PROXY_PASSWORD);

  }

  public HttpConnectionManagerParams getHttpConnectionManagerParams() {
    return httpConnectionManagerParams;
  }

  public void setHttpConnectionManagerParams(HttpConnectionManagerParams httpConnectionManagerParams) {
    this.httpConnectionManagerParams = httpConnectionManagerParams;
  }

  public void setDefaultMaxConnectionsPerHost(int maxConnectionsPerHost) {
    getHttpConnectionManagerParams().setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
  }

  public String getApiUser() {
    return apiUser;
  }

  public void setApiUser(String apiUser) {
    this.apiUser = apiUser;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getRemoteHost() {
    return remoteHost;
  }

  public void setRemoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
  }

  public Integer getDefaultAttributeSetId() {
    return defaultAttributeSetId;
  }

  public void setDefaultAttributeSetId(Integer defaultAttributeSetId) {
    this.defaultAttributeSetId = defaultAttributeSetId;
  }

  public Integer getDefaultRootCategoryId() {
    return defaultRootCategoryId;
  }

  public void setDefaultRootCategoryId(Integer defaultRootCategoryId) {
    this.defaultRootCategoryId = defaultRootCategoryId;
  }

  public boolean isHttpProxyEnabled() {
    return this.httpProxyEnabled;
  }

  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  public Short getHttpProxyPort() {
    return this.httpProxyPort;
  }

  public boolean isHttpProxyAuthEnabled() {
    return this.httpProxyAuthEnabled;
  }

  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  public void setHttpProxyEnabled(boolean httpProxyEnabled) {
    this.httpProxyEnabled = httpProxyEnabled;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  public void setHttpProxyAuthEnabled(boolean httpProxyAuthEnabled) {
    this.httpProxyAuthEnabled = httpProxyAuthEnabled;
  }

  public void setHttpProxyPort(Short httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((apiUser == null) ? 0 : apiUser.hashCode());
    result = prime * result + ((defaultAttributeSetId == null) ? 0 : defaultAttributeSetId.hashCode());
    result = prime * result + ((defaultRootCategoryId == null) ? 0 : defaultRootCategoryId.hashCode());
    result = prime * result + (httpProxyAuthEnabled ? 1231 : 1237);
    result = prime * result + (httpProxyEnabled ? 1231 : 1237);
    result = prime * result + ((httpProxyHost == null) ? 0 : httpProxyHost.hashCode());
    result = prime * result + ((httpProxyPort == null) ? 0 : httpProxyPort.hashCode());
    result = prime * result + ((httpProxyUsername == null) ? 0 : httpProxyUsername.hashCode());
    result = prime * result + ((remoteHost == null) ? 0 : remoteHost.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SoapConfig other = (SoapConfig) obj;
    if (apiUser == null) {
      if (other.apiUser != null) {
        return false;
      }
    } else if (!apiUser.equals(other.apiUser)) {
      return false;
    }
    if (defaultAttributeSetId == null) {
      if (other.defaultAttributeSetId != null) {
        return false;
      }
    } else if (!defaultAttributeSetId.equals(other.defaultAttributeSetId)) {
      return false;
    }
    if (defaultRootCategoryId == null) {
      if (other.defaultRootCategoryId != null) {
        return false;
      }
    } else if (!defaultRootCategoryId.equals(other.defaultRootCategoryId)) {
      return false;
    }
    if (httpProxyAuthEnabled != other.httpProxyAuthEnabled) {
      return false;
    }
    if (httpProxyEnabled != other.httpProxyEnabled) {
      return false;
    }
    if (httpProxyHost == null) {
      if (other.httpProxyHost != null) {
        return false;
      }
    } else if (!httpProxyHost.equals(other.httpProxyHost)) {
      return false;
    }
    if (httpProxyPort == null) {
      if (other.httpProxyPort != null) {
        return false;
      }
    } else if (!httpProxyPort.equals(other.httpProxyPort)) {
      return false;
    }
    if (httpProxyUsername == null) {
      if (other.httpProxyUsername != null) {
        return false;
      }
    } else if (!httpProxyUsername.equals(other.httpProxyUsername)) {
      return false;
    }
    if (remoteHost == null) {
      if (other.remoteHost != null) {
        return false;
      }
    } else if (!remoteHost.equals(other.remoteHost)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "SoapConfig [apiUser=" + apiUser + ", remoteHost=" + remoteHost + ", defaultAttributeSetId=" + defaultAttributeSetId + ", defaultRootCategoryId="
        + defaultRootCategoryId + ", httpProxyEnabled=" + httpProxyEnabled + ", httpProxyHost=" + httpProxyHost + ", httpProxyPort=" + httpProxyPort
        + ", httpProxyAuthEnabled=" + httpProxyAuthEnabled + ", httpProxyUsername=" + httpProxyUsername + "]";
  }

}
