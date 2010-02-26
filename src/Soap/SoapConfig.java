package Soap;

public class SoapConfig {

	private String apiUser;
	private String apiKey;
	private String remoteHost;

	public SoapConfig(String apiUser, String apiKey, String remoteHost) {
		this.apiUser = apiUser;
		this.apiKey = apiKey;
		this.remoteHost = remoteHost;
	}

	public String getApiUser() {
		return this.apiUser;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public String getRemoteHost() {
		return this.remoteHost;
	}
}
