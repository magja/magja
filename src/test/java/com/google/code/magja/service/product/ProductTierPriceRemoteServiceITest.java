package com.google.code.magja.service.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductTierPrice;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.util.ObjectFactory;
import com.google.common.collect.Lists;

public class ProductTierPriceRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ProductTierPriceRemoteServiceITest.class);

  private ProductTierPriceRemoteService service;
  private ProductRemoteService productService;
  private Product product;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getProductTierPriceRemoteService();
    productService = remoteServiceFactory.getProductRemoteService();
  }

  @After
  public void clean() {
    ObjectFactory.removeProducts(productService, product);
  }

  @Test
  public void testSave() throws NoSuchAlgorithmException, ServiceException {

    product = ObjectFactory.generateProduct();
    productService.add(product, null);

    final List<ProductTierPrice> tierPrices = Lists.newArrayList();
    final ProductTierPrice tierPrice1 = new ProductTierPrice();
    tierPrice1.setPrice(12.5);
    tierPrice1.setQuantity(100d);
    tierPrices.add(tierPrice1);

    final ProductTierPrice tierPrice2 = new ProductTierPrice();
    tierPrice2.setPrice(10.5);
    tierPrice2.setQuantity(200d);
    tierPrices.add(tierPrice2);

    Boolean update = service.update(product, tierPrices);
    assertTrue(update);

    final List<ProductTierPrice> retrievedPrices = service.getTierPrices(product);
    assertEquals(tierPrices.size(), retrievedPrices.size());
    for (ProductTierPrice p : tierPrices) {
      log.info("Tier price {}", p);
      assertTrue(retrievedPrices.contains(p));
    }

  }

}
