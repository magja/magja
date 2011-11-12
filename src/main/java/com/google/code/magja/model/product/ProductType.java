/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

public class ProductType {

    private String name;
    private String code;

    public static ProductType SIMPLE = new ProductType("Simple Product", "simple");
    public static ProductType GROUPED = new ProductType("Grouped Product", "grouped");
    public static ProductType CONFIGURABLE = new ProductType("Configurable Product", "configurable");
    public static ProductType VIRTUAL = new ProductType("Virtual Product", "virtual");
    public static ProductType BUNDLE = new ProductType("Bundle Product", "bundle");
    public static ProductType DOWNLOADABLE = new ProductType("Downloadable Product", "downloadable");

    public static ProductType getType(String code) {
        if (code.equals(SIMPLE.getCode())) return SIMPLE;
        else if (code.equals(GROUPED.getCode())) return GROUPED;
        else if (code.equals(CONFIGURABLE.getCode())) return CONFIGURABLE;
        else if (code.equals(VIRTUAL.getCode())) return VIRTUAL;
        else if (code.equals(BUNDLE.getCode())) return BUNDLE;
        else if (code.equals(DOWNLOADABLE.getCode())) return DOWNLOADABLE;

        return SIMPLE;
    }

    public ProductType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductType)) return false;

        ProductType that = (ProductType) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
