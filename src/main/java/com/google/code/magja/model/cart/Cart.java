/*
 * Copyright 2011 Panticz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.magja.model.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.customer.Customer;

/**
 * @author schneider
 * 
 */
public class Cart extends BaseMagentoModel {

	private static final long serialVersionUID = 8055684083369213255L;

	private Integer storeId;

	private Customer customer = new Customer();

	private List<CartItem> items = new ArrayList<CartItem>();

	private List<CartLicense> licenseAgreements = new ArrayList<CartLicense>();

	private List<CartTotal> totals = new ArrayList<CartTotal>();

	private String createdAt;

	private String updatedAt;

	private String convertedAt;

	private Boolean isActive;

	private Boolean isVirtual;

	private Boolean isMultiShipping;

	private Boolean customerIsGuest;

	private CartAddress shippingAddress;

	private CartAddress billingaddress;

	public static Cart fromAttributes(Map<String, Object> attrs) {
		Cart c = new Cart();

		for (Map.Entry<String, Object> attr : attrs.entrySet()) {
			c.set(attr.getKey(), attr.getValue());
		}

		Customer customer = Customer.fromAttributes(attrs);
		if (customer != null) {
			c.setCustomer(customer);
		}
		CartAddress shippingAddress = CartAddress
				.fromAttributes((Map<String, Object>) attrs
						.get("shipping_address"));
		c.setShippingAddress(shippingAddress);
		CartAddress billingAddress = CartAddress
				.fromAttributes((Map<String, Object>) attrs
						.get("billing_address"));
		c.setBillingaddress(billingAddress);

		System.out.println(billingAddress);

		// TODO: payment

		return c;
	}

	@Override
	public Object serializeToApi() {
		return null;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setStoreId(String storeId) {
		try {
			setStoreId(Integer.parseInt(storeId));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartLicense> getLicenseAgreements() {
		return licenseAgreements;
	}

	public void setLicenseAgreements(List<CartLicense> licenseAgreements) {
		this.licenseAgreements = licenseAgreements;
	}

	public List<CartTotal> getTotals() {
		return totals;
	}

	public void setTotals(List<CartTotal> totals) {
		this.totals = totals;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CartAddress getBillingAddress() {
		return billingaddress;
	}

	public void setBillingaddress(CartAddress billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getConvertedAt() {
		return convertedAt;
	}

	public void setConvertedAt(String convertedAt) {
		this.convertedAt = convertedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsMultiShipping() {
		return isMultiShipping;
	}

	public void setIsMultiShipping(Boolean isMultiShipping) {
		this.isMultiShipping = isMultiShipping;
	}

	public Boolean getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public CartAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(CartAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Boolean getCustomerIsGuest() {
		return customerIsGuest;
	}

	public void setCustomerIsGuest(Boolean customerIsGuest) {
		this.customerIsGuest = customerIsGuest;
	}

	@Override
	public String toString() {
		return "Cart [" + "quoteId=" + getId() + ", storeId=" + storeId
				+ ", isActive=" + isActive + ", isVirtual=" + isVirtual
				+ ", customer=" + customer + ", customerIsGuest="
				+ customerIsGuest + ", shippingAddress=" + shippingAddress
				+ ", billingAddress=" + billingaddress + ", properties="
				+ properties + "]";

	}
}
