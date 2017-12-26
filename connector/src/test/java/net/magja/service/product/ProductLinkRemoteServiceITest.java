/**
 *
 */
package net.magja.service.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import net.magja.soap.MagentoSoapClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.product.Product;
import net.magja.model.product.ProductLink;
import net.magja.model.product.ProductLink.LinkType;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;
import net.magja.util.ObjectFactory;

/**
 * @author andre
 * @author Simon Zambrovski
 */
public class ProductLinkRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ProductLinkRemoteServiceITest.class);

  private ProductLinkRemoteService service;
  private ProductRemoteService productService;
  private List<Product> products;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    this.service = remoteServiceFactory.getProductLinkRemoteService();
    this.productService = remoteServiceFactory.getProductRemoteService();
    this.products = ObjectFactory.generateRandomProductsAndSave(productService, 2);
  }

  @After
  public void cleanUp() {
    ObjectFactory.removeProducts(this.productService, this.products);
  }

  public void createLink() {
    // associate the products
    final Product master = products.get(0);
    final ProductLink link = new ProductLink();
    link.setId(products.get(1).getId());
    link.setPosition(1);
    link.setQty(new Double(10));
    link.setLinkType(LinkType.RELATED);

    try {
      service.assign(master, link);
    } catch (ServiceException e) {
      log.error("Error creating link", e);
      fail("Error on create link");
    }
  }

  @Test
  public void testAssignProductProductLink() {
    createLink();
  }

  @Test
  public void testListByProduct() {

    createLink();
    Product product = products.get(0);

    try {
      final Set<ProductLink> links = service.list(product);
      for (ProductLink productLink : links) {
        log.debug(productLink.toString());
      }
      assertFalse(links.isEmpty());
    } catch (ServiceException e) {
      log.error("Error listing links", e);
      fail("Error on list link");
    }
  }

  @Test
  public void testListLinkTypeProduct() {

    createLink();
    Product product = products.get(0);

    try {
      final Set<ProductLink> links = service.list(LinkType.RELATED, product);
      for (ProductLink productLink : links) {
        log.debug(productLink.toString());
      }
      assertFalse(links.isEmpty());
    } catch (ServiceException e) {
      log.error("Error listing links", e);
      fail("Error on list link");
    }

  }

  @Test
  public void testRemove() {

    createLink();
    Product product = products.get(0);

    Set<ProductLink> links = null;
    try {
      links = service.list(LinkType.RELATED, product);
    } catch (ServiceException e) {
      log.error("Error listing links", e);
      fail("Error on list links");
    }

    for (ProductLink link : links) {
      try {
        service.remove(product, link);
      } catch (ServiceException e) {
        log.error("Error listing links", e);
        fail("Error on remove link");
      }
    }
  }

  @Test
  public void testUpdate() {

    createLink();
    Product product = products.get(0);

    Set<ProductLink> links = null;
    try {
      links = service.list(LinkType.RELATED, product);
    } catch (ServiceException e) {
      log.error("Error listing links", e);
      fail("Error on list links");
    }

    for (ProductLink link : links) {
      try {
        link.setQty(new Double(20));
        service.update(product, link);
      } catch (ServiceException e) {
        log.error("Error updating links", e);
        fail("Error on update link");
      }
    }
  }

}
