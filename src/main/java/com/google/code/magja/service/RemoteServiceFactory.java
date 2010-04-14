/**
 *
 */
package com.google.code.magja.service;

import com.google.code.magja.service.category.CategoryAttributeRemoteService;
import com.google.code.magja.service.category.CategoryAttributeRemoteServiceImpl;
import com.google.code.magja.service.category.CategoryRemoteService;
import com.google.code.magja.service.category.CategoryRemoteServiceImpl;
import com.google.code.magja.service.country.CountryRemoteService;
import com.google.code.magja.service.country.CountryRemoteServiceImpl;
import com.google.code.magja.service.customer.CustomerAddressRemoteService;
import com.google.code.magja.service.customer.CustomerAddressRemoteServiceImpl;
import com.google.code.magja.service.customer.CustomerRemoteService;
import com.google.code.magja.service.customer.CustomerRemoteServiceImpl;
import com.google.code.magja.service.order.OrderRemoteService;
import com.google.code.magja.service.order.OrderRemoteServiceImpl;
import com.google.code.magja.service.order.ShipmentRemoteService;
import com.google.code.magja.service.order.ShipmentRemoteServiceImpl;
import com.google.code.magja.service.product.ProductAttributeRemoteService;
import com.google.code.magja.service.product.ProductAttributeRemoteServiceImpl;
import com.google.code.magja.service.product.ProductLinkRemoteService;
import com.google.code.magja.service.product.ProductLinkRemoteServiceImpl;
import com.google.code.magja.service.product.ProductMediaRemoteService;
import com.google.code.magja.service.product.ProductMediaRemoteServiceImpl;
import com.google.code.magja.service.product.ProductRemoteService;
import com.google.code.magja.service.product.ProductRemoteServiceImpl;
import com.google.code.magja.service.region.RegionRemoteService;
import com.google.code.magja.service.region.RegionRemoteServiceImpl;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.soap.SoapConfig;

/**
 * @author andre
 *
 */
public class RemoteServiceFactory {

    private final MagentoSoapClient magentoSoapClient;

    public RemoteServiceFactory() {
        this.magentoSoapClient = MagentoSoapClient.getInstance();
    }

    public RemoteServiceFactory(SoapConfig soapConfig) {
        this.magentoSoapClient = MagentoSoapClient.getInstance(soapConfig);
    }

    public RemoteServiceFactory(MagentoSoapClient magentoSoapClient) {
        this.magentoSoapClient = magentoSoapClient;
    }

    /**
     * @return the shipmentRemoteService
     */
    public ShipmentRemoteService getShipmentRemoteService() {

        ShipmentRemoteService shipmentRemoteService = new ShipmentRemoteServiceImpl();
        shipmentRemoteService.setSoapClient(magentoSoapClient);

        return shipmentRemoteService;
    }

    /**
     * @return the orderRemoteService
     */
    public OrderRemoteService getOrderRemoteService() {

        OrderRemoteService orderRemoteService = new OrderRemoteServiceImpl();
        orderRemoteService.setSoapClient(magentoSoapClient);

        return orderRemoteService;
    }

    /**
     * @return the regionRemoteService
     */
    public RegionRemoteService getRegionRemoteService() {

        RegionRemoteService regionRemoteService = new RegionRemoteServiceImpl();
        regionRemoteService.setSoapClient(magentoSoapClient);

        return regionRemoteService;
    }

    /**
     * @return the countryRemoteService
     */
    public CountryRemoteService getCountryRemoteService() {

        CountryRemoteService countryRemoteService = new CountryRemoteServiceImpl();
        countryRemoteService.setSoapClient(magentoSoapClient);

        return countryRemoteService;
    }

    /**
     * @return the customerAddressRemoteService
     */
    public CustomerAddressRemoteService getCustomerAddressRemoteService() {

        CustomerAddressRemoteService customerAddressRemoteService = new CustomerAddressRemoteServiceImpl();
        customerAddressRemoteService.setSoapClient(magentoSoapClient);

        return customerAddressRemoteService;
    }

    /**
     * @return the customerRemoteService
     */
    public CustomerRemoteService getCustomerRemoteService() {

        CustomerRemoteService customerRemoteService = new CustomerRemoteServiceImpl();
        customerRemoteService.setSoapClient(magentoSoapClient);

        return customerRemoteService;
    }

    /**
     * @return the productLinkRemoteService
     */
    public ProductLinkRemoteService getProductLinkRemoteService() {

        ProductLinkRemoteService productLinkRemoteService = new ProductLinkRemoteServiceImpl();
        productLinkRemoteService.setSoapClient(magentoSoapClient);

        return productLinkRemoteService;
    }

    /**
     * @return the productMediaRemoteService
     */
    public ProductMediaRemoteService getProductMediaRemoteService() {

        ProductMediaRemoteService productMediaRemoteService = new ProductMediaRemoteServiceImpl();
        productMediaRemoteService.setSoapClient(magentoSoapClient);

        return productMediaRemoteService;
    }

    /**
     * @return the productAttributeRemoteService
     */
    public ProductAttributeRemoteService getProductAttributeRemoteService() {

        ProductAttributeRemoteService productAttributeRemoteService = new ProductAttributeRemoteServiceImpl();
        productAttributeRemoteService.setSoapClient(magentoSoapClient);

        return productAttributeRemoteService;
    }

    /**
     * @return the productRemoteService
     */
    public ProductRemoteService getProductRemoteService() {

        ProductRemoteService productRemoteService = new ProductRemoteServiceImpl();
        productRemoteService.setSoapClient(magentoSoapClient);
        productRemoteService.setCategoryRemoteService(getCategoryRemoteService());
        productRemoteService.setProductMediaRemoteService(getProductMediaRemoteService());
        productRemoteService.setProductLinkRemoteService(getProductLinkRemoteService());

        return productRemoteService;
    }

    /**
     * @return the categoryRemoteService
     */
    public CategoryRemoteService getCategoryRemoteService() {

        CategoryRemoteService categoryRemoteService = new CategoryRemoteServiceImpl();
        categoryRemoteService.setSoapClient(magentoSoapClient);

        return categoryRemoteService;
    }

    /**
     * @return the categoryAttributeRemoteService
     */
    public CategoryAttributeRemoteService getCategoryAttributeRemoteService() {

        CategoryAttributeRemoteService categoryAttributeRemoteService = new CategoryAttributeRemoteServiceImpl();
        categoryAttributeRemoteService.setSoapClient(magentoSoapClient);

        return categoryAttributeRemoteService;
    }
}