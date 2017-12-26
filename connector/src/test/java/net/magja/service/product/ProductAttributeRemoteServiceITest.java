package net.magja.service.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.product.ProductAttribute;
import net.magja.model.product.ProductAttributeSet;
import net.magja.model.product.ProductType;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;
import net.magja.utils.MagjaStringUtils;

/**
 * @author andre
 */
public class ProductAttributeRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ProductAttributeRemoteServiceITest.class);
  private ProductAttributeRemoteService service;
  private ProductAttributeSet defaultAttributeSet;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getProductAttributeRemoteService();
    defaultAttributeSet = ProductAttributeSet.getDefaultProductAttributeSet();
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#delete(java.lang.String)}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testDelete() throws ServiceException {
    // first, create a attribute to be deleted
    ProductAttribute pa = createTextAttributeSimple();
    service.save(pa);
    assertTrue(pa.getId() != null);

    // then, delete the pa
    service.delete(pa.getCode());
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#addOption(net.magja.model.product.ProductAttribute, java.lang.String)}
   * .
   */
  @Test
  public void testAddOption() {
    ProductAttribute pa = createSelectAttributeSimple();

    try {

      service.save(pa);

      service.addOption(pa, "Small");
      service.addOption(pa, "Medium");
      service.addOption(pa, "Big");

      assertTrue(pa.getId() != null);

      service.delete(pa.getCode());

    } catch (ServiceException e) {
      fail(e.getMessage());
    }

  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#getOptions(net.magja.model.product.ProductAttribute)}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testGetOptions() throws ServiceException {
    ProductAttribute pa = new ProductAttribute();
    pa.setCode("color");
    service.getOptions(pa);
    if (pa.getOptions() != null) {
      for (Map.Entry<Integer, String> opt : pa.getOptions().entrySet())
        System.out.println(opt.toString());
    }
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#listAllProductAttributeSet()}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testListAllProductAttributeSet() throws ServiceException {
    List<ProductAttributeSet> sets = service.listAllProductAttributeSet();
    assertNotNull(sets);
    log.info("Got {} attribute sets: {}", sets.size(), sets);
    assertTrue(sets.size() > 0);
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#listByAttributeSet(net.magja.model.product.ProductAttributeSet)}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testListByAttributeSet() throws ServiceException {
    List<ProductAttribute> results = service.listByAttributeSet(defaultAttributeSet);
    for (ProductAttribute productAttribute : results) {
      log.info("{}", productAttribute);
    }
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#listByAttributeSet(net.magja.model.product.ProductAttributeSet)}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testListByAttributeSet112() throws ServiceException {
    List<ProductAttribute> results = service.listByAttributeSet(112);
    for (ProductAttribute productAttribute : results) {
      log.info("{}", productAttribute);
    }
  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#save(net.magja.model.product.ProductAttribute)}
   * .
   *
   * @throws ServiceException
   */
  @Test
  public void testSave() throws ServiceException {

    ProductAttribute pa1 = createTextAttributeSimple();
    service.save(pa1);

    assertTrue(pa1.getId() != null);

    ProductAttribute pa2 = createSelectWithOptionsAndApplyToSpecified();
    service.save(pa2);

    assertTrue(pa2.getId() != null);

    // comment if you don't want to delete the created pa's
    service.delete(pa1.getCode());
    service.delete(pa2.getCode());

  }

  /**
   * Test method for
   * {@link net.magja.service.product.ProductAttributeRemoteServiceImpl#getByCode(java.lang.String)}
   * .
   */
  @Test
  public void testGetByCode() {
    try {
      ProductAttribute pa = service.getByCode("description");
      assertTrue(pa.getId() != null);

    } catch (ServiceException e) {
      fail(e.getMessage());
    }
  }

  private ProductAttribute createSelectAttributeSimple() {
    ProductAttribute attribute = createTextAttributeSimple();

    attribute.setType("int");
    attribute.setInput("select");

    return attribute;
  }

  private ProductAttribute createTextAttributeSimple() {

    ProductAttribute attribute = new ProductAttribute();

    attribute.setCode(MagjaStringUtils.randomString(3, 8).toUpperCase());
    attribute.setScope(ProductAttribute.Scope.STORE);
    attribute.setGroup("General");
    attribute.setType("varchar");
    attribute.setBackend("");
    attribute.setFrontend("");
    attribute.setLabel(MagjaStringUtils.randomString(3, 8).toUpperCase());
    attribute.setInput("text");
    attribute.setAttributeClass("");
    attribute.setSource("");
    attribute.setVisible(true);
    attribute.setRequired(false);
    attribute.setUserDefined(true);
    attribute.setDefaultValue("");
    attribute.setSearchable(true);
    attribute.setFilterable(true);
    attribute.setComparable(true);
    attribute.setVisibleOnFront(true);
    attribute.setVisibleInAdvancedSearch(true);
    attribute.setUnique(false);

    return attribute;
  }

  public ProductAttribute createSelectWithOptionsAndApplyToSpecified() {

    ProductAttribute attribute = new ProductAttribute();

    attribute.setCode(MagjaStringUtils.randomString(3, 8).toUpperCase());
    attribute.setScope(ProductAttribute.Scope.STORE);

    attribute.setGroup("General");
    attribute.setType("int");
    attribute.setBackend("");
    attribute.setFrontend("");
    attribute.setLabel(MagjaStringUtils.randomString(3, 8).toUpperCase());
    attribute.setInput("select");
    attribute.setAttributeClass("");
    attribute.setSource("");
    attribute.setVisible(true);
    attribute.setRequired(false);
    attribute.setUserDefined(true);
    attribute.setDefaultValue("");
    attribute.setSearchable(true);
    attribute.setFilterable(true);
    attribute.setComparable(true);
    attribute.setVisibleOnFront(false);
    attribute.setVisibleInAdvancedSearch(true);
    attribute.setUnique(false);

    attribute.setApplyTo(new ArrayList<ProductType>());
    attribute.getApplyTo().add(ProductType.SIMPLE);
    attribute.getApplyTo().add(ProductType.GROUPED);
    attribute.getApplyTo().add(ProductType.CONFIGURABLE);

    // create some options
    attribute.setOptions(new HashMap<Integer, String>());
    attribute.getOptions().put(1, MagjaStringUtils.randomString(3, 8));
    attribute.getOptions().put(2, MagjaStringUtils.randomString(3, 8));
    attribute.getOptions().put(3, MagjaStringUtils.randomString(3, 8));

    return attribute;
  }

}
