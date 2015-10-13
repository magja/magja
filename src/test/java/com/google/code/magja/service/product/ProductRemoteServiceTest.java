package com.google.code.magja.service.product;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.model.media.Media;
import com.google.code.magja.model.product.ConfigurableAttributeData;
import com.google.code.magja.model.product.ConfigurableData;
import com.google.code.magja.model.product.ConfigurableDataException;
import com.google.code.magja.model.product.ConfigurableProductData;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductAttributeSet;
import com.google.code.magja.model.product.ProductMedia;
import com.google.code.magja.model.product.ProductRefMagja;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.model.product.ProductUpdatePrice;
import com.google.code.magja.model.product.Visibility;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.service.product.ProductRemoteService.Dependency;
import com.google.code.magja.utils.MagjaFileUtils;
import com.google.code.magja.utils.MagjaStringUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @author andre
 */
public class ProductRemoteServiceTest {

	private transient static Logger log = LoggerFactory.getLogger(ProductRemoteServiceTest.class);
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
        service = RemoteServiceFactory.getSingleton().getProductRemoteService();
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

    @Test
    public void createOneSimpleProductWithoutImageWithMaterialAttribute() throws ServiceException, NoSuchAlgorithmException {
        Product product = generateProductWithoutImage();
        product.setAttributes(ImmutableMap.<String, Object>of("material", 383));
        service.save(product, null);

        assertNotNull(product.getId());

        // set up the id and sku for use in other methods
        productId = product.getId();
        productSku = product.getSku();
    }
    
    /**
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl
     * #save(com.google.code.magja.model.product.Product)}.
     */
    @Test
    public void createOneSimpleProductWithImage() throws ServiceException, NoSuchAlgorithmException {
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

        createOneSimpleProductWithImage();

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
     * Test method for {@link com.google.code.magja.service.product.ProductRemoteServiceImpl#listAllPlus()}.
     */
    @Test
    public void testListAllPlus() throws ServiceException {
        List<Product> products = service.listAllPlus(ImmutableSet.of("status"));
        assertNotNull(products);
        log.info("Got {} products", products.size());
        assertTrue(products.size() > 0);
        for (Product product : products) {
        	assertNotNull(product.getAttributes());
        	assertNotNull(product.getAttributes().get("status"));
        }
        for (Product product : products)
        	log.info("{}", product);
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
        createOneSimpleProductWithImage();

        // then delete it by id
        service.delete(productId);

        // another product
        createOneSimpleProductWithImage();

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

        createOneSimpleProductWithImage();

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
        product.setAttributeSet(new ProductAttributeSet(93, "Fabric"));
        product.setShop_id("zibalabel");
        product.setLocal_sku("T13");
        product.setSku("zibalabel_t13");//(MagjaStringUtils.randomString(3, 10).toUpperCase());
        product.setName("Tas T09");//(MagjaStringUtils.randomString(3, 5) + " Product Test");
        product.setShortDescription("this is a short description");
        product.setDescription("this is a description");
        product.setPrice(new Double(10000.00));//(new Double(230.23));
        product.setCost(new Double(9500.00));//(new Double(120.22));
        product.setEnabled(true);
        product.setWeight(new Double(0.100));
        product.setLocal_price(new Double(8000.00));
        product.setShipping_policy("normal");
        product.setAttributes(ImmutableMap.<String, Object>of(
        		"length", 3.0,
        		"width", 2.0,
        		"height", 4.0));
//        product.setMaterial(1L);
//        product.setMotif(1L);
//        product.setSignature(1L);
//        product.setBatik_technique(1L);
//        product.setOrigin(1L);
//        product.setBatik_age(1L);
//        product.setCondition(1L);
//        product.setItem_color(1L);
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
        categorys.add(new Category(3));
        product.setCategories(categorys);

        //product.set("options_container", "container2");

        log.debug("Product without image is {}", product);
        
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
            image.setMime("image/png");
            image.setData(data);

            Set<ProductMedia.Type> types = ImmutableSet.of(ProductMedia.Type.IMAGE, ProductMedia.Type.SMALL_IMAGE);

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

    @Test
    public void getRefsMapShouldWork() throws ServiceException {
    	Map<String, Map<String, String>> productRefs = service.getRefsMap(ImmutableList.of(
    			"zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"));
    	log.info("Refs: {}", productRefs);
    	assertNotNull(productRefs);
    	assertEquals(2, productRefs.size());
    	assertEquals(ImmutableSet.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"),
    			productRefs.keySet());
    }
    
    @Test
    public void getRefsShouldWork() throws ServiceException {
    	Map<String, ProductRefMagja> productRefs = service.getRefs(ImmutableList.of(
    			"zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"));
    	log.info("Refs: {}", productRefs);
    	assertNotNull(productRefs);
    	assertEquals(2, productRefs.size());
    	assertEquals(ImmutableSet.of("zibalabel_venus-apparel-15", "zibalabel_zulfia-houseware-76"),
    			productRefs.keySet());
    }
    
    @Test
    public void updatePrice() throws NoSuchAlgorithmException, ServiceException {
    	ImmutableList<ProductUpdatePrice> products = ImmutableList.of(
				new ProductUpdatePrice("ajah_a", new BigDecimal(1000000), new BigDecimal(2000000)),
				new ProductUpdatePrice("zibalabel_b_06", new BigDecimal(300000), new BigDecimal(400000)));
    	
    	service.updatePrice(products);
    }
    
    @Test public void getProductBySku() throws ServiceException {
    	Product product = service.getBySku("zibalabel_letika-bag-37");
    	log.debug("Product by zibalabel_letika-bag-37: {}.", product);
    	log.debug("Product attrset: {}.", product.getAttributeSet());
    	log.debug("Product attributes: {}.", product.getAttributes());
    }
    
    @Test public void getProductBySku_NumericSkusCanBeUsed() throws ServiceException, NoSuchAlgorithmException {
        
        final String numericSku = "12782178";
        Product newProduct = generateProductWithoutImage();
        newProduct.setSku(numericSku);
        service.save(newProduct, null);
        Product productFound = service.getBySku(numericSku);
        assertNotNull(productFound.getId());
        log.debug("Product by zibalabel_letika-bag-37: {}.", productFound);
        log.debug("Product attrset: {}.", productFound.getAttributeSet());
        log.debug("Product attributes: {}.", productFound.getAttributes());
    }
    
    @Test public void getProductBySkuWithAttributeSet() throws ServiceException {
    	Product product = service.getBySku("zibalabel_letika-bag-37", ImmutableSet.<String>of(),
    			ImmutableSet.of(Dependency.ATTRIBUTE_SET));
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

    @Test public void getProductBySkuWithAttributes() throws ServiceException {
    	Product product = service.getBySku("zibalabel_letika-bag-37",
    			ImmutableSet.of("weight", "item_color"));
    	log.debug("Product by zibalabel_letika-bag-37: {}.", product);
    	log.debug("Product attributes: {}.", product.getAttributes());
    	assertNotNull(product);
    	assertThat(product.getAttributes().values(), hasSize(2));
    }
    
}
