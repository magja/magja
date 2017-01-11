package com.google.code.magja.service.product;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.product.ConfigurableAttributeData;
import com.google.code.magja.model.product.ConfigurableData;
import com.google.code.magja.model.product.ConfigurableDataException;
import com.google.code.magja.model.product.ConfigurableProductData;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.model.product.ProductRefMagja;
import com.google.code.magja.model.product.ProductTierPrice;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.product.ProductRemoteService.Dependency;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.util.ObjectFactory;
import com.google.code.magja.utils.MagjaStringUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * Basic integration test of product remote service.
 * 
 * @author andre
 * @author Simon Zambrovski
 */
public class ProductRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ProductRemoteServiceITest.class);
  private static final int AMOUNT = 50;
  private ProductRemoteService service;

  private Integer productId;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getProductRemoteService();
  }

  @Test
  public void testSimpleProductCreate() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());
    service.delete(product.getId());
  }

  @Test
  public void testDelete() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());
    service.delete(product.getSku());
  }

  @Test
  public void testCreateSimpleProductWithImage() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProductWithImage();
    service.add(product);
    assertNotNull(product.getId());
    service.delete(product.getId());
  }

  @Test
  public void testCreateSimpleProductWithoutImageWithMaterialAttribute() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    product.setAttributes(ImmutableMap.<String, Object> of("material", 383));
    service.add(product);
    assertNotNull(product.getId());
    service.delete(product.getId());
  }

  @Test
  public void testGetByIdAndSku() throws ServiceException, NoSuchAlgorithmException {

    final Product product = ObjectFactory.generateProductWithImage();
    service.add(product);
    assertNotNull(product.getId());

    Product productById = service.getById(product.getId());
    assertNotNull(productById);

    Product productBySku = service.getBySku(product.getSku());
    assertNotNull(productBySku);

    assertEquals(productById.getName(), productBySku.getName());
    service.delete(product.getId());
  }

  @Test
  public void testListAll() throws ServiceException, NoSuchAlgorithmException {

    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    boolean found = false;
    final List<Product> products = service.listAll();
    for (final Product foundProduct : products) {
      if (foundProduct.getId().equals(product.getId())) {
        found = true;
        break;
      }
    }

    service.delete(product.getId());
    assertTrue(found);
  }

  @Test
  public void testListAllNoDep() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    boolean found = false;
    final List<Product> products = service.listAllNoDep();
    for (final Product foundProduct : products) {
      if (foundProduct.getId().equals(product.getId())) {
        found = true;
        break;
      }
    }

    service.delete(product.getId());
    assertTrue(found);
  }

  @Test
  public void testTierPriceProductCreate() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    final List<ProductTierPrice> tierPrices = Lists.newArrayList();

    final ProductTierPrice tierPrice = new ProductTierPrice();
    tierPrice.setPrice(10.01d);
    tierPrice.setQuantity(12d);
    tierPrices.add(tierPrice);

    final ProductTierPrice tierPrice2 = new ProductTierPrice();
    tierPrice2.setPrice(9.01d);
    tierPrice2.setQuantity(24d);
    tierPrices.add(tierPrice2);

    product.setTierPrices(tierPrices);

    service.add(product);
    assertNotNull(product.getId());
    service.delete(product.getId());

  }

  /**
   * Currently error is thrown.
   * 
   * @throws ServiceException
   * @throws NoSuchAlgorithmException
   */
  @Ignore("Exception on access, catalog_product.list_plus is not part of official api")
  @Test
  public void testListAllPlus() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    boolean found = false;
    final List<Product> products = service.listAllPlus(ImmutableSet.of("state"));
    for (final Product foundProduct : products) {
      assertNotNull(foundProduct.getAttributes());
      assertNotNull(foundProduct.getAttributes().get("state"));

      if (foundProduct.getId().equals(product.getId())) {
        found = true;
        break;
      }
    }

    service.delete(product.getId());
    assertTrue(found);
  }

  @Test
  public void testInfo() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    // product.getId()
    final Product found = service.getBySku("242d8a7b-999d-4d8a-b209-9da7f64fa2ba", ImmutableSet.of("tier_price"));
    assertNotNull(found.getAttributes());
    assertNotNull(found.getAttributes().get("tier_price"));
    log.info("tier price is {}", found.getAttributes().get("tier_price"));

    // assertEquals(product.getId(), found.getId());

    service.delete(product.getId());
  }

  @Test
  public void testListAllProductTypes() throws ServiceException {
    final List<ProductType> types = service.listAllProductTypes();
    for (final ProductType type : types) {
      log.debug("{}", type.toString());
    }
    assertTrue(!types.isEmpty());
  }

  @Test
  public void testGetInventoryInfo() throws ServiceException, NoSuchAlgorithmException {
    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    final Set<Product> products = new HashSet<Product>(service.listAllNoDep());
    service.getInventoryInfo(products);
    for (Product productInventory : products) {
      if (productInventory.getId().equals(product.getId())) {
        assertEquals(product.getQty(), productInventory.getQty());
      }
      log.debug("Product ID: {} - qty: {}  - inStock: {}", product.getId(), product.getQty(), product.getInStock());
    }

    service.delete(product.getId());
  }

  @Test
  public void testUpdateInventory() throws ServiceException, NoSuchAlgorithmException {

    final Product product = ObjectFactory.generateProduct();
    service.add(product);
    assertNotNull(product.getId());

    final Product productUpdate = new Product();
    productUpdate.setId(product.getId());
    productUpdate.setSku(product.getSku());
    productUpdate.setQty(Double.valueOf(50));

    service.updateInventory(productUpdate);

    final Product updated = service.getById(product.getId());
    assertEquals(Double.valueOf(50), updated.getQty());
    service.delete(product.getId());
  }

  @Test
  public void testGetProductByNumericSku() throws ServiceException, NoSuchAlgorithmException {
    final String numericSku = "12782178";
    Product newProduct = ObjectFactory.generateProduct();
    newProduct.setSku(numericSku);
    service.add(newProduct);
    Product productFound = service.getBySku(numericSku);
    assertNotNull(productFound.getId());
    service.delete(newProduct.getId());
  }

  @Test
  @Ignore
  public void getRefsMapShouldWork() throws ServiceException {
    Map<String, Map<String, String>> productRefs = service.getRefsMap(ImmutableList.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"));
    log.info("Refs: {}", productRefs);
    assertNotNull(productRefs);
    assertEquals(2, productRefs.size());
    assertEquals(ImmutableSet.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"), productRefs.keySet());
  }

  @Test
  @Ignore
  public void getRefsShouldWork() throws ServiceException {
    Map<String, ProductRefMagja> productRefs = service.getRefs(ImmutableList.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"));
    log.info("Refs: {}", productRefs);
    assertNotNull(productRefs);
    assertEquals(2, productRefs.size());
    assertEquals(ImmutableSet.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"), productRefs.keySet());
  }

  @Ignore
  @Test
  public void getProductBySkuWithAttributeSet() throws ServiceException {
    Product product = service.getBySku("zibalabel_letika-bag-37", ImmutableSet.<String> of(), ImmutableSet.of(Dependency.ATTRIBUTE_SET));
    log.debug("Product by zibalabel_letika-bag-37: {}.", product);
    log.debug("Product attrset: {}.", product.getAttributeSet());
    log.debug("Product attributes: {}.", product.getAttributes());
    assertNotNull(product);
    assertNotNull(product.getAttributeSet());
    assertNotNull(product.getAttributeSet().getId());
    assertThat(product.getAttributeSet().getName(), not(isEmptyOrNullString()));
    assertThat(product.getAttributeSet().getId(), greaterThan(0));
    assertThat(product.getAttributes().values(), empty());
  }

  @Ignore
  @Test
  public void getProductBySkuWithAttributes() throws ServiceException {
    Product product = service.getBySku("zibalabel_letika-bag-37", ImmutableSet.of("weight", "item_color"));
    log.debug("Product by zibalabel_letika-bag-37: {}.", product);
    log.debug("Product attributes: {}.", product.getAttributes());
    assertNotNull(product);
    assertThat(product.getAttributes().values(), hasSize(2));
  }

  /**
   * Just a test for create up to 50 products for performance purposes, maked as
   * ignore for maven builds
   *
   * @throws Exception
   */
  @Test
  @Ignore
  public void testSaveMultipleProducts() throws Exception {
    for (int i = 0; i < AMOUNT; i++)
      testSaveConfigurableProduct();
  }

  /**
   * Test method for create a configurable product This test its just for
   * exemplify the use of Product Configurable Before run this test, create some
   * Attribute Set in Magento admin and change the name and id below of that to
   * work. This test use two configurable attributes for instance, size and
   * color
   */
  @Ignore
  @Test
  public void testSaveConfigurableProduct() throws ServiceException, NoSuchAlgorithmException {

    ProductAttributeSet set = new ProductAttributeSet();
    set.setId(9);
    set.setName("Vestuario");

    Product product = new Product();
    product.setAttributeSet(set);
    product.setSku(MagjaStringUtils.randomString(3, 10).toUpperCase());
    product.setName(MagjaStringUtils.randomString(3, 5) + " Configurable Prod");
    product.setShortDescription("Short description");
    product.setDescription("Some description for that configurable product");
    product.setPrice(new Double(222.23));
    product.setCost(new Double(111.22));
    product.setEnabled(true);
    product.setWeight(new Double(0.100));
    Integer[] websites = { 1 };
    product.setWebsites(websites);

    // set the product type as a configurable
    product.setType(ProductType.CONFIGURABLE);

    /*
     * creates the Configurable Attributes Data, that are the attributes wich
     * are configurable for this product
     */
    product.setConfigurableAttributesData(new LinkedList<ConfigurableAttributeData>());

    // the attribute SIZE
    ConfigurableAttributeData cnfgAttributeSize = new ConfigurableAttributeData();
    cnfgAttributeSize.setAttributeId(126);
    cnfgAttributeSize.setAttributeCode("size");

    // the attribute COLOR
    ConfigurableAttributeData cnfgAttributeColor = new ConfigurableAttributeData();
    cnfgAttributeColor.setAttributeId(83);
    cnfgAttributeColor.setAttributeCode("color");

    // add the conf attr data to the product
    product.getConfigurableAttributesData().add(cnfgAttributeSize);
    product.getConfigurableAttributesData().add(cnfgAttributeColor);

    // reset the configurable product data from product
    product.setConfigurableSubProducts(new LinkedList<ConfigurableProductData>());

    /*
     * creates the variations of the attributes, for each configurable attribute
     * it can add a new product for this variation, remember you have to creates
     * the Configurable Attributes Data first (above code)
     */
    ConfigurableProductData prdData1 = new ConfigurableProductData();

    ConfigurableData confgDataSizeOne = new ConfigurableData();
    confgDataSizeOne.setAttributeId(126);
    confgDataSizeOne.setLabel("P");
    confgDataSizeOne.setValueIndex(6);

    ConfigurableData confgDataColorOne = new ConfigurableData();
    confgDataColorOne.setAttributeId(83);
    confgDataColorOne.setLabel("blue");
    confgDataColorOne.setValueIndex(15);

    prdData1.getData().add(confgDataSizeOne);
    prdData1.getData().add(confgDataColorOne);

    try {
      prdData1.configurateProduct(product, new Double(10), new Double(100), "euirowruio");
    } catch (ConfigurableDataException e) {
      log.error("Error during product configuration", e);
      fail(e.getMessage());
    }

    ConfigurableProductData prdData2 = new ConfigurableProductData();

    ConfigurableData confgDataSizeTwo = new ConfigurableData();
    confgDataSizeTwo.setAttributeId(126);
    confgDataSizeTwo.setLabel("M");
    confgDataSizeTwo.setValueIndex(7);

    ConfigurableData confgDataColorTwo = new ConfigurableData();
    confgDataColorTwo.setAttributeId(83);
    confgDataColorTwo.setLabel("red");
    confgDataColorTwo.setValueIndex(14);

    prdData2.getData().add(confgDataSizeTwo);
    prdData2.getData().add(confgDataColorTwo);

    try {
      prdData2.configurateProduct(product, new Double(15), new Double(130), "eurieowreu");
    } catch (ConfigurableDataException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    // don't forget to add the ConfigurableProductData as a subproduct to the
    // main product
    product.getConfigurableSubProducts().add(prdData1);
    product.getConfigurableSubProducts().add(prdData2);

    // finally, create the product
    service.add(product, null);

    assertTrue(product.getId() != null);

    productId = product.getId();

    // way to get the subproducts from a super products:
    // product.get("subproduct_ids");

    service.delete(product.getId());
  }

  @Ignore
  @Test
  public void testGetByIdWithSubProducts() throws ServiceException, NoSuchAlgorithmException {
    this.testSaveConfigurableProduct();
    Product product = service.getById(productId);
    assertTrue(product.get("subproduct_ids") != null);
  }

}
