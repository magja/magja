package com.google.code.magja.model.product;

import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;

/**
 * Product tier price.
 */
public class ProductTierPrice extends BaseMagentoModel<Map<String, Object>> {

  public static final String WEBSITE_ALL = "all";
  public static final String CUSTOMER_GROUP_ALL = "all";

  private static final long serialVersionUID = 1L;

  private String customerGroupId = CUSTOMER_GROUP_ALL;
  private String website = WEBSITE_ALL;
  private Double quantity;
  private Double price;

  @Override
  public Map<String, Object> serializeToApi() {
    Map<String, Object> props = getAllProperties();
    return props;
  }

  public String getCustomerGroupId() {
    return customerGroupId;
  }

  public void setCustomerGroupId(String customerGroupId) {
    this.customerGroupId = customerGroupId;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((customerGroupId == null) ? 0 : customerGroupId.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
    result = prime * result + ((website == null) ? 0 : website.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ProductTierPrice other = (ProductTierPrice) obj;
    if (customerGroupId == null) {
      if (other.customerGroupId != null) {
        return false;
      }
    } else if (!customerGroupId.equals(other.customerGroupId)) {
      return false;
    }
    if (price == null) {
      if (other.price != null) {
        return false;
      }
    } else if (!price.equals(other.price)) {
      return false;
    }
    if (quantity == null) {
      if (other.quantity != null) {
        return false;
      }
    } else if (!quantity.equals(other.quantity)) {
      return false;
    }
    if (website == null) {
      if (other.website != null) {
        return false;
      }
    } else if (!website.equals(other.website)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "ProductTierPrice [customerGroupId=" + customerGroupId + ", website=" + website + ", quantity=" + quantity + ", price=" + price + "]";
  }
}
