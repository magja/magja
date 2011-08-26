/**
 * @author andre
 *
 */
package com.google.code.magja.model.order;

import com.google.code.magja.model.BaseMagentoModel;

public class OrderItem extends BaseMagentoModel {

	private static final long serialVersionUID=-1561918324133522580L;

	private Integer parentItemId;

	private Integer productId;

	private String sku;

	private String name;

	private String description;

	private String productOptions;

	private Double weight;

	private Double qtyOrdered;

	private Double baseCost;

	private Double price;

	private Double basePrice;

	private Double originalPrice;

	private Double baseOriginalPrice;

	private Double rowTotal;

	private Double baseRowTotal;

	private Double rowInvoiced;

	private Double baseRowInvoiced;

	private Double rowWeight;

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/**
	 * @return the parentItemId
	 */
	public Integer getParentItemId() {
		return parentItemId;
	}

	/**
	 * @param parentItemId the parentItemId to set
	 */
	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
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
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the productOptions
	 */
	public String getProductOptions() {
		return productOptions;
	}

	/**
	 * @param productOptions the productOptions to set
	 */
	public void setProductOptions(String productOptions) {
		this.productOptions = productOptions;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the qtyOrdered
	 */
	public Double getQtyOrdered() {
		return qtyOrdered;
	}

	/**
	 * @param qtyOrdered the qtyOrdered to set
	 */
	public void setQtyOrdered(Double qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	/**
	 * @return the baseCost
	 */
	public Double getBaseCost() {
		return baseCost;
	}

	/**
	 * @param baseCost the baseCost to set
	 */
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return the originalPrice
	 */
	public Double getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * @return the baseOriginalPrice
	 */
	public Double getBaseOriginalPrice() {
		return baseOriginalPrice;
	}

	/**
	 * @param baseOriginalPrice the baseOriginalPrice to set
	 */
	public void setBaseOriginalPrice(Double baseOriginalPrice) {
		this.baseOriginalPrice = baseOriginalPrice;
	}

	/**
	 * @return the rowTotal
	 */
	public Double getRowTotal() {
		return rowTotal;
	}

	/**
	 * @param rowTotal the rowTotal to set
	 */
	public void setRowTotal(Double rowTotal) {
		this.rowTotal = rowTotal;
	}

	/**
	 * @return the baseRowTotal
	 */
	public Double getBaseRowTotal() {
		return baseRowTotal;
	}

	/**
	 * @param baseRowTotal the baseRowTotal to set
	 */
	public void setBaseRowTotal(Double baseRowTotal) {
		this.baseRowTotal = baseRowTotal;
	}

	/**
	 * @return the rowInvoiced
	 */
	public Double getRowInvoiced() {
		return rowInvoiced;
	}

	/**
	 * @param rowInvoiced the rowInvoiced to set
	 */
	public void setRowInvoiced(Double rowInvoiced) {
		this.rowInvoiced = rowInvoiced;
	}

	/**
	 * @return the baseRowInvoiced
	 */
	public Double getBaseRowInvoiced() {
		return baseRowInvoiced;
	}

	/**
	 * @param baseRowInvoiced the baseRowInvoiced to set
	 */
	public void setBaseRowInvoiced(Double baseRowInvoiced) {
		this.baseRowInvoiced = baseRowInvoiced;
	}

	/**
	 * @return the rowWeight
	 */
	public Double getRowWeight() {
		return rowWeight;
	}

	/**
	 * @param rowWeight the rowWeight to set
	 */
	public void setRowWeight(Double rowWeight) {
		this.rowWeight = rowWeight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((baseCost == null) ? 0 : baseCost.hashCode());
		result = prime
				* result
				+ ((baseOriginalPrice == null) ? 0 : baseOriginalPrice
						.hashCode());
		result = prime * result
				+ ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result
				+ ((baseRowInvoiced == null) ? 0 : baseRowInvoiced.hashCode());
		result = prime * result
				+ ((baseRowTotal == null) ? 0 : baseRowTotal.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((originalPrice == null) ? 0 : originalPrice.hashCode());
		result = prime * result
				+ ((parentItemId == null) ? 0 : parentItemId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((productOptions == null) ? 0 : productOptions.hashCode());
		result = prime * result
				+ ((qtyOrdered == null) ? 0 : qtyOrdered.hashCode());
		result = prime * result
				+ ((rowInvoiced == null) ? 0 : rowInvoiced.hashCode());
		result = prime * result
				+ ((rowTotal == null) ? 0 : rowTotal.hashCode());
		result = prime * result
				+ ((rowWeight == null) ? 0 : rowWeight.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (baseCost == null) {
			if (other.baseCost != null)
				return false;
		} else if (!baseCost.equals(other.baseCost))
			return false;
		if (baseOriginalPrice == null) {
			if (other.baseOriginalPrice != null)
				return false;
		} else if (!baseOriginalPrice.equals(other.baseOriginalPrice))
			return false;
		if (basePrice == null) {
			if (other.basePrice != null)
				return false;
		} else if (!basePrice.equals(other.basePrice))
			return false;
		if (baseRowInvoiced == null) {
			if (other.baseRowInvoiced != null)
				return false;
		} else if (!baseRowInvoiced.equals(other.baseRowInvoiced))
			return false;
		if (baseRowTotal == null) {
			if (other.baseRowTotal != null)
				return false;
		} else if (!baseRowTotal.equals(other.baseRowTotal))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (originalPrice == null) {
			if (other.originalPrice != null)
				return false;
		} else if (!originalPrice.equals(other.originalPrice))
			return false;
		if (parentItemId == null) {
			if (other.parentItemId != null)
				return false;
		} else if (!parentItemId.equals(other.parentItemId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productOptions == null) {
			if (other.productOptions != null)
				return false;
		} else if (!productOptions.equals(other.productOptions))
			return false;
		if (qtyOrdered == null) {
			if (other.qtyOrdered != null)
				return false;
		} else if (!qtyOrdered.equals(other.qtyOrdered))
			return false;
		if (rowInvoiced == null) {
			if (other.rowInvoiced != null)
				return false;
		} else if (!rowInvoiced.equals(other.rowInvoiced))
			return false;
		if (rowTotal == null) {
			if (other.rowTotal != null)
				return false;
		} else if (!rowTotal.equals(other.rowTotal))
			return false;
		if (rowWeight == null) {
			if (other.rowWeight != null)
				return false;
		} else if (!rowWeight.equals(other.rowWeight))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderItem [baseCost=" + baseCost + ", baseOriginalPrice="
				+ baseOriginalPrice + ", basePrice=" + basePrice
				+ ", baseRowInvoiced=" + baseRowInvoiced + ", baseRowTotal="
				+ baseRowTotal + ", description=" + description + ", name="
				+ name + ", originalPrice=" + originalPrice + ", parentItemId="
				+ parentItemId + ", price=" + price + ", productId="
				+ productId + ", productOptions=" + productOptions
				+ ", qtyOrdered=" + qtyOrdered + ", rowInvoiced=" + rowInvoiced
				+ ", rowTotal=" + rowTotal + ", rowWeight=" + rowWeight
				+ ", sku=" + sku + ", weight=" + weight + ", id=" + id
				+ ", properties=" + properties + "]";
	}
}
