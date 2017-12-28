package net.magja.model.product;

import net.magja.model.BaseMagentoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration data holding information of which configuration objects are selected on a child products
 * as selected option to become a variant of a configurable product.
 *
 * @author andre
 * @author Simon Zambrovski
 */
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
      for (ConfigurableData configurableData : data) {
        result.put(i.toString(), configurableData.serializeToApi());
        i++;
      }
      return result;
    }

    return null;
  }

  /**
   * Create a simple product with default properties from super product and add
   * the specified options.
   *
   * @param parentProduct
   * @param qty
   * @param weight
   * @throws ConfigurableDataException
   */
  public void createNewSubProduct(Product parentProduct, Double qty, Double weight, String sku) throws ConfigurableDataException {

    if (data == null) {
      throw new ConfigurableDataException("You have to put some ConfigurableData first");
    }
    if (data.isEmpty()) {
      throw new ConfigurableDataException("You have to put some ConfigurableData first");
    }
    if (parentProduct.getConfigurableAttributesData() == null) {
      throw new ConfigurableDataException("You have to put some ConfigurableAttributesData in your super product first");
    }

    StringBuffer sufix = new StringBuffer("");

    // get the options to use on SKU and product name
    for (ConfigurableData cnfdata : data) {
      sufix.append("-" + cnfdata.getLabel());
    }

    product = new Product();
    product.setAttributeSet(parentProduct.getAttributeSet());
    product.setName(parentProduct.getName() + sufix.toString());
    product.setShortDescription(parentProduct.getShortDescription());
    product.setDescription(parentProduct.getDescription());
    product.setPrice(parentProduct.getPrice());
    product.setCost(parentProduct.getCost());
    product.setEnabled(parentProduct.getEnabled());
    product.setWeight(weight);
    product.setSku(sku);
    product.setTaxClassId(parentProduct.getTaxClassId());

    // default visibility for sub-product its not visible individually
    product.setVisibility(Visibility.NOT_VISIBLE_INDIVIDUALLY);

    // inventory
    product.setQty(qty);
    if (qty > 0) {
      product.setInStock(true);
    } else {
      product.setInStock(false);
    }

    // only simple products
    product.setType(ProductType.SIMPLE);

    applyConfigurationAttributesToProduct(parentProduct);
  }

  /**
   * Applies the configurable attributes from the parent product and provides the selected option in the child product.
   *
   * @param parent parent product with configured configurable attribute data.
   * @throws ConfigurableDataException on configuration errors.
   */
  public Product applyConfigurationAttributesToProduct(final Product parent) throws ConfigurableDataException {
    if (parent.getConfigurableAttributesData() == null || parent.getConfigurableAttributesData().isEmpty()) {
      throw new ConfigurableDataException("Parent product should have configurable data.");
    }

    // iterate over configuration data of the child (selected option)
    // required attributes are attribute id, attribute code and value index.
    for (final ConfigurableData childData : data) {

      ConfigurableAttributeData configurationAttributeData = null;

      // find the attribute code on the ConfigurableAttributeData from parent product
      for (ConfigurableAttributeData parentAttributeData : parent.getConfigurableAttributesData()) {
        if (parentAttributeData.getAttributeId().equals(childData.getAttributeId())) {
          // attribute found, assign the configuration data.
          configurationAttributeData = parentAttributeData;
          break;
        }
      }

      if (configurationAttributeData != null) {
        product.set(configurationAttributeData.getAttributeCode(), childData.getValueIndex());
      }
    }

    return product;
  }

  public Product getExistingProduct() {
    return existingProduct;
  }

  public void setExistingProduct(Product existingProduct) {
    this.existingProduct = existingProduct;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((data == null) ? 0 : data.hashCode());
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    return result;
  }

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

  @Override
  public String toString() {
    return "ConfigurableProductsData [product=" + product + ", data=" + data + "]";
  }

}
