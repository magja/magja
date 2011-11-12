package com.google.code.magja.service;

import com.google.code.magja.service.cart.CartRemoteService;
import com.google.code.magja.service.cart.CartRemoteServiceImpl;
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
import com.google.code.magja.service.order.*;
import com.google.code.magja.service.product.*;
import com.google.code.magja.service.region.RegionRemoteService;
import com.google.code.magja.service.region.RegionRemoteServiceImpl;
import com.google.code.magja.service.registry.RegistryRemoteService;
import com.google.code.magja.service.registry.RegistryRemoteServiceImpl;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.soap.SoapConfig;

/**
 * @author andre.fabbro
 */
public abstract class RemoteServiceFactory {

    /**
     * @return the shipmentRemoteService
     */
    public static InvoiceRemoteService getInvoiceRemoteService() {
        return getInvoiceRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the shipmentRemoteService
     */
    public static InvoiceRemoteService getInvoiceRemoteService(
            SoapConfig soapConfig) {

        InvoiceRemoteService invoiceRemoteService = new InvoiceRemoteServiceImpl();
        invoiceRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return invoiceRemoteService;
    }

    /**
     * @return the shipmentRemoteService
     */
    public static RegistryRemoteService getRegistryRemoteService() {
        return getRegistryRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the shipmentRemoteService
     */
    public static RegistryRemoteService getRegistryRemoteService(
            SoapConfig soapConfig) {

        RegistryRemoteService registryRemoteService = new RegistryRemoteServiceImpl();
        registryRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return registryRemoteService;
    }

    /**
     * @return the shipmentRemoteService
     */
    public static ShipmentRemoteService getShipmentRemoteService() {
        return getShipmentRemoteService(null);
    }

    /**
     * @return the shipmentRemoteService
     */
    public static ShipmentRemoteService getShipmentRemoteService(
            SoapConfig soapConfig) {

        ShipmentRemoteService shipmentRemoteService = new ShipmentRemoteServiceImpl();
        shipmentRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return shipmentRemoteService;
    }

    /**
     * @return the orderRemoteService
     */
    public static OrderRemoteService getOrderRemoteService() {
        return getOrderRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the orderRemoteService
     */
    public static OrderRemoteService getOrderRemoteService(SoapConfig soapConfig) {

        OrderRemoteService orderRemoteService = new OrderRemoteServiceImpl();
        orderRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return orderRemoteService;
    }

    /**
     * @return the regionRemoteService
     */
    public static RegionRemoteService getRegionRemoteService() {
        return getRegionRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the regionRemoteService
     */
    public static RegionRemoteService getRegionRemoteService(
            SoapConfig soapConfig) {

        RegionRemoteService regionRemoteService = new RegionRemoteServiceImpl();
        regionRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return regionRemoteService;
    }

    /**
     * @return the countryRemoteService
     */
    public static CountryRemoteService getCountryRemoteService() {
        return getCountryRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the countryRemoteService
     */
    public static CountryRemoteService getCountryRemoteService(
            SoapConfig soapConfig) {

        CountryRemoteService countryRemoteService = new CountryRemoteServiceImpl();
        countryRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return countryRemoteService;
    }

    /**
     * @return the customerAddressRemoteService
     */
    public static CustomerAddressRemoteService getCustomerAddressRemoteService() {
        return getCustomerAddressRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the customerAddressRemoteService
     */
    public static CustomerAddressRemoteService getCustomerAddressRemoteService(
            SoapConfig soapConfig) {

        CustomerAddressRemoteService customerAddressRemoteService = new CustomerAddressRemoteServiceImpl();
        customerAddressRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return customerAddressRemoteService;
    }

    /**
     * @return the customerRemoteService
     */
    public static CustomerRemoteService getCustomerRemoteService() {
        return getCustomerRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the customerRemoteService
     */
    public static CustomerRemoteService getCustomerRemoteService(
            SoapConfig soapConfig) {

        CustomerRemoteService customerRemoteService = new CustomerRemoteServiceImpl();
        customerRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return customerRemoteService;
    }

    /**
     * @return the productLinkRemoteService
     */
    public static ProductLinkRemoteService getProductLinkRemoteService() {
        return getProductLinkRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the productLinkRemoteService
     */
    public static ProductLinkRemoteService getProductLinkRemoteService(
            SoapConfig soapConfig) {

        ProductLinkRemoteService productLinkRemoteService = new ProductLinkRemoteServiceImpl();
        productLinkRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return productLinkRemoteService;
    }

    /**
     * @return the productMediaRemoteService
     */
    public static ProductMediaRemoteService getProductMediaRemoteService() {
        return getProductMediaRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the productMediaRemoteService
     */
    public static ProductMediaRemoteService getProductMediaRemoteService(
            SoapConfig soapConfig) {

        ProductMediaRemoteService productMediaRemoteService = new ProductMediaRemoteServiceImpl();
        productMediaRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return productMediaRemoteService;
    }

    /**
     * @return the productAttributeRemoteService
     */
    public static ProductAttributeRemoteService getProductAttributeRemoteService() {
        return getProductAttributeRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the productAttributeRemoteService
     */
    public static ProductAttributeRemoteService getProductAttributeRemoteService(
            SoapConfig soapConfig) {

        ProductAttributeRemoteService productAttributeRemoteService = new ProductAttributeRemoteServiceImpl();
        productAttributeRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return productAttributeRemoteService;
    }

    /**
     * @return the productRemoteService
     */
    public static ProductRemoteService getProductRemoteService() {
        return getProductRemoteService(null, getCategoryRemoteService());
    }

    /**
     * @param soapConfig
     * @return the productRemoteService
     */
    public static ProductRemoteService getProductRemoteService(
            SoapConfig soapConfig) {
        return getProductRemoteService(soapConfig,
                getCategoryRemoteService(soapConfig));
    }

    /**
     * @param soapConfig
     * @param categoryRemoteService
     * @return the productRemoteService
     */
    public static ProductRemoteService getProductRemoteService(
            SoapConfig soapConfig, CategoryRemoteService categoryRemoteService) {

        ProductRemoteService productRemoteService = new ProductRemoteServiceImpl();
        productRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        productRemoteService.setCategoryRemoteService(categoryRemoteService);
        productRemoteService
                .setProductMediaRemoteService(getProductMediaRemoteService(soapConfig));
        productRemoteService
                .setProductLinkRemoteService(getProductLinkRemoteService(soapConfig));

        return productRemoteService;
    }

    /**
     * @return the categoryRemoteService
     */
    public static CategoryRemoteService getCategoryRemoteService() {
        return getCategoryRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the categoryRemoteService
     */
    public static CategoryRemoteService getCategoryRemoteService(
            SoapConfig soapConfig) {

        CategoryRemoteService categoryRemoteService = new CategoryRemoteServiceImpl();
        categoryRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        ProductRemoteService productRemoteService = getProductRemoteService(
                soapConfig, categoryRemoteService);
        categoryRemoteService.setProductRemoteService(productRemoteService);

        return categoryRemoteService;
    }

    /**
     * @return the categoryAttributeRemoteService
     */
    public static CategoryAttributeRemoteService getCategoryAttributeRemoteService() {
        return getCategoryAttributeRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the categoryAttributeRemoteService
     */
    public static CategoryAttributeRemoteService getCategoryAttributeRemoteService(
            SoapConfig soapConfig) {

        CategoryAttributeRemoteService categoryAttributeRemoteService = new CategoryAttributeRemoteServiceImpl();
        categoryAttributeRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return categoryAttributeRemoteService;
    }

    /**
     * @return the cartRemoteService
     */
    public static CartRemoteService getCartRemoteService() {
        return getCartRemoteService(null);
    }

    /**
     * @param soapConfig
     * @return the cartRemoteService
     */
    public static CartRemoteService getCartRemoteService(SoapConfig soapConfig) {
        CartRemoteService cartRemoteService = new CartRemoteServiceImpl();
        cartRemoteService.setSoapClient(MagentoSoapClient
                .getInstance(soapConfig));

        return cartRemoteService;
    }
}