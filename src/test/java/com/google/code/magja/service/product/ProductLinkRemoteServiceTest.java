/**
 *
 */
package com.google.code.magja.service.product;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductLink;
import com.google.code.magja.model.product.ProductLink.LinkType;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * @author andre
 */
public class ProductLinkRemoteServiceTest {

    private ProductLinkRemoteService service;

    private ProductRemoteService productService;

    private List<Product> products;

    private void generateProductsAndSave(Integer qty) {
        products = new ArrayList<Product>();

        for (int i = 0; i < qty; i++) {
            Product prd = ProductRemoteServiceTest.generateProduct();
            try {
                productService.save(prd, null);
            } catch (ServiceException e) {
                fail("Error on create product");
            } catch (NoSuchAlgorithmException e) {
                fail("Error on create product");
            }
            products.add(prd);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = RemoteServiceFactory.getProductLinkRemoteService();
        productService = RemoteServiceFactory.getProductRemoteService();
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductLinkRemoteServiceImpl#assign(com.google.code.magja.model.product.Product, com.google.code.magja.model.product.ProductLink)}.
     */
    @Test
    public void testAssignProductProductLink() {

        // generate two products and create
        generateProductsAndSave(2);

        // associate the products

        Product prd_master = products.get(0);

        ProductLink link_1 = new ProductLink();
        link_1.setId(products.get(1).getId());
        link_1.setPosition(1);
        link_1.setQty(new Double(10));
        link_1.setLinkType(LinkType.RELATED);

        try {
            service.assign(prd_master, link_1);
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Error on create link");
        }

    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductLinkRemoteServiceImpl#list(com.google.code.magja.model.product.Product)}.
     */
    @Test
    public void testListByProduct() {

        testAssignProductProductLink();

        Product product = products.get(0);

        try {
            Set<ProductLink> links = service.list(product);
            for (ProductLink productLink : links) System.out.println(productLink.toString());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Error on create link");
        }

    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductLinkRemoteServiceImpl#list(com.google.code.magja.model.product.ProductLink.LinkType, com.google.code.magja.model.product.Product)}.
     */
    @Test
    public void testListLinkTypeProduct() {

        testAssignProductProductLink();

        Product product = products.get(0);

        try {
            Set<ProductLink> links = service.list(LinkType.RELATED, product);
            for (ProductLink productLink : links) System.out.println(productLink.toString());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Error on list link");
        }

    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductLinkRemoteServiceImpl#remove(com.google.code.magja.model.product.Product, com.google.code.magja.model.product.ProductLink)}.
     */
    @Test
    public void testRemove() {

        testAssignProductProductLink();

        Product product = products.get(0);

        Set<ProductLink> links = null;
        try {
            links = service.list(LinkType.RELATED, product);
        } catch (ServiceException ex) {
            fail("Error on list links");
        }

        for (ProductLink link : links) {
            try {
                service.remove(product, link);
            } catch (ServiceException e) {
                fail("Error on remove link");
            }
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.product.ProductLinkRemoteServiceImpl#update(com.google.code.magja.model.product.Product, com.google.code.magja.model.product.ProductLink)}.
     */
    @Test
    public void testUpdate() {

        testAssignProductProductLink();

        Product product = products.get(0);

        Set<ProductLink> links = null;
        try {
            links = service.list(LinkType.RELATED, product);
        } catch (ServiceException ex) {
            fail("Error on list links");
        }

        for (ProductLink link : links) {
            try {
                link.setQty(new Double(20));
                service.update(product, link);
            } catch (ServiceException e) {
                fail("Error on update link");
            }
        }
    }

}
