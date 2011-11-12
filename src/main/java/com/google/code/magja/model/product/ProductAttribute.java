/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import com.google.code.magja.model.BaseMagentoModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ProductAttribute extends BaseMagentoModel {

    private static final long serialVersionUID = 7015962673006863327L;

    public enum Scope {
        STORE(0), GLOBAL(1), WEBSITE(2);

        private final Integer value;

        private Scope(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static Scope getByName(String name) {
            if (name.equals("store"))
                return STORE;
            else if (name.equals("global"))
                return GLOBAL;
            else if (name.equals("website"))
                return WEBSITE;
            return null;
        }
    }

    private Scope scope = Scope.GLOBAL;

    private String code;

    private String group;

    private String type;

    private String backend;

    private String frontend;

    private String label;

    private String input;

    private String attributeClass;

    private String source;

    private String defaultValue;

    private Boolean visible;

    private Boolean required;

    private Boolean userDefined;

    private Boolean searchable;

    private Boolean filterable;

    private Boolean comparable;

    private Boolean visibleOnFront;

    private Boolean visibleInAdvancedSearch;

    private Boolean unique;

    private List<ProductType> applyTo;

    private Map<Integer, String> options;

    private Boolean sortBy;

    private Boolean configurable;


    protected void loadMappings() {
        this.mapping = new Properties();
        mapping.setProperty("attribute_id", "id");
        mapping.setProperty("type", "input");
        mapping.setProperty("backend", "backend");
        mapping.setProperty("frontend", "frontend");
        mapping.setProperty("label", "label");
        mapping.setProperty("class", "attributeClass");
        mapping.setProperty("source", "source");
        mapping.setProperty("default", "defaultValue");
        mapping.setProperty("visible", "visible");
        mapping.setProperty("required", "required");
        mapping.setProperty("user_defined", "userDefined");
        mapping.setProperty("searchable", "searchable");
        mapping.setProperty("filterable", "filterable");
        mapping.setProperty("comparable", "comparable");
        mapping.setProperty("visible_on_front", "visibleOnFront");
        mapping.setProperty("visible_in_advanced_search", "visibleInAdvancedSearch");
        mapping.setProperty("unique", "unique");
        mapping.setProperty("used_for_sort_by", "sortBy");
        mapping.setProperty("is_configurable", "configurable");
    }


    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
      */
    @Override
    public Object serializeToApi() {
        Map<String, Object> properties = getAllProperties();
        List<Object> list = new LinkedList<Object>();

        list.add(code);

        /*
           * When list the attributes, the type property its the input, and when
           * it create a attribute, the type property means varchar, int, etc, and
           * the input is text, textarea, select, etc. so, we have to work
           * workaround that here, thats because we didnt put the input mapping on
           * the mapping file
           */
        if (input != null)
            properties.put("input", input);
        if (type != null)
            properties.put("type", type);

        /*
           * we have to workaround the scope too, in the api the scope its the
           * 'global' property
           */
        properties.put("global", scope.ordinal());

        // not necessary
        properties.remove("code");
        properties.remove("attribute_id");

        // handle the applyTo
        if (applyTo != null) {
            if (!applyTo.isEmpty()) {
                String[] types = new String[applyTo.size()];
                int i = 0;
                for (ProductType type : applyTo)
                    types[i++] = type.getCode();
            }
        }

        // finally, add the properties to parameters to create
        list.add(properties);

        return list;
    }

    /**
     * @return the scope
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(Scope scope) {
        this.scope = scope;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the backend
     */
    public String getBackend() {
        return backend;
    }

    /**
     * @param backend the backend to set
     */
    public void setBackend(String backend) {
        this.backend = backend;
    }

    /**
     * @return the frontend
     */
    public String getFrontend() {
        return frontend;
    }

    /**
     * @param frontend the frontend to set
     */
    public void setFrontend(String frontend) {
        this.frontend = frontend;
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
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @return the attributeClass
     */
    public String getAttributeClass() {
        return attributeClass;
    }

    /**
     * @param attributeClass the attributeClass to set
     */
    public void setAttributeClass(String attributeClass) {
        this.attributeClass = attributeClass;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * @return the userDefined
     */
    public Boolean getUserDefined() {
        return userDefined;
    }

    /**
     * @param userDefined the userDefined to set
     */
    public void setUserDefined(Boolean userDefined) {
        this.userDefined = userDefined;
    }

    /**
     * @return the searchable
     */
    public Boolean getSearchable() {
        return searchable;
    }

    /**
     * @param searchable the searchable to set
     */
    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    /**
     * @return the filterable
     */
    public Boolean getFilterable() {
        return filterable;
    }

    /**
     * @param filterable the filterable to set
     */
    public void setFilterable(Boolean filterable) {
        this.filterable = filterable;
    }

    /**
     * @return the comparable
     */
    public Boolean getComparable() {
        return comparable;
    }

    /**
     * @param comparable the comparable to set
     */
    public void setComparable(Boolean comparable) {
        this.comparable = comparable;
    }

    /**
     * @return the visibleOnFront
     */
    public Boolean getVisibleOnFront() {
        return visibleOnFront;
    }

    /**
     * @param visibleOnFront the visibleOnFront to set
     */
    public void setVisibleOnFront(Boolean visibleOnFront) {
        this.visibleOnFront = visibleOnFront;
    }

    /**
     * @return the visibleInAdvancedSearch
     */
    public Boolean getVisibleInAdvancedSearch() {
        return visibleInAdvancedSearch;
    }

    /**
     * @param visibleInAdvancedSearch the visibleInAdvancedSearch to set
     */
    public void setVisibleInAdvancedSearch(Boolean visibleInAdvancedSearch) {
        this.visibleInAdvancedSearch = visibleInAdvancedSearch;
    }

    /**
     * @return the unique
     */
    public Boolean getUnique() {
        return unique;
    }

    /**
     * @param unique the unique to set
     */
    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    /**
     * @return the applyTo
     */
    public List<ProductType> getApplyTo() {
        return applyTo;
    }

    /**
     * @param applyTo the applyTo to set
     */
    public void setApplyTo(List<ProductType> applyTo) {
        this.applyTo = applyTo;
    }

    /**
     * @return the options
     */
    public Map<Integer, String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(Map<Integer, String> options) {
        this.options = options;
    }

    /**
     * @return the sortBy
     */
    public Boolean getSortBy() {
        return sortBy;
    }

    /**
     * @param sortBy Used for sorting in product listing
     */
    public void setSortBy(Boolean sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * @return the configurable
     */
    public Boolean getConfigurable() {
        return configurable;
    }

    /**
     * @param configurable the configurable to set
     */
    public void setConfigurable(Boolean configurable) {
        this.configurable = configurable;
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
        result = prime * result + ((applyTo == null) ? 0 : applyTo.hashCode());
        result = prime * result
                + ((attributeClass == null) ? 0 : attributeClass.hashCode());
        result = prime * result + ((backend == null) ? 0 : backend.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((comparable == null) ? 0 : comparable.hashCode());
        result = prime * result
                + ((configurable == null) ? 0 : configurable.hashCode());
        result = prime * result
                + ((defaultValue == null) ? 0 : defaultValue.hashCode());
        result = prime * result
                + ((filterable == null) ? 0 : filterable.hashCode());
        result = prime * result
                + ((frontend == null) ? 0 : frontend.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((input == null) ? 0 : input.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((options == null) ? 0 : options.hashCode());
        result = prime * result
                + ((required == null) ? 0 : required.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result
                + ((searchable == null) ? 0 : searchable.hashCode());
        result = prime * result + ((sortBy == null) ? 0 : sortBy.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((unique == null) ? 0 : unique.hashCode());
        result = prime * result
                + ((userDefined == null) ? 0 : userDefined.hashCode());
        result = prime * result + ((visible == null) ? 0 : visible.hashCode());
        result = prime
                * result
                + ((visibleInAdvancedSearch == null) ? 0
                : visibleInAdvancedSearch.hashCode());
        result = prime * result
                + ((visibleOnFront == null) ? 0 : visibleOnFront.hashCode());
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
        ProductAttribute other = (ProductAttribute) obj;
        if (applyTo == null) {
            if (other.applyTo != null)
                return false;
        } else if (!applyTo.equals(other.applyTo))
            return false;
        if (attributeClass == null) {
            if (other.attributeClass != null)
                return false;
        } else if (!attributeClass.equals(other.attributeClass))
            return false;
        if (backend == null) {
            if (other.backend != null)
                return false;
        } else if (!backend.equals(other.backend))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (comparable == null) {
            if (other.comparable != null)
                return false;
        } else if (!comparable.equals(other.comparable))
            return false;
        if (defaultValue == null) {
            if (other.defaultValue != null)
                return false;
        } else if (!defaultValue.equals(other.defaultValue))
            return false;
        if (filterable == null) {
            if (other.filterable != null)
                return false;
        } else if (!filterable.equals(other.filterable))
            return false;
        if (frontend == null) {
            if (other.frontend != null)
                return false;
        } else if (!frontend.equals(other.frontend))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (input == null) {
            if (other.input != null)
                return false;
        } else if (!input.equals(other.input))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (options == null) {
            if (other.options != null)
                return false;
        } else if (!options.equals(other.options))
            return false;
        if (required == null) {
            if (other.required != null)
                return false;
        } else if (!required.equals(other.required))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (searchable == null) {
            if (other.searchable != null)
                return false;
        } else if (!searchable.equals(other.searchable))
            return false;
        if (sortBy == null) {
            if (other.sortBy != null)
                return false;
        } else if (!sortBy.equals(other.sortBy))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (unique == null) {
            if (other.unique != null)
                return false;
        } else if (!unique.equals(other.unique))
            return false;
        if (userDefined == null) {
            if (other.userDefined != null)
                return false;
        } else if (!userDefined.equals(other.userDefined))
            return false;
        if (visible == null) {
            if (other.visible != null)
                return false;
        } else if (!visible.equals(other.visible))
            return false;
        if (visibleInAdvancedSearch == null) {
            if (other.visibleInAdvancedSearch != null)
                return false;
        } else if (!visibleInAdvancedSearch
                .equals(other.visibleInAdvancedSearch))
            return false;
        if (visibleOnFront == null) {
            if (other.visibleOnFront != null)
                return false;
        } else if (!visibleOnFront.equals(other.visibleOnFront))
            return false;
        if (configurable == null) {
            if (other.configurable != null)
                return false;
        } else if (!configurable.equals(other.configurable))
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
        return "ProductAttribute [applyTo=" + applyTo + ", attributeClass="
                + attributeClass + ", backend=" + backend + ", code=" + code
                + ", comparable=" + comparable + ", defaultValue="
                + defaultValue + ", filterable=" + filterable + ", frontend="
                + frontend + ", group=" + group + ", input=" + input
                + ", label=" + label + ", options=" + options + ", required="
                + required + ", scope=" + scope + ", searchable=" + searchable
                + ", sortBy=" + sortBy + ", source=" + source + ", type="
                + type + ", unique=" + unique + ", userDefined=" + userDefined
                + ", visible=" + visible + ", visibleInAdvancedSearch="
                + visibleInAdvancedSearch + ", visibleOnFront="
                + visibleOnFront + ", configurable=" + configurable + ", id="
                + id + ", properties=" + properties + "]";
    }
}
