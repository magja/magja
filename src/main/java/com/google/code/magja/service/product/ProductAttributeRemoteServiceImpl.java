/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.product.ProductAttribute;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import org.apache.axis2.AxisFault;

import java.util.*;

public class ProductAttributeRemoteServiceImpl extends
        GeneralServiceImpl<ProductAttribute> implements
        ProductAttributeRemoteService {

    private static final long serialVersionUID = -1575503087022556608L;

    /**
     * Create a object product attribute from the attributes map
     *
     * @param attributes
     * @return ProductAttribute
     */
    private ProductAttribute buildProductAttribute(
            Map<String, Object> attributes) {
        ProductAttribute pa = new ProductAttribute();

        for (Map.Entry<String, Object> attr : attributes.entrySet())
            pa.set(attr.getKey(), attr.getValue());

        return pa;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.product.ProductAttributeRemoteService#delete
      * (java.lang.String)
      */
    @Override
    public void delete(String attributeName) throws ServiceException {

        try {
            if (!(Boolean) soapClient.call(ResourcePath.ProductAttributeDelete,
                    attributeName))
                throw new ServiceException("Error deleting product attribute.");
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.service.product.ProductAttributeRemoteService#
      * getOptions (com.google.code.magja.model.product.ProductAttribute)
      */
    @Override
    public void getOptions(ProductAttribute productAttribute)
            throws ServiceException {

        if (productAttribute.getId() == null
                && productAttribute.getCode() == null)
            throw new ServiceException(
                    "The id or the code of the attribute must have some value.");

        List<Map<String, Object>> options;
        try {
            options = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.ProductAttributeOptions, (productAttribute
                    .getId() != null ? productAttribute.getId()
                    : productAttribute.getCode()));
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (options != null) {
            for (Map<String, Object> option : options) {
                if (!"".equals((String) option.get("label"))) {
                    if (productAttribute.getOptions() == null)
                        productAttribute
                                .setOptions(new HashMap<Integer, String>());
                    productAttribute.getOptions().put(
                            new Integer((String) option.get("value")),
                            (String) option.get("label"));
                }
            }
        }
    }

    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.service.product.ProductAttributeRemoteService#
      * listAllProductAttributeSet()
      */
    @Override
    public List<ProductAttributeSet> listAllProductAttributeSet()
            throws ServiceException {

        List<ProductAttributeSet> resultList = new ArrayList<ProductAttributeSet>();

        List<Map<String, Object>> attSetList;
        try {
            attSetList = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.ProductAttributeSetList, "");
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (attSetList == null)
            return resultList;

        for (Map<String, Object> att : attSetList) {
            ProductAttributeSet set = new ProductAttributeSet();
            for (Map.Entry<String, Object> attribute : att.entrySet())
                set.set(attribute.getKey(), attribute.getValue());
            resultList.add(set);
        }

        return resultList;
    }

    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.service.product.ProductAttributeRemoteService#
      * listByAttributeSet
      * (com.google.code.magja.model.product.ProductAttributeSet)
      */
    @Override
    public List<ProductAttribute> listByAttributeSet(ProductAttributeSet set)
            throws ServiceException {

        List<ProductAttribute> results = new ArrayList<ProductAttribute>();

        List<Map<String, Object>> prd_attributes = null;
        try {
            prd_attributes = (List<Map<String, Object>>) soapClient.call(
                    ResourcePath.ProductAttributeList, set.getId());
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (prd_attributes == null)
            return results;

        for (Map<String, Object> att : prd_attributes) {
            ProductAttribute prd_attribute = new ProductAttribute();
            for (Map.Entry<String, Object> attribute : att.entrySet()) {
                if (!attribute.getKey().equals("scope"))
                    prd_attribute.set(attribute.getKey(), attribute.getValue());
            }

            prd_attribute.setScope(ProductAttribute.Scope
                    .getByName((String) att.get("scope")));

            results.add(prd_attribute);
        }

        return results;
    }

    /*
      * (non-Javadoc)
      *
      * @see com.google.code.magja.service.product.ProductAttributeRemoteService#
      * listAllAttributes()
      */
    public List<ProductAttribute> listAllAttributes() throws ServiceException {
        List<ProductAttribute> allProductAttributes = new ArrayList<ProductAttribute>();

        try {
            List<ProductAttributeSet> allProductAttributeSets = listAllProductAttributeSet();
            for (ProductAttributeSet productAttributeSet : allProductAttributeSets) {
                List<ProductAttribute> listProductAttributes = listByAttributeSet(productAttributeSet);
                for (ProductAttribute productAttribute : listProductAttributes) {
                    allProductAttributes.add(productAttribute);
                }
            }
        } catch (ServiceException e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        return allProductAttributes;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.service.product.ProductAttributeRemoteService#create(
      * com.google.code.magja.model.product.ProductAttribute)
      */
    @Override
    public void save(ProductAttribute productAttribute) throws ServiceException {
        if (productAttribute.getId() != null
                || exists(productAttribute.getCode()))
            throw new ServiceException(
                    productAttribute.getCode()
                            + " exists already. Not allowed to update product attributes yet");

        Integer id = null;
        try {
            id = Integer.parseInt((String) soapClient.call(
                    ResourcePath.ProductAttributeCreate,
                    productAttribute.serializeToApi()));
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (id == null)
            throw new ServiceException("Error on create product attribute");

        productAttribute.setId(id);

        // if has options, include this too
        if (productAttribute.getOptions() != null) {
            if (!productAttribute.getOptions().isEmpty()) {
                String[] options = new String[productAttribute.getOptions()
                        .size()];
                int i = 0;
                for (Map.Entry<Integer, String> option : productAttribute
                        .getOptions().entrySet())
                    options[i++] = option.getValue();

                List<Object> params = new LinkedList<Object>();
                params.add(productAttribute.getId());
                params.add(options);

                try {
                    if (!(Boolean) soapClient.call(
                            ResourcePath.ProductAttributeAddOptions, params))
                        throw new ServiceException(
                                "The product attribute was saved, but had error "
                                        + "on create the options for that");
                } catch (AxisFault e) {
                    if (debug)
                        e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                }
            }
        }
    }

    /*
      * (non-Javadoc)
      *
      * @seecom.google.code.magja.service.product.ProductAttributeRemoteService#
      * addOptions( com.google.code.magja.model.product.ProductAttribute ,
      * Map<Integer, String>)
      */
    @Override
    public void saveOptions(ProductAttribute productAttribute,
                            Map<Integer, String> productAttributeOptions)
            throws ServiceException {
        // if has options, include this too
        if (productAttributeOptions != null) {
            if (!productAttributeOptions.isEmpty()) {
                String[] options = new String[productAttributeOptions.size()];
                int i = 0;
                for (Map.Entry<Integer, String> option : productAttributeOptions
                        .entrySet())
                    options[i++] = option.getValue();

                List<Object> params = new LinkedList<Object>();
                params.add(productAttribute.getId());
                params.add(options);

                try {
                    if (!(Boolean) soapClient.call(
                            ResourcePath.ProductAttributeAddOptions, params))
                        throw new ServiceException(
                                "The product attribute was saved, but had error "
                                        + "on create the options for that");
                } catch (AxisFault e) {
                    if (debug)
                        e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                }
            }
        }
    }

    @Override
    public Integer AddOptions(String code,
                              String option)
            throws ServiceException {
        // if has options, include this too

        List<Object> params = new LinkedList<Object>();
        params.add(code);
        params.add(option);

        try {
            LinkedList list = (LinkedList) soapClient.call(
                    ResourcePath.ProductAttributeAdd, params);
            if (list.size() > 0) {
                Map id = (Map) list.get(0);
                if (id != null) {
                    return new Integer((String) id.get("id"));
                }
            }
        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        return null;
    }


    /*
      * (non-Javadoc)
      *
      * @seecom.google.code.magja.service.product.ProductAttributeRemoteService#
      * exists(String)
      */
    public boolean exists(String attributeName) throws ServiceException {
        List<ProductAttribute> allProductAttributes = listAllAttributes();

        for (ProductAttribute productAttribute : allProductAttributes) {
            if (productAttribute.getCode().equals(attributeName)) {
                return true;
            }
        }

        return false;
    }

    /*
      * (non-Javadoc)
      *
      * @seecom.google.code.magja.service.product.ProductAttributeRemoteService#
      * getByCode(String)
      */
    public ProductAttribute getByCode(String code) throws ServiceException {

        Map<String, Object> remote_result = null;
        try {
            remote_result = (Map<String, Object>) soapClient.call(
                    ResourcePath.ProductAttributeInfo, code);

        } catch (AxisFault e) {
            if (debug)
                e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (remote_result == null)
            return null;
        else
            return buildProductAttribute(remote_result);
    }

    /*
      * (non-Javadoc)
      *
      * @seecom.google.code.magja.service.product.ProductAttributeRemoteService#
      * addOption(ProductAttribute, String)
      */
    public void addOption(ProductAttribute productAttribute, String option)
            throws ServiceException {
        // create ProductAttribute option
        Map<Integer, String> productAttributeOption = new HashMap<Integer, String>();
        productAttributeOption.put(0, option);

        // add options to ProductAttribute
        productAttribute.setOptions(productAttributeOption);

        // create ProductAttribute
        saveOptions(productAttribute, productAttributeOption);
    }
}
