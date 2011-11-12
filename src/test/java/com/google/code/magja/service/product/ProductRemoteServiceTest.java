/**
 *
 */
package com.google.code.magja.service.product;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.media.Media;
import com.google.code.magja.model.product.*;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.utils.MagjaFileUtils;
import com.google.code.magja.utils.MagjaStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author andre
 */
public class ProductRemoteServiceTest {

    private ProductRemoteService service;

    private String productSku;

    private Integer productId;

    /**
     * This method will run before the execution of any method
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = RemoteServiceFactory.getProductRemoteService();
    }

    /**
     * Just a test for create up to 50 products for performance purposes,
     * maked as ignore for maven builds
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testSaveMultipleProducts() throws Exception {
        for (int i = 0; i < 50; i++)
            testSaveConfigurableProduct();
    }

    /**
     * Test method for create a configurable product
     * This test its just for exemplify the use of Product Configurable
     * Before run this test, create some Attribute Set in Magento admin and change
     * the name and id bellow of that to work.
     * this test use two configurable attributes for instance, size and color
     */
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
        Integer[] websites = {1};
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
           * it can add a new product for this variation, remember you have to
           * creates the Configurable Attributes Data first (above code)
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
            e.printStackTrace();
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

        // don't forget to add the ConfigurableProductData as a subproduct to the main product
        product.getConfigurableSubProducts().add(prdData1);
        product.getConfigurableSubProducts().add(prdData2);

        // finally, create the product
        service.save(product, null);

        assertTrue(product.getId() != null);

        productId = product.getId();

        // way to get the subproducts from a super products:
        // product.get("subproduct_ids");
    }

    @Test
    public void testGetByIdWithSubProducts() throws ServiceException, NoSuchAlgorithmException {
        this.testSaveConfigurableProduct();
        Product product = service.getById(productId);
        assertTrue(product.get("subproduct_ids") != null);
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl
     * #save(com.google.code.magja.model.product.Product)}.
     */
    @Test
    public void testSave() throws ServiceException, NoSuchAlgorithmException {

        Product product = generateProduct();
        service.save(product, null);

        assertTrue(product.getId() != null);

        // set up the id and sku for use in other methods
        productId = product.getId();
        productSku = product.getSku();
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#getById(java.lang.Integer)}.
     */
    @Test
    public void testGetByIdAndSku() throws ServiceException, NoSuchAlgorithmException {

        testSave();

        Product productById = service.getById(productId);
        assertTrue(productById != null);

        Product productBySku = service.getBySku(productSku);
        assertTrue(productBySku != null);

        System.out.println(productById.toString());
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAll()}.
     */
    @Test
    public void testListAll() throws ServiceException {
        List<Product> products = service.listAll();
        for (Product product : products) System.out.println(product.toString());
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAllNoDep()}.
     */
    @Test
    public void testListAllNoDep() throws ServiceException {
        List<Product> products = service.listAllNoDep();
        for (Product product : products) System.out.println(product.toString());
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAllProductTypes()}.
     */
    @Test
    public void testListAllProductTypes() throws ServiceException {
        List<ProductType> types = service.listAllProductTypes();
        for (ProductType type : types) System.out.println(type.toString());
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#delete(java.lang.Integer)}
     * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#delete(java.lang.String)}.
     */
    @Test
    public void testDelete() throws ServiceException, NoSuchAlgorithmException {
        // first create some product
        testSave();

        // then delete it by id
        service.delete(productId);

        // another product
        testSave();

        // delete it
        service.delete(productSku);
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#getInventoryInfo(java.util.Set)}.
     */
    @Test
    public void testGetInventoryInfo() throws ServiceException {

        Set<Product> products = new HashSet<Product>(service.listAllNoDep());
        service.getInventoryInfo(products);

        for (Product product : products)
            System.out.println("Product ID: " + product.getId() + " - qty: " + product.getQty() + " - inStock: " + product.getInStock());
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#updateInventory(com.google.code.magja.model.product.Product)}.
     */
    @Test
    public void testUpdateInventory() throws ServiceException, NoSuchAlgorithmException {

        testSave();

        Product product = new Product();
        product.setId(productId);
        product.setSku(productSku);
        product.setQty(new Double(50));

        service.updateInventory(product);
    }

    /**
     * Support method for create a simple product without image
     *
     * @return simple product
     */
    public static Product generateProductWithoutImage() {
        Product product = new Product();
        product.setSku(MagjaStringUtils.randomString(3, 10).toUpperCase());
        product.setName(MagjaStringUtils.randomString(3, 5) + " Product Test");
        product.setShortDescription("this is a short description");
        product.setDescription("this is a description");
        product.setPrice(new Double(230.23));
        product.setCost(new Double(120.22));
        product.setEnabled(true);
        product.setWeight(new Double(0.100));
        Integer[] websites = {1};
        product.setWebsites(websites);

        product.setVisibility(Visibility.CATALOG_SEARCH);

        // inventory
        product.setQty(new Double(20));
        product.setInStock(true);

        // can use like that too (for the properties not mapped):
        product.set("meta_description", "one two tree");
        product.set("enable_googlecheckout", 1);

        // add category
        List<Category> categorys = new ArrayList<Category>();
        categorys.add(new Category(2));
        product.setCategories(categorys);

        //product.set("options_container", "container2");

        return product;
    }

    /**
     * Support method for create a simple product
     *
     * @return simple product
     */
    public static Product generateProduct() {
        Product product = generateProductWithoutImage();

        // add media
        try {
            byte[] data = MagjaFileUtils.getBytesFromFileURL("http://code.google.com/images/code_sm.png");

            Media image = new Media();
            image.setName("google");
            image.setMime("image/jpeg");
            image.setData(data);

            Set<ProductMedia.Type> types = new HashSet<ProductMedia.Type>();
            types.add(ProductMedia.Type.IMAGE);
            types.add(ProductMedia.Type.SMALL_IMAGE);

            ProductMedia media = new ProductMedia();
            media.setExclude(false);
            media.setImage(image);
            media.setLabel("Image for Product");
            media.setPosition(1);
            media.setTypes(types);

            product.addMedia(media);
        } catch (Exception e) {
            System.err.println("fail to add media to product");
        }

        return product;
    }

}
