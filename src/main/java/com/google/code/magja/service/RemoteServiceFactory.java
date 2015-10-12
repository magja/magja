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
import com.google.code.magja.service.order.InvoiceRemoteService;
import com.google.code.magja.service.order.InvoiceRemoteServiceImpl;
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
 * WARNING: Currently this is a "singleton" object.
 * 
 * @author andre.fabbro
 */
public class RemoteServiceFactory {
	
	private MagentoSoapClient magentoClient;
	
	private static RemoteServiceFactory singletonInstance;
	
	private OrderRemoteService orderRemoteService;
	private ShipmentRemoteService shipmentRemoteService;
	private RegistryRemoteService registryRemoteService;
	private RegionRemoteService regionRemoteService;
	private CountryRemoteService countryRemoteService;
	private CustomerAddressRemoteService customerAddressRemoteService;
	private CustomerRemoteService customerRemoteService;
	private ProductLinkRemoteService productLinkRemoteService;
	private ProductMediaRemoteService productMediaRemoteService;
	private ProductAttributeRemoteService productAttributeRemoteService;
	private ProductRemoteService productRemoteService;
	private CategoryRemoteService categoryRemoteService;
	private CategoryAttributeRemoteService categoryAttributeRemoteService;
	private CartRemoteService cartRemoteService;
	
    public RemoteServiceFactory(MagentoSoapClient magentoClient) {
		super();
		this.magentoClient = magentoClient;
	}
    
    @Deprecated
    public static RemoteServiceFactory getSingleton() {
    	return getSingleton(null);
    }

    @Deprecated
    public static RemoteServiceFactory getSingleton(SoapConfig soapConfig) {
    	if (singletonInstance == null) {
    		singletonInstance = new RemoteServiceFactory(MagentoSoapClient.getInstance(soapConfig));
    	}
    	return singletonInstance;
    }

	/**
     * @return the shipmentRemoteService
     */
    public InvoiceRemoteService getInvoiceRemoteService() {
        InvoiceRemoteService invoiceRemoteService = new InvoiceRemoteServiceImpl(magentoClient);
        return invoiceRemoteService;
    }

    /**
     * @return the shipmentRemoteService
     */
    public RegistryRemoteService getRegistryRemoteService() {
    	if (registryRemoteService == null) {
    		registryRemoteService = new RegistryRemoteServiceImpl(magentoClient);
    	}
        return registryRemoteService;
    }

    /**
     * @return the shipmentRemoteService
     */
    public ShipmentRemoteService getShipmentRemoteService() {
    	if (shipmentRemoteService == null) {
    		shipmentRemoteService = new ShipmentRemoteServiceImpl(magentoClient);
    	}
        return shipmentRemoteService;
    }

    /**
     * @return the orderRemoteService
     */
    public OrderRemoteService getOrderRemoteService() {
    	if (orderRemoteService == null) {
    		orderRemoteService = new OrderRemoteServiceImpl(magentoClient);
    	}
        return orderRemoteService;
    }

    /**
     * @return the regionRemoteService
     */
    public RegionRemoteService getRegionRemoteService() {
    	if (regionRemoteService == null) {
    		regionRemoteService = new RegionRemoteServiceImpl(magentoClient);
    	}
        return regionRemoteService;
    }

    /**
     * @return the countryRemoteService
     */
    public CountryRemoteService getCountryRemoteService() {
    	if (countryRemoteService == null) {
    		countryRemoteService = new CountryRemoteServiceImpl(magentoClient);
    	}
        return countryRemoteService;
    }

    /**
     * @return the customerAddressRemoteService
     */
    public CustomerAddressRemoteService getCustomerAddressRemoteService() {
    	if (customerAddressRemoteService == null) {
    		customerAddressRemoteService = new CustomerAddressRemoteServiceImpl(magentoClient);
    	}
        return customerAddressRemoteService;
    }

    /**
     * @return the customerRemoteService
     */
    public CustomerRemoteService getCustomerRemoteService() {
    	if (customerRemoteService == null) {
    		customerRemoteService = new CustomerRemoteServiceImpl(magentoClient);
    	}
        return customerRemoteService;
    }

    /**
     * @return the productLinkRemoteService
     */
    public ProductLinkRemoteService getProductLinkRemoteService() {
    	if (productLinkRemoteService == null) {
    		productLinkRemoteService = new ProductLinkRemoteServiceImpl(magentoClient);
    	}
        return productLinkRemoteService;
    }

    /**
     * @return the productMediaRemoteService
     */
    public ProductMediaRemoteService getProductMediaRemoteService() {
    	if (productMediaRemoteService == null) {
    		productMediaRemoteService = new ProductMediaRemoteServiceImpl(magentoClient);
    	}
        return productMediaRemoteService;
    }

    /**
     * @return the productAttributeRemoteService
     */
    public ProductAttributeRemoteService getProductAttributeRemoteService() {
    	if (productAttributeRemoteService == null) {
    		productAttributeRemoteService = new ProductAttributeRemoteServiceImpl(magentoClient);
    	}
        return productAttributeRemoteService;
    }

    /**
     * @return the productRemoteService
     */
    public ProductRemoteService getProductRemoteService() {
    	if (productRemoteService == null) {
    		productRemoteService = new ProductRemoteServiceImpl(magentoClient, this);
    	}
        return productRemoteService;

    }

    /**
     * @return the categoryRemoteService
     */
    public CategoryRemoteService getCategoryRemoteService() {
    	if (categoryRemoteService == null) {
    		categoryRemoteService = new CategoryRemoteServiceImpl(magentoClient, this);
    	}
        return categoryRemoteService;
    }

    /**
     * @return the categoryAttributeRemoteService
     */
    public CategoryAttributeRemoteService getCategoryAttributeRemoteService() {
    	if (categoryAttributeRemoteService == null) {
    		categoryAttributeRemoteService = new CategoryAttributeRemoteServiceImpl(magentoClient);
    	}
        return categoryAttributeRemoteService;
    }

    /**
     * @return the cartRemoteService
     */
    public CartRemoteService getCartRemoteService() {
    	if (cartRemoteService == null) {
    		cartRemoteService = new CartRemoteServiceImpl(magentoClient);
    	}
        return cartRemoteService;
    }

}