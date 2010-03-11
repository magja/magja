/**
 *
 */
package code.google.magja.model.product;

import java.awt.Image;
import java.util.Map;
import java.util.Set;

import code.google.magja.model.BaseMagentoModel;
import code.google.magja.utils.PropertyLoader;

/**
 * @author andre
 *
 */
public class ProductMedia extends BaseMagentoModel {

	private static final String MAPPING_FILE_NAME = "productMedia-mapping";

	public enum Type {
		IMAGE, SMALL_IMAGE, THUMBNAIL;

		public static Type getValueOfString(String type) {
			if(type.equals(IMAGE.toString().toLowerCase())) return IMAGE;
			else if(type.equals(SMALL_IMAGE.toString().toLowerCase())) return SMALL_IMAGE;
			else if(type.equals(THUMBNAIL.toString().toLowerCase())) return THUMBNAIL;
			else return null;
		}
	}

	private Product product;

	private String label;

	private Boolean exclude;

	private Integer position;

	private String file;

	private Set<Type> types;

	private String url;

	private Image image;

	public ProductMedia() {
		super();
		mapping = PropertyLoader.loadProperties(getClass().getPackage().getName() + "." + MAPPING_FILE_NAME);
	}

	/* (non-Javadoc)
	 * @see code.google.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {

		Map<String, Object> properties = getAllProperties();
		properties.remove("url");

		String[] str_types = new String[types.size()];
		int i = 0;
		for (Type type : types)
			str_types[i++] = type.toString().toLowerCase();

		System.out.println(str_types.toString());

		return null;
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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the exclude
	 */
	public Boolean getExclude() {
		return exclude;
	}

	/**
	 * @param exclude the exclude to set
	 */
	public void setExclude(Boolean exclude) {
		this.exclude = exclude;
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
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the types
	 */
	public Set<Type> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((exclude == null) ? 0 : exclude.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		ProductMedia other = (ProductMedia) obj;
		if (exclude == null) {
			if (other.exclude != null)
				return false;
		} else if (!exclude.equals(other.exclude))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductMedia [exclude=" + exclude + ", file=" + file
				+ ", label=" + label + ", position=" + position + ", types="
				+ types + ", url=" + url + "]";
	}

}
