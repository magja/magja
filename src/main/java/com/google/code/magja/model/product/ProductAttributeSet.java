/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import java.util.Properties;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.soap.SoapConfig;
import com.google.code.magja.utils.PropertyLoader;

public class ProductAttributeSet extends BaseMagentoModel {

    private static final long serialVersionUID = -3154289809263844919L;

    private String name;

    public ProductAttributeSet(Integer id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public ProductAttributeSet() {
        super();
    }

    public static ProductAttributeSet getDefaultProductAttributeSet() {
        Properties magentoapi = PropertyLoader.loadProperties(MagentoSoapClient.CONFIG_PROPERTIES_FILE);
        Integer defaultId = Integer.parseInt(magentoapi.getProperty(SoapConfig.DEFAULT_ATTRIBUTE_SET_ID));
        return new ProductAttributeSet(defaultId, "Default");
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

    /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ProductAttributeSet other = (ProductAttributeSet) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /* (non-Javadoc)
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "ProductAttributeSet [name=" + name + ", id=" + id + "]";
    }

    /* (non-Javadoc)
      * Its ready only, we never will create a attributeSet to magento
      * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
      */
    @Override
    public Object serializeToApi() {
        return null;
    }

}
