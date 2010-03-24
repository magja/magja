/**
 *
 */
package com.google.code.magja.model.order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.code.magja.model.BaseMagentoModel;

/**
 * @author andre
 *
 */
public class Shipment extends BaseMagentoModel {

	private Integer customerId;

	private Integer orderId;

	private Double totalQty;

	private Integer shipmentId;

	private List<ShipmentItem> items = new ArrayList<ShipmentItem>();

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {

		List<Object> params = new LinkedList<Object>();

		// only create for now
		if(id == null) {

			params.add(orderId);

			List<Object> items_list = new LinkedList<Object>();
			for (ShipmentItem item : items)
				items_list.add(item.serializeToApi());

			params.add((!items_list.isEmpty() ? items_list : ""));
		}

		return params;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the totalQty
	 */
	public Double getTotalQty() {
		return totalQty;
	}

	/**
	 * @param totalQty the totalQty to set
	 */
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * @return the shipmentId
	 */
	public Integer getShipmentId() {
		return shipmentId;
	}

	/**
	 * @param shipmentId the shipmentId to set
	 */
	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}

	/**
	 * @return the items
	 */
	public List<ShipmentItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ShipmentItem> items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((shipmentId == null) ? 0 : shipmentId.hashCode());
		result = prime * result
				+ ((totalQty == null) ? 0 : totalQty.hashCode());
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
		Shipment other = (Shipment) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (shipmentId == null) {
			if (other.shipmentId != null)
				return false;
		} else if (!shipmentId.equals(other.shipmentId))
			return false;
		if (totalQty == null) {
			if (other.totalQty != null)
				return false;
		} else if (!totalQty.equals(other.totalQty))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Shipment [customerId=" + customerId + ", items=" + items
				+ ", orderId=" + orderId + ", shipmentId=" + shipmentId
				+ ", totalQty=" + totalQty + ", id=" + id + ", properties="
				+ properties + "]";
	}
}
