package com.google.code.magja.service.product;

import java.util.List;
import java.util.Map;

import com.google.code.magja.model.product.ProductAttribute;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface ProductAttributeRemoteService extends GeneralService<ProductAttribute> {

  /**
   * Delete a product attribute with the name specified
   *
   * @param attributeName
   * @throws ServiceException
   */
  void delete(String attributeName) throws ServiceException;

  /**
   * Populate the options attribute of a ProductAttribute specified, the id or
   * the code of that ProductAttribute must not be null.
   *
   * @param productAttribute
   * @throws ServiceException
   */
  void getOptions(ProductAttribute productAttribute) throws ServiceException;

  /**
   * @return List of all ProductAttributeSet from magento api
   * @throws ServiceException
   */
  List<ProductAttributeSet> listAllProductAttributeSet() throws ServiceException;

  /**
   * List all product attributes of a attribute set from magento
   *
   * @param set
   *          - the attribute set
   * @return List<ProductAttribute>
   * @throws ServiceException
   */
  List<ProductAttribute> listByAttributeSet(ProductAttributeSet set) throws ServiceException;

  /**
   * List all product attributes of a attribute set from magento
   *
   * @param setId
   *          the attribute set ID
   * @return List<ProductAttribute>
   * @throws ServiceException
   */
  List<ProductAttribute> listByAttributeSet(Integer setId) throws ServiceException;

  /**
   * List all product attributes from all attribute sets from magento
   *
   * @throws ServiceException
   */
  List<ProductAttribute> listAllAttributes() throws ServiceException;

  /**
   * Save a new Product Attribute to the magento api
   *
   * @param productAttribute
   * @throws ServiceException
   */
  void save(ProductAttribute productAttribute) throws ServiceException;

  /**
   * Save new options for Product Attribute
   *
   * @param productAttribute
   *          a <code>ProductAttribute</code> instance
   * @param productAttributeOptions
   *          a <code>Map<Integer, String></code> instance
   * @throws ServiceException
   */
  void saveOptions(ProductAttribute productAttribute, Map<Integer, String> productAttributeOptions) throws ServiceException;

  /**
   * Save new options for Product Attribute
   *
   * @param attributeName
   *          a <code>ProductAttribute</code> code
   * @throws ServiceException
   */
  boolean exists(String attributeName) throws ServiceException;

  Integer AddOptions(String code, String option) throws ServiceException;

  /**
   * get Product Attribute by code
   *
   * @param code
   *          a <code>ProductAttribute</code> code
   * @throws ServiceException
   */
  ProductAttribute getByCode(String code) throws ServiceException;

  /**
   * add option to Product Attribute
   *
   * @param productAttribute
   *          a <code>ProductAttribute</code>
   * @param option
   *          a <code>String</code>
   * @throws ServiceException
   */
  void addOption(ProductAttribute productAttribute, String option) throws ServiceException;
}
