/**
 * @author andre
 *
 */
package net.magja.model.product;

import net.magja.model.BaseMagentoModel;
import net.magja.soap.MagentoSoapClient;
import net.magja.soap.SoapClient;

public class ProductAttributeSet extends BaseMagentoModel {

  private static final long serialVersionUID = -3154289809263844919L;

  private String name;

  public ProductAttributeSet(Integer id, String name) {
    super();
    super.setId(id);
    ;
    this.name = name;
  }

  public ProductAttributeSet() {
    super();
  }

  @Override
  public Object serializeToApi() {
    return null;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

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

  @Override
  public String toString() {
    return "ProductAttributeSet [name=" + name + ", id=" + id + "]";
  }

  /**
   * Retrieves a default product attribute set.
   *
   * @return attribute set.
   */
  public static ProductAttributeSet getDefaultProductAttributeSet() {
    final SoapClient soapClient = MagentoSoapClient.getInstance();
    Integer defaultId = soapClient.getConfig().getDefaultAttributeSetId();
    return new ProductAttributeSet(defaultId, "Default");
  }

}
