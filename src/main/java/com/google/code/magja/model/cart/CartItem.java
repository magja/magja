package com.google.code.magja.model.cart;

import com.google.code.magja.model.BaseMagentoModel;

/**
 * @author schneider
 * 
 */
public class CartItem extends BaseMagentoModel {

	private static final long serialVersionUID = -1561918324133522580L;

	private Integer productId;

	private String sku;

	private Double quantity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the qtyOrdered
	 */
	public Double getQantity() {
		return quantity;
	}

	/**
	 * @param qtyOrdered
	 *            the qtyOrdered to set
	 */
	public void setQantity(Double qty) {
		this.quantity = qty;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash
				+ (this.productId != null ? this.productId.hashCode() : 0);
		hash = 59 * hash + (this.sku != null ? this.sku.hashCode() : 0);
		hash = 59 * hash
				+ (this.quantity != null ? this.quantity.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CartItem other = (CartItem) obj;
		if (this.productId != other.productId
				&& (this.productId == null || !this.productId
						.equals(other.productId))) {
			return false;
		}
		if ((this.sku == null) ? (other.sku != null) : !this.sku
				.equals(other.sku)) {
			return false;
		}
		if (this.quantity != other.quantity
				&& (this.quantity == null || !this.quantity
						.equals(other.quantity))) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", qantity=" + quantity
				+ ", sku=" + sku + "]";
	}

}
