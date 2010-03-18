package code.google.magja.model.category;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import code.google.magja.model.BaseMagentoModel;
import code.google.magja.utils.PropertyLoader;

@SuppressWarnings("serial")
public class Category extends BaseMagentoModel {

	private static final String MAPPING_FILE_NAME = "category-mapping";

	private String name;

	private String availableSortBy;

	private String defaultSortBy;

	private String description;

	private Boolean active = false;

	private String metaDescription;

	private String metaKeywords;

	private Integer position;

	private Boolean anchor = false;

	private Category parent;

	private List<Category> children;

	public Category() {
		super();
		mapping = PropertyLoader.loadProperties(getClass().getPackage().getName() + "." + MAPPING_FILE_NAME);
	}

	/* (non-Javadoc)
	 * @see code.google.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * @return the availableSortBy
	 */
	public String getAvailableSortBy() {
		return availableSortBy;
	}

	/**
	 * @param availableSortBy the availableSortBy to set
	 */
	public void setAvailableSortBy(String availableSortBy) {
		this.availableSortBy = availableSortBy;
	}

	/**
	 * @return the defaultSortBy
	 */
	public String getDefaultSortBy() {
		return defaultSortBy;
	}

	/**
	 * @param defaultSortBy the defaultSortBy to set
	 */
	public void setDefaultSortBy(String defaultSortBy) {
		this.defaultSortBy = defaultSortBy;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the metaDescription
	 */
	public String getMetaDescription() {
		return metaDescription;
	}

	/**
	 * @param metaDescription the metaDescription to set
	 */
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	/**
	 * @return the metaKeywords
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * @param metaKeywords the metaKeywords to set
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
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
	 * @return the anchor
	 */
	public Boolean getAnchor() {
		return anchor;
	}

	/**
	 * @param anchor the anchor to set
	 */
	public void setAnchor(Boolean anchor) {
		this.anchor = anchor;
	}

	/**
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<Category> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Category> children) {
		this.children = children;
	}

	/**
	 * @param child the child to add
	 */
	public void addChild(Category child) {
		if(child == null) throw new InvalidParameterException("the child cannot be null");
		if(children == null) children = new ArrayList<Category>();
		child.setParent(this);
		children.add(child);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
		result = prime * result
				+ ((availableSortBy == null) ? 0 : availableSortBy.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result
				+ ((defaultSortBy == null) ? 0 : defaultSortBy.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((metaDescription == null) ? 0 : metaDescription.hashCode());
		result = prime * result
				+ ((metaKeywords == null) ? 0 : metaKeywords.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
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
		Category other = (Category) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (anchor == null) {
			if (other.anchor != null)
				return false;
		} else if (!anchor.equals(other.anchor))
			return false;
		if (availableSortBy == null) {
			if (other.availableSortBy != null)
				return false;
		} else if (!availableSortBy.equals(other.availableSortBy))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (defaultSortBy == null) {
			if (other.defaultSortBy != null)
				return false;
		} else if (!defaultSortBy.equals(other.defaultSortBy))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (metaDescription == null) {
			if (other.metaDescription != null)
				return false;
		} else if (!metaDescription.equals(other.metaDescription))
			return false;
		if (metaKeywords == null) {
			if (other.metaKeywords != null)
				return false;
		} else if (!metaKeywords.equals(other.metaKeywords))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [active=" + active + ", anchor=" + anchor
				+ ", availableSortBy=" + availableSortBy + ", children="
				+ children + ", defaultSortBy=" + defaultSortBy
				+ ", description=" + description + ", metaDescription="
				+ metaDescription + ", metaKeywords=" + metaKeywords
				+ ", name=" + name + ", parent=" + parent + ", position="
				+ position + ", id=" + id + ", properties=" + properties + "]";
	}

}
