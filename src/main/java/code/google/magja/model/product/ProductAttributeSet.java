/**
 *
 */
package code.google.magja.model.product;

import code.google.magja.model.BaseMagentoModel;
import code.google.magja.utils.PropertyLoader;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class ProductAttributeSet extends BaseMagentoModel {

	private static final String MAPPING_FILE_NAME = "productAttributeSet-mapping";

	private String name;

	public ProductAttributeSet() {
		super();
		mapping = PropertyLoader.loadProperties(getClass().getPackage().getName() + "." + MAPPING_FILE_NAME);
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

}
