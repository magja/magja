package com.google.code.magja.service.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.util.ObjectFactory;

public class ProductMediaRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ProductMediaRemoteServiceITest.class);

  private ProductMediaRemoteService service;
  private ProductRemoteService productService;

  private Product product;
  private String file;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getProductMediaRemoteService();
    productService = remoteServiceFactory.getProductRemoteService();
  }

  @After
  public void clean() {
    ObjectFactory.removeProducts(productService, product);
  }

  @Test
  public void testSaveProductWithTwoMedias() throws NoSuchAlgorithmException, ServiceException {

    product = ObjectFactory.generateProduct();

    ProductMedia media1 = ObjectFactory.readMedia();
    product.addMedia(media1);

    ProductMedia media2 = ObjectFactory.readMedia();
    media2.setPosition(2);
    product.addMedia(media2);

    productService.add(product, null);

  }

  @Test
  public void testSave() throws NoSuchAlgorithmException, ServiceException {

    product = ObjectFactory.generateProduct();
    // first create a product to add the image to
    productService.add(product, null);

    ProductMedia productMedia = ObjectFactory.readMedia();
    productMedia.setProduct(product);
    service.create(productMedia);

    assertTrue(productMedia.getFile() != null);

    product.addMedia(productMedia);
    file = productMedia.getFile();
  }

  @Test
  public void testDelete() throws ServiceException, NoSuchAlgorithmException {

    testSave();

    ProductMedia media = product.getMedias().get(0);
    service.delete(media);
  }

  @Test
  public void testGetByProductAndFile() throws ServiceException, NoSuchAlgorithmException {
    testSave();

    ProductMedia media = service.getByProductAndFile(product, file);
    assertTrue("media is null!", media != null);
  }

  @Test
  public void testListByProduct() throws NoSuchAlgorithmException, ServiceException {
    testSave();

    Product search = new Product();
    search.setSku(product.getSku());
    search.setId(product.getId());

    List<ProductMedia> medias = service.listByProduct(search);
    for (ProductMedia media : medias) {
      log.info("Media: {}", media);
    }
    assertFalse(medias.isEmpty());

  }

  @Test
  @Ignore("Throws an error, the additional extension needs to be installed in Magento.")
  public void testGetMd5() throws ServiceException, NoSuchAlgorithmException {
    testSave();

    List<ProductMedia> medias = service.listByProduct(product);

    for (ProductMedia media : medias) {
      String md5 = service.getMd5(media.getFile());
      assertTrue("md5 is null!", md5 != null);
    }
  }

}
