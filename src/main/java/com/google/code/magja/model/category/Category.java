package com.google.code.magja.model.category;

import com.google.code.magja.model.BaseMagentoModel;

import java.security.InvalidParameterException;
import java.util.*;

public class Category extends BaseMagentoModel {

    private static final long serialVersionUID = -3521812333331235180L;

    private String name;

    private String availableSortBy;

    private String defaultSortBy;

    private String description;

    private Boolean active = false;

    private String metaTitle;

    private String metaDescription;

    private String metaKeywords;

    private Integer position;

    private Integer level;

    private Boolean anchor = false;

    private Boolean includeInMenu = false;

    private Category parent;

    private List<Category> children;


    protected void loadMappings() {
        this.mapping = new Properties();
        mapping.setProperty("category_id", "id");
        mapping.setProperty("name", "name");
        mapping.setProperty("available_sort_by", "availableSortBy");
        mapping.setProperty("default_sort_by", "defaultSortBy");
        mapping.setProperty("description", "description");
        mapping.setProperty("is_active", "active");
        mapping.setProperty("meta_keywords", "metaKeywords");
        mapping.setProperty("meta_description", "metaDescription");
        mapping.setProperty("position", "position");
        mapping.setProperty("is_anchor", "anchor");
        mapping.setProperty("include_in_menu", "includeInMenu");
        mapping.setProperty("meta_title", "metaTitle");
        mapping.setProperty("level", "level");
    }

    /*
      * create empty category
      */
    public Category() {
        super();
    }

    /*
      * create category with id
      */
    public Category(Integer id) {
        super();

        setId(id);
    }

    /*
      * create category with name
      */
    public Category(String name) {
        super();

        setName(name);
    }

    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
      */
    @Override
    public Object serializeToApi() {
        List<Object> newCategory = new LinkedList<Object>();
        if (getId() == null) {
            // means its a new category
            newCategory.add(getParent().getId());
        } else {
            // means its a old category
            newCategory.add(getId());
        }
        Map<String, Object> allProperties = getAllProperties();
        /*
           * FIXME: Workaround for Magento API available_sort_by bug For more info
           * see documentation from setAvailableSortBy()
           */
/*
		if (getAvailableSortBy().length == 0) {
			allProperties.put("available_sort_by", "");
		}
*/
        newCategory.add(allProperties);

        return newCategory;
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
     * @param availableSortBy
     *            the availableSortBy to set
     *
     *            ATTENTION: Run this SQL query first to fix the
     *            available_sort_by bug (tested with Magento 1.4)
     *            <code>update eav_attribute set is_required = 0 where attribute_code = 'available_sort_by';</code>
     *
     *            See:
     *            http://www.magentocommerce.com/bug-tracking/issue?issue=6842
     *            http
     *            ://www.magentocommerce.com/boards/viewthread/48088/#t198697
     *
     */
/*
	public void setAvailableSortBy(String[] availableSortBy) {
		this.availableSortBy = availableSortBy;
	}
*/

    /**
     * @param availableSortBy in string mode, because magento doens't return in a array mode
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
     * @return the metaTitle
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * @param metaTitle the metaTitle to set
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
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
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     * @return the includeInMenu
     */
    public Boolean getIncludeInMenu() {
        return includeInMenu;
    }

    /**
     * @param includeInMenu the includeInMenu to set
     */
    public void setIncludeInMenu(Boolean includeInMenu) {
        this.includeInMenu = includeInMenu;
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
        if (children != null) {
            return children;
        } else {
            return new ArrayList<Category>();
        }
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
        if (child == null)
            throw new InvalidParameterException("the child cannot be null");
        if (children == null)
            children = new ArrayList<Category>();
        child.setParent(this);
        children.add(child);
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
        result = prime * result
                + ((includeInMenu == null) ? 0 : includeInMenu.hashCode());
        result = prime * result
                + ((availableSortBy == null) ? 0 : availableSortBy.hashCode());
        result = prime * result
                + ((children == null) ? 0 : children.hashCode());
        result = prime * result
                + ((defaultSortBy == null) ? 0 : defaultSortBy.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result
                + ((metaDescription == null) ? 0 : metaDescription.hashCode());
        result = prime * result
                + ((metaKeywords == null) ? 0 : metaKeywords.hashCode());
        result = prime * result
                + ((metaTitle == null) ? 0 : metaTitle.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
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
        if (includeInMenu == null) {
            if (other.includeInMenu != null)
                return false;
        } else if (!includeInMenu.equals(other.includeInMenu))
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
        if (level == null) {
            if (other.level != null)
                return false;
        } else if (!level.equals(other.level))
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
        if (metaTitle == null) {
            if (other.metaTitle != null)
                return false;
        } else if (!metaTitle.equals(other.metaTitle))
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

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "Category [active=" + active + ", anchor=" + anchor
                + ", includeInMenu=" + includeInMenu + ", availableSortBy="
                + availableSortBy + ", defaultSortBy=" + defaultSortBy
                + ", description=" + description + ", level=" + level
                + ", metaDescription=" + metaDescription + ", metaKeywords="
                + metaKeywords + ", metaTitle=" + metaTitle + ", name=" + name
                + ", parent=" + parent + ", position=" + position + ", id="
                + id + ", properties=" + properties + "]";
    }


}
