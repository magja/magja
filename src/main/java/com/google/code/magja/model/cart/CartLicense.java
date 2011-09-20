package com.google.code.magja.model.cart;

import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;

/**
 * @author schneider
 * 
 */
public class CartLicense extends BaseMagentoModel {

	private static final long serialVersionUID = 4626944484751451321L;

	private String agreementId;

	private String name;

	private String content;

	private Integer isActive;

	private Integer isHtml;

	public static CartLicense fromAttributes(Map<String, Object> attrs) {
		CartLicense license = new CartLicense();
		for (Map.Entry<String, Object> attr : attrs.entrySet()) {
			license.set(attr.getKey(), attr.getValue());
		}

		return license;
	}

	@Override
	public Object serializeToApi() {
		return null;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsHtml() {
		return isHtml;
	}

	public void setIsHtml(Integer isHtml) {
		this.isHtml = isHtml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CartLicense [" + "agreementId=" + agreementId + ", name="
				+ name + ", content=" + content + ", isActive=" + isActive
				+ ", isHtml=" + isHtml + ']';
	}

}
