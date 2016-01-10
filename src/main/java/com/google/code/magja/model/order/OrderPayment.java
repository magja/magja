/**
 * @author hartmut
 *
 */
package com.google.code.magja.model.order;

import com.google.code.magja.model.BaseMagentoModel;

public class OrderPayment extends BaseMagentoModel {

    private static final long serialVersionUID = -896486904788703676L;

    /* (non-Javadoc)
     * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
     */
    @Override
    public Object serializeToApi() {
        return null;
    }

    private Integer paymentId;

    private Integer parentId;

    private Double amountOrdered;

    private Double shippingAmount;

    private Double baseAmountOrdered;

    private Double baseShippingAmount;

    private String method;

    private Integer ccExpMonth;

    private Integer ccExpYear;

    private Integer ccSSStartMonth;

    private Integer ccSSStartYear;

    /**
     * @return the payment id
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the payment id
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Double getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(Double amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Double getBaseAmountOrdered() {
        return baseAmountOrdered;
    }

    public void setBaseAmountOrdered(Double baseAmountOrdered) {
        this.baseAmountOrdered = baseAmountOrdered;
    }

    public Double getBaseShippingAmount() {
        return baseShippingAmount;
    }

    public void setBaseShippingAmount(Double baseShippingAmount) {
        this.baseShippingAmount = baseShippingAmount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getCcExpMonth() {
        return ccExpMonth;
    }

    public void setCcExpMonth(Integer ccExpMonth) {
        this.ccExpMonth = ccExpMonth;
    }

    public Integer getCcExpYear() {
        return ccExpYear;
    }

    public void setCcExpYear(Integer ccExpYear) {
        this.ccExpYear = ccExpYear;
    }

    public Integer getCcSSStartMonth() {
        return ccSSStartMonth;
    }

    public void setCcSSStartMonth(Integer ccSSStartMonth) {
        this.ccSSStartMonth = ccSSStartMonth;
    }

    public Integer getCcSSStartYear() {
        return ccSSStartYear;
    }

    public void setCcSSStartYear(Integer ccSSStartYear) {
        this.ccSSStartYear = ccSSStartYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPayment)) return false;
        if (!super.equals(o)) return false;

        OrderPayment that = (OrderPayment) o;

        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (amountOrdered != null ? !amountOrdered.equals(that.amountOrdered) : that.amountOrdered != null)
            return false;
        if (shippingAmount != null ? !shippingAmount.equals(that.shippingAmount) : that.shippingAmount != null)
            return false;
        if (baseAmountOrdered != null ? !baseAmountOrdered.equals(that.baseAmountOrdered) : that.baseAmountOrdered != null)
            return false;
        if (baseShippingAmount != null ? !baseShippingAmount.equals(that.baseShippingAmount) : that.baseShippingAmount != null)
            return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (ccExpMonth != null ? !ccExpMonth.equals(that.ccExpMonth) : that.ccExpMonth != null) return false;
        if (ccExpYear != null ? !ccExpYear.equals(that.ccExpYear) : that.ccExpYear != null) return false;
        if (ccSSStartMonth != null ? !ccSSStartMonth.equals(that.ccSSStartMonth) : that.ccSSStartMonth != null)
            return false;
        return ccSSStartYear != null ? ccSSStartYear.equals(that.ccSSStartYear) : that.ccSSStartYear == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = prime * result + (parentId != null ? parentId.hashCode() : 0);
        result = prime * result + (amountOrdered != null ? amountOrdered.hashCode() : 0);
        result = prime * result + (shippingAmount != null ? shippingAmount.hashCode() : 0);
        result = prime * result + (baseAmountOrdered != null ? baseAmountOrdered.hashCode() : 0);
        result = prime * result + (baseShippingAmount != null ? baseShippingAmount.hashCode() : 0);
        result = prime * result + (method != null ? method.hashCode() : 0);
        result = prime * result + (ccExpMonth != null ? ccExpMonth.hashCode() : 0);
        result = prime * result + (ccExpYear != null ? ccExpYear.hashCode() : 0);
        result = prime * result + (ccSSStartMonth != null ? ccSSStartMonth.hashCode() : 0);
        result = prime * result + (ccSSStartYear != null ? ccSSStartYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderPayment{" +
                "paymentId=" + paymentId +
                ", parentId=" + parentId +
                ", amountOrdered=" + amountOrdered +
                ", shippingAmount=" + shippingAmount +
                ", baseAmountOrdered=" + baseAmountOrdered +
                ", baseShippingAmount=" + baseShippingAmount +
                ", method='" + method + '\'' +
                ", ccExpMonth=" + ccExpMonth +
                ", ccExpYear=" + ccExpYear +
                ", ccSSStartMonth=" + ccSSStartMonth +
                ", ccSSStartYear=" + ccSSStartYear +
                '}';
    }
}
