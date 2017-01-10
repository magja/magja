/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;

public class ConfigurableProductData extends BaseMagentoModel {

    private static final long serialVersionUID = -4140349719003168632L;

    private Product product;

    private Product existingProduct;

    private List<ConfigurableData> data = new ArrayList<ConfigurableData>();

    @Override
    public Map<String, Object> serializeToApi() {

        if (data != null) {
            Integer i = 0;
            Map<String, Object> result = new HashMap<String, Object>();
            for (ConfigurableData conf : data) {
                result.put(i.toString(), conf.serializeToApi());
                i++;
            }
            return result;
        }

        return null;
    }

    /**
     * Create a simple product with default properties from super product and
     * add the specified options
     *
     * @param superprd
     * @param qty
     * @param weight
     * @throws ConfigurableDataException
     */
    public void configurateProduct(Product superprd, Double qty, Double weight, String sku)
            throws ConfigurableDataException {

        if (data == null)
            throw new ConfigurableDataException(
                    "You have to put some ConfigurableData first");
        if (data.isEmpty())
            throw new ConfigurableDataException(
                    "You have to put some ConfigurableData first");
        if (superprd.getConfigurableAttributesData() == null)
            throw new ConfigurableDataException(
                    "You have to put some ConfigurableAttributesData in your super product first");

        StringBuffer sufix = new StringBuffer("");

        // get the options to use on sku and product name
        for (ConfigurableData cnfdata : data)
            sufix.append("-" + cnfdata.getLabel());

        product = new Product();
        product.setAttributeSet(superprd.getAttributeSet());
        product.setName(superprd.getName() + sufix.toString());
        product.setShortDescription(superprd.getShortDescription());
        product.setDescription(superprd.getDescription());
        product.setPrice(superprd.getPrice());
        product.setCost(superprd.getCost());
        product.setEnabled(superprd.getEnabled());
        product.setWeight(weight);
        product.setSku(sku);
        product.setTaxClassId(superprd.getTaxClassId());

        // defaul visibility for subproduct its not visible individually
        product.setVisibility(Visibility.NOT_VISIBLE_INDIVIDUALLY);


        // inventory
        product.setQty(qty);
        if (qty > 0)
            product.setInStock(true);
        else
            product.setInStock(false);


        // only simple products
        product.setType(ProductType.SIMPLE);

        // set the attributes to the product
        for (ConfigurableData cnfdata : data) {

            ConfigurableAttributeData cnfAttData = null;

            // find the attribute code on the ConfigurableAttributeData from
            // super product
            for (ConfigurableAttributeData attrData : superprd
                    .getConfigurableAttributesData()) {
                if (attrData.getAttributeId().equals(cnfdata.getAttributeId())) {
                    cnfAttData = attrData;
                    break;
                }
            }

            if (cnfAttData != null)
                product.set(cnfAttData.getAttributeCode(),
                        cnfdata.getValueIndex());
        }
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the data
     */
    public List<ConfigurableData> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<ConfigurableData> data) {
        this.data = data;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#equals(java.lang.Object)
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConfigurableProductData other = (ConfigurableProductData) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "ConfigurableProductsData [product=" + product + ", data="
                + data + "]";
    }


    public Product getExistingProduct() {
        return existingProduct;
    }

    public void setExistingProduct(Product existingProduct) {
        this.existingProduct = existingProduct;
    }
}
