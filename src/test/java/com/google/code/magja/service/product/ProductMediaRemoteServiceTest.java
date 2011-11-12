package com.google.code.magja.service.product;

import com.google.code.magja.model.media.Media;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.utils.MagjaFileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProductMediaRemoteServiceTest {

    private ProductMediaRemoteService service;

    private ProductRemoteService productService;

    private Product product;

    private String file;

    @Before
    public void setUp() throws Exception {
        service = RemoteServiceFactory.getProductMediaRemoteService();
        productService = RemoteServiceFactory.getProductRemoteService();
    }

    /**
     * Testing create a new product with two medias already
     */
    @Test
    public void testSaveProductWithTwoMedias() {

        try {

            product = ProductRemoteServiceTest.generateProduct();

            // image 1

            byte[] data1 = MagjaFileUtils
                    .getBytesFromFileURL("http://www.rockstore.com.br/store/catalog/co356.jpg");

            Media image1 = new Media();
            image1.setName("guevara");
            image1.setMime("image/jpeg");
            image1.setData(data1);

            Set<ProductMedia.Type> types1 = new HashSet<ProductMedia.Type>();
            types1.add(ProductMedia.Type.IMAGE);

            ProductMedia prd_media1 = new ProductMedia();
            prd_media1.setExclude(false);
            prd_media1.setImage(image1);
            prd_media1.setLabel("Image for Product");
            prd_media1.setPosition(1);
            prd_media1.setTypes(types1);

            product.addMedia(prd_media1);

            byte[] data2 = MagjaFileUtils
                    .getBytesFromFileURL("http://www.rockstore.com.br/store/catalog/co356_foto.jpg");

            Media image2 = new Media();
            image2.setName("guevara");
            image2.setMime("image/jpeg");
            image2.setData(data2);

            Set<ProductMedia.Type> types2 = new HashSet<ProductMedia.Type>();
            types2.add(ProductMedia.Type.SMALL_IMAGE);

            ProductMedia prd_media2 = new ProductMedia();
            prd_media2.setExclude(true);
            prd_media2.setImage(image2);
            prd_media2.setLabel("Small Image for Product");
            prd_media2.setPosition(2);
            prd_media2.setTypes(types2);

            product.addMedia(prd_media2);

            productService.save(product, null);

        } catch (IOException e1) {
            fail(e1.getMessage());
        } catch (ServiceException e2) {
            fail(e2.getMessage());
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductMediaRemoteServiceImpl#create(com.google.code.magja.model.product.ProductMedia)
     */
    @Test
    public void testSave() {

        try {
            // first create a product to add the image to
            product = ProductRemoteServiceTest.generateProduct();
            productService.save(product, null);

            // get some image from internet
            byte[] data = MagjaFileUtils
                    .getBytesFromFileURL("http://www.rockstore.com.br/store/catalog/hp-177_foto.jpg");

            // create the media contents
            Media image = new Media();
            image.setName("guevara");
            image.setMime("image/jpeg");
            image.setData(data);

            // options for the product media
            Set<ProductMedia.Type> types = new HashSet<ProductMedia.Type>();
            types.add(ProductMedia.Type.IMAGE);

            // finally, creates the product media and persists
            ProductMedia prd_media = new ProductMedia();
            prd_media.setExclude(false);
            prd_media.setImage(image);
            prd_media.setLabel("Testing that");
            prd_media.setPosition(1);
            prd_media.setTypes(types);
            prd_media.setProduct(product);

            service.create(prd_media);

            assertTrue(prd_media.getFile() != null);

            product.addMedia(prd_media);
            file = prd_media.getFile();

        } catch (IOException e1) {
            fail(e1.getMessage());
        } catch (ServiceException e2) {
            fail(e2.getMessage());
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductMediaRemoteServiceImpl#delete(com.google.code.magja.model.product.ProductMedia)
     */
    @Test
    public void testDelete() {

        try {

            testSave();
            ProductMedia media = product.getMedias().get(0);
            service.delete(media);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductMediaRemoteServiceImpl#getByProductAndFile(com.google.code.magja.model.product.Product, java.lang.String)
     */
    @Test
    public void testGetByProductAndFile() {
        try {

            testSave();
            ProductMedia media = service.getByProductAndFile(product, file);
            assertTrue("media is null!", media != null);

        } catch (ServiceException e) {
            fail(e.getMessage());
        }

    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductMediaRemoteServiceImpl#listByProduct(com.google.code.magja.model.product.Product)}
     * .
     */
    @Test
    public void testListByProduct() {
        try {

            testSave();
            List<ProductMedia> medias = service.listByProduct(product);
            for (ProductMedia media : medias)
                System.out.println(media.toString());

        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductMediaRemoteServiceImpl#getMd5(java.lang.String)}.
     */
    @Test
    public void testGetMd5() {
        try {
            testSave();
            List<ProductMedia> medias = service.listByProduct(product);

            for (ProductMedia media : medias) {
                String md5 = service.getMd5(media.getFile());
                assertTrue("md5 is null!", md5 != null);
            }


        } catch (ServiceException e) {
            fail(e.getMessage());
        }
    }

}
