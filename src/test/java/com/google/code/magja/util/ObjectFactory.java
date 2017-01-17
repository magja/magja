package com.google.code.magja.util;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.customer.Customer;
import com.google.code.magja.model.customer.Customer.Gender;
import com.google.code.magja.model.customer.CustomerAddress;
import com.google.code.magja.model.media.Media;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.model.product.Visibility;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.product.ProductRemoteService;
import com.google.code.magja.utils.MagjaStringUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Facotry for creating objects for tests.
 * 
 * @author Simon Zambrovski
 */
public class ObjectFactory {

  private final static Logger log = LoggerFactory.getLogger(ObjectFactory.class);

  /**
   * Support method for create a simple product without image.
   *
   * @return simple product.
   */
  @SuppressWarnings("deprecation")
  public static Product generateProduct() {
    Product product = new Product();

    // product.setAttributeSet(new ProductAttributeSet(93, "Fabric"));

    product.setShop_id("zibalabel");
    product.setLocal_sku("T13");
    product.setLocal_price(new Double(8000.00));
    product.setShipping_policy("normal");

    product.setSku(UUID.randomUUID().toString());
    product.setName("Tas T09");
    product.setShortDescription("this is a short description");
    product.setDescription("this is a description");
    product.setPrice(new Double(10000.00));// (new Double(230.23));
    product.setCost(new Double(9500.00));// (new Double(120.22));
    product.setEnabled(true);
    product.setWeight(new Double(0.100));
    product.setAttributes(ImmutableMap.<String, Object> of("length", 3.0, "width", 2.0, "height", 4.0));
    // product.setMaterial(1L);
    // product.setMotif(1L);
    // product.setSignature(1L);
    // product.setBatik_technique(1L);
    // product.setOrigin(1L);
    // product.setBatik_age(1L);
    // product.setCondition(1L);
    // product.setItem_color(1L);
    product.setWebsites(new Integer[] { 1 });
    product.setVisibility(Visibility.CATALOG_SEARCH);

    // inventory
    product.setQty(new Double(20));
    product.setInStock(true);
    product.setMetaKeyword("keyword");
    product.setMetaDescription("meta description");
    product.setMetaTitle("meta title");

    // can use like that too (if properties are not mapped):
    product.set("enable_googlecheckout", 1);

    // add category
    List<Category> categories = new ArrayList<Category>();
    categories.add(new Category(2));
    product.setCategories(categories);

    // product.set("options_container", "container2");

    log.debug("New product created: {}", product);

    return product;
  }

  /**
   * Support method for create a simple product with image.
   *
   * @return simple product.
   * 
   */
  public static Product generateProductWithImage() {
    final Product product = ObjectFactory.generateProduct();

    // add media
    final ProductMedia media = readMedia();
    product.addMedia(media);

    return product;
  }

  /**
   * Create products using supplied product service.
   * 
   * @param productService
   *          initialized product service to use.
   * @param amount
   *          amount of items to create.
   * @return list of created products.
   */
  public static List<Product> generateRandomProductsAndSave(final ProductRemoteService productService, final int amount) {
    final List<Product> products = new ArrayList<Product>();
    for (int i = 0; i < amount; i++) {
      final Product product = ObjectFactory.generateProduct();
      try {
        productService.add(product, null);
      } catch (ServiceException e) {
        log.error("Error on creation", e);
        fail("Error on product creation");
      } catch (NoSuchAlgorithmException e) {
        log.error("Error on creation", e);
        fail("Error on product creation");
      }
      products.add(product);
    }
    return products;
  }

  /**
   * Remove a list of products using supplied product service.
   * 
   * @param productService
   *          initialized product service to use.
   * @param products
   *          products to remove.
   */
  public static void removeProducts(final ProductRemoteService productService, final List<Product> products) {
    for (Product product : products) {
      try {
        productService.delete(product.getId());
      } catch (ServiceException e) {
        log.error("Error on creation", e);
        fail("Error on product deletion");
      }
    }
  }

  /**
   * Remove a list of products using supplied product service.
   * 
   * @param productService
   *          initialized product service to use.
   * @param products
   *          products to remove.
   */
  public static void removeProducts(ProductRemoteService productService, final Product... products) {
    removeProducts(productService, Arrays.asList(products));
  }

