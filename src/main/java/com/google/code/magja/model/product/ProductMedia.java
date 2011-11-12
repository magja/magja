/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.media.Media;

import java.util.*;

public class ProductMedia extends BaseMagentoModel {

    private static final long serialVersionUID = -7705516482921672346L;

    public enum Type {
        IMAGE, SMALL_IMAGE, THUMBNAIL;

        public static Type getValueOfString(String type) {
            if (type.equals(IMAGE.toString().toLowerCase())) return IMAGE;
            else if (type.equals(SMALL_IMAGE.toString().toLowerCase())) return SMALL_IMAGE;
            else if (type.equals(THUMBNAIL.toString().toLowerCase())) return THUMBNAIL;
            else return null;
        }
    }

    private Boolean stillAssigned = Boolean.FALSE;

    private Product product;

    private String label;

    private Boolean exclude;

    private Integer position;

    private String file;

    private Set<Type> types;

    private String url;

    private Media image;

    protected void loadMappings() {
        this.mapping = new Properties();
        mapping.setProperty("exclude", "exclude");
        mapping.setProperty("label", "label");
        mapping.setProperty("position", "position");
        mapping.setProperty("file", "file");
        mapping.setProperty("url", "url");
    }


    /* (non-Javadoc)
      * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
      */
    @Override
    public Object serializeToApi() {

        Map<String, Object> props = getAllProperties();
        props.remove("url");

        String[] str_types = new String[types.size()];
        int i = 0;
        for (Type type : types)
            str_types[i++] = type.toString().toLowerCase();

        if (str_types.length > 0)
            props.put("types", str_types);

        props.put("file", image.serializeToApi());

        List<Object> newMedia = new LinkedList<Object>();
        newMedia.add(product.getSku());
        newMedia.add(props);

        return newMedia;
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
    public Media getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Media image) {
        this.image = image;
    }

    public Boolean getStillAssigned() {
        return stillAssigned;
    }

    public void setStillAssigned(Boolean stillAssigned) {
        this.stillAssigned = stillAssigned;
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
        result = prime * result + ((image == null) ? 0 : image.hashCode());
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
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
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
                + ", image=" + image + ", label=" + label + ", position="
                + position + ", types=" + types + ", url=" + url
                + ", properties=" + properties + "]";
    }
}
