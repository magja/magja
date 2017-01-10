/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import java.util.Properties;

import com.google.code.magja.model.BaseMagentoModel;

public class ProductLink extends BaseMagentoModel {

    private static final long serialVersionUID = 189811375354364842L;

    public enum LinkType {
        RELATED, UP_SELL, CROSS_SELL, GROUPED;

        public static LinkType getValueOfString(String linkType) {
            if (linkType.equals(RELATED.toString().toLowerCase())) return RELATED;
            else if (linkType.equals(UP_SELL.toString().toLowerCase())) return UP_SELL;
            else if (linkType.equals(CROSS_SELL.toString().toLowerCase())) return CROSS_SELL;
            else if (linkType.equals(GROUPED.toString().toLowerCase())) return GROUPED;
            else return null;
        }
    }

    private LinkType linkType;

    private ProductType productType;

    private Integer setId;

    private String sku;

    private Integer position;

    private Double qty;


    protected void loadMappings() {
        this.mapping = new Properties();
        mapping.setProperty("product_id", "id");
        mapping.setProperty("set", "setId");
        mapping.setProperty("position", "position");
        mapping.setProperty("sku", "sku");
        mapping.setProperty("qty", "qty");
    }

    /* (non-Javadoc)
      * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
      */
    @Override
    public Object serializeToApi() {
        return null;
    }

    /**
     * @return the linkType
     */
    public LinkType getLinkType() {
        return linkType;
    }

    /**
     * @param linkType the linkType to set
     */
    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    /**
     * @return the productType
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    /**
     * @return the setId
     */
    public Integer getSetId() {
        return setId;
    }

    /**
     * @param setId the setId to set
     */
    public void setSetId(Integer setId) {
        this.setId = setId;
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
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the qty
     */
    public Double getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Double qty) {
        this.qty = qty;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((linkType == null) ? 0 : linkType.hashCode());
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
        result = prime * result
                + ((productType == null) ? 0 : productType.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((setId == null) ? 0 : setId.hashCode());
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
        ProductLink other = (ProductLink) obj;
        if (linkType == null) {
            if (other.linkType != null)
                return false;
        } else if (!linkType.equals(other.linkType))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (productType == null) {
            if (other.productType != null)
                return false;
        } else if (!productType.equals(other.productType))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (setId == null) {
            if (other.setId != null)
                return false;
        } else if (!setId.equals(other.setId))
            return false;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "ProductLink [linkType=" + linkType + ", position=" + position
                + ", productType=" + productType + ", qty=" + qty + ", setId="
                + setId + ", sku=" + sku + ", id=" + id + "]";
    }
}