  /**
   * Reads an image from classpath.
   * 
   * @return product media read from classpath.
   * @throws IOException
   *           on any errors.
   */
  public static ProductMedia readMedia() {
    final ProductMedia productMedia = new ProductMedia();
    // get some image from classpath
    final InputStream imageStream = ObjectFactory.class.getResourceAsStream("/bandung-everlasting-beauty_notext.png");
    byte[] data;
    try {
      data = IOUtils.toByteArray(imageStream);

      // create the media contents
      final Media image = new Media();
      image.setName("bandung-everlasting-beauty");
      image.setMime("image/png");
      image.setData(data);

      // options for the product media
      final Set<ProductMedia.Type> types = ImmutableSet.of(ProductMedia.Type.IMAGE);

      // finally, creates the product media and persists
      productMedia.setExclude(false);
      productMedia.setImage(image);
      productMedia.setLabel("Testing that");
      productMedia.setPosition(1);
      productMedia.setTypes(types);

    } catch (IOException e) {
      log.error("Error creating a media", e);
      fail("Error media creation");
    }

    return productMedia;
  }

  /**
   * Generates a random integer.
   * 
   * @return random integer.
   */
  public static Integer randomInteger() {
    final Random random = new Random();
    return random.nextInt(255);
  }

  public static Customer generateCustomer() {
    Customer cust = new Customer();

    cust.setFirstName(MagjaStringUtils.randomString(5, 10));
    cust.setMiddleName(MagjaStringUtils.randomString(5, 10));
    cust.setLastName(MagjaStringUtils.randomString(5, 10));
    cust.setPassword("test12");
    cust.setPrefix("Herr");
    cust.setWebsiteId(1);
    cust.setGroupId(1);
    cust.setGender(Gender.MALE);
    String email = MagjaStringUtils.randomString(4, 5) + "@" + MagjaStringUtils.randomString(4, 5) + ".com";
    cust.setEmail(email.toLowerCase());

    // this include the date of birth on the customer, and it's works,
    // but, that attribute isn't listed when getting a customer from magento
    cust.set("dob", "1980-08-17 20:53:04");
    return cust;
  }

  public static CustomerAddress generateAddress() {
    CustomerAddress address = new CustomerAddress();
    address.setCity("Berlin");
    address.setCompany("Company");
    address.setCountryCode("DE");
    address.setFax("4444-1111");
    address.setPrefix("Herr");
    address.setFirstName(MagjaStringUtils.randomString(5, 10));
    address.setMiddleName(MagjaStringUtils.randomString(5, 10));
    address.setLastName(MagjaStringUtils.randomString(5, 10));
    address.setPostCode("10117");
    address.setTelephone("1111-2222");
    address.setRegion("Berlin");
    address.setStreet(MagjaStringUtils.randomString(5, 10) + " 1");

    return address;
  }

  public static CustomerAddress generateAddress2() {

    CustomerAddress address = new CustomerAddress();

    address.setCity(MagjaStringUtils.randomString(5, 10));
    address.setCompany(MagjaStringUtils.randomString(5, 10));
    address.setCountryCode("BR");
    address.setDefaultBilling(true);
    address.setDefaultShipping(false);
    address.setFax("(19) 4444-1111");
    address.setFirstName(MagjaStringUtils.randomString(5, 10));
    address.setMiddleName(MagjaStringUtils.randomString(5, 10));
    address.setLastName(MagjaStringUtils.randomString(5, 10));
    address.setPostCode("13000-002");
    address.setTelephone("(19) 1111-2222");
    address.setRegion("SP");
    address.setStreet(MagjaStringUtils.randomString(5, 10));

    return address;
  }

  public static Customer generateCustomer2() {
    Customer cust = new Customer();

    cust.setFirstName(MagjaStringUtils.randomString(5, 10));
    cust.setMiddleName(MagjaStringUtils.randomString(5, 10));
    cust.setLastName(MagjaStringUtils.randomString(5, 10));
    cust.setPassword("test12");
    cust.setPrefix("Mr.");
    cust.setWebsiteId(1);
    cust.setGroupId(1);
    cust.setGender(Gender.MALE);
    String email = MagjaStringUtils.randomString(4, 5) + "@" + MagjaStringUtils.randomString(4, 5) + ".com";
    cust.setEmail(email.toLowerCase());

    // this include the date of birth on the customer, and it's works,
    // but, that attribute isn't listed when getting a customer from magento
    cust.set("dob", "1980-08-17 20:53:04");

    return cust;
  }
}
