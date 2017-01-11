package com.google.code.magja.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.product.ProductAttribute;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
public class ProductAttributeRemoteServiceImpl extends GeneralServiceImpl<ProductAttribute> implements ProductAttributeRemoteService {

  private static final long serialVersionUID = -1575503087022556608L;

  public ProductAttributeRemoteServiceImpl(MagentoSoapClient soapClient) {
    super(soapClient);
  }

  /**
   * Create a object product attribute from the attributes map
   *
   * @param attributes
   * @return ProductAttribute
   */
  private ProductAttribute buildProductAttribute(Map<String, Object> attributes) {
    ProductAttribute pa = new ProductAttribute();

    for (Map.Entry<String, Object> attr : attributes.entrySet())
      pa.set(attr.getKey(), attr.getValue());

    return pa;
  }

  @Override
  public void delete(String attributeName) throws ServiceException {

    try {
      if (!(Boolean) soapClient.callSingle(ResourcePath.ProductAttributeDelete, attributeName))
        throw new ServiceException("Error deleting product attribute.");
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

  }

  @Override
  public void getOptions(ProductAttribute productAttribute) throws ServiceException {

    if (productAttribute.getId() == null && productAttribute.getCode() == null)
      throw new ServiceException("The id or the code of the attribute must have some value.");

    List<Map<String, Object>> options;
    try {
      options = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.ProductAttributeOptions,
          (productAttribute.getId() != null ? productAttribute.getId() : productAttribute.getCode()));
    } catch (AxisFault e) {
      if (debug)
        e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

    if (options != null) {
      for (Map<String, Object> option : options) {
        if (!"".equals((String) option.get("label"))) {
          if (productAttribute.getOptions() == null)
            productAttribute.setOptions(new HashMap<Integer, String>());
          productAttribute.getOptions().put(new Integer((String) option.get("value")), (String) option.get("label"));
        }
      }
    }
  }

  @Override
  public List<ProductAttributeSet> listAllProductAttributeSet() throws ServiceException {

    List<ProductAttributeSet> resultList = new ArrayList<ProductAttributeSet>();

    List<Map<String, Object>> attSetList;
    try {
      attSetList = (List<Map<String, Object>>) soapClient.callSingle(ResourcePath.ProductAttributeSetList, "");
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

  @Override
  public List<ProductAttribute> listByAttributeSet(Integer setId) throws ServiceException {

    List<ProductAttribute> results = new ArrayList<ProductAttribute>();

    List<Map<String, Object>> prd_attributes = null;
    try {
      prd_attributes = soapClient.callSingle(ResourcePath.ProductAttributeList, setId);
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

      prd_attribute.setScope(ProductAttribute.Scope.getByName((String) att.get("scope")));

      results.add(prd_attribute);
    }

    return results;
  }

  @Override
  public List<ProductAttribute> listByAttributeSet(ProductAttributeSet set) throws ServiceException {
    return listByAttributeSet(set.getId());
  }

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

  @Override
  public void save(ProductAttribute productAttribute) throws ServiceException {
    if (productAttribute.getId() != null || exists(productAttribute.getCode()))
      throw new ServiceException(productAttribute.getCode() + " exists already. Not allowed to update product attributes yet");

    Integer id = null;
    try {
      id = Integer.parseInt((String) soapClient.callSingle(ResourcePath.ProductAttributeCreate, productAttribute.serializeToApi()));
    } catch (AxisFault e) {
      if (debug) {
        e.printStackTrace();
      }
      throw new ServiceException(e.getMessage());
    }

    productAttribute.setId(id);

    // if has options, include this too
    if (productAttribute.getOptions() != null) {
      if (!productAttribute.getOptions().isEmpty()) {
        String[] options = new String[productAttribute.getOptions().size()];
        int i = 0;
        for (Map.Entry<Integer, String> option : productAttribute.getOptions().entrySet())
          options[i++] = option.getValue();

        // List<Object> params = new LinkedList<Object>();
        // params.add(productAttribute.getId());
        // params.add(options);

        try {
          if (!(Boolean) soapClient.callArgs(ResourcePath.ProductAttributeAddOptions, new Object[] { productAttribute.getId(), options }))
            throw new ServiceException("The product attribute was saved, but had error " + "on create the options for that");
        } catch (AxisFault e) {
          if (debug)
            e.printStackTrace();
          throw new ServiceException(e.getMessage());
        }
      }
    }
  }

  @Override
  public void saveOptions(ProductAttribute productAttribute, Map<Integer, String> productAttributeOptions) throws ServiceException {
    // if has options, include this too
    if (productAttributeOptions != null) {
      if (!productAttributeOptions.isEmpty()) {
        String[] options = new String[productAttributeOptions.size()];
        int i = 0;
        for (Map.Entry<Integer, String> option : productAttributeOptions.entrySet())
          options[i++] = option.getValue();

        // List<Object> params = new LinkedList<Object>();
        // params.add(productAttribute.getId());
        // params.add(options);

        try {
          if (!(Boolean) soapClient.callArgs(ResourcePath.ProductAttributeAddOptions, new Object[] { productAttribute.getId(), options }))
            throw new ServiceException("The product attribute was saved, but had error " + "on create the options for that");
        } catch (AxisFault e) {
          if (debug)
            e.printStackTrace();
          throw new ServiceException(e.getMessage());
        }
      }
    }
  }

  @Override
  public Integer AddOptions(String code, String option) throws ServiceException {
    // if has options, include this too

    // List<Object> params = new LinkedList<Object>();
    // params.add(code);
    // params.add(option);

    try {
      LinkedList list = (LinkedList) soapClient.callArgs(ResourcePath.ProductAttributeAdd, new Object[] { code, option });
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

  public boolean exists(String attributeName) throws ServiceException {
    List<ProductAttribute> allProductAttributes = listAllAttributes();

    for (ProductAttribute productAttribute : allProductAttributes) {
      if (productAttribute.getCode().equals(attributeName)) {
        return true;
      }
    }

    return false;
  }

  public ProductAttribute getByCode(String code) throws ServiceException {

    Map<String, Object> remote_result = null;
    try {
      remote_result = (Map<String, Object>) soapClient.callSingle(ResourcePath.ProductAttributeInfo, code);

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

  public void addOption(ProductAttribute productAttribute, String option) throws ServiceException {
    // create ProductAttribute option
    Map<Integer, String> productAttributeOption = new HashMap<Integer, String>();
    productAttributeOption.put(0, option);

    // add options to ProductAttribute
    productAttribute.setOptions(productAttributeOption);

    // create ProductAttribute
    saveOptions(productAttribute, productAttributeOption);
  }
}
