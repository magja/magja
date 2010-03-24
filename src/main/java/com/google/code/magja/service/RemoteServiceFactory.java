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

/**
 * @author andre
 *
 */
public class RemoteServiceFactory {

	/**
	 * @return the shipmentRemoteService
	 */
	public static ShipmentRemoteService getShipmentRemoteService() {

		ShipmentRemoteService shipmentRemoteService = new ShipmentRemoteServiceImpl();
		shipmentRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return shipmentRemoteService;
	}

	/**
	 * @return the orderRemoteService
	 */
	public static OrderRemoteService getOrderRemoteService() {

		OrderRemoteService orderRemoteService = new OrderRemoteServiceImpl();
		orderRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return orderRemoteService;
	}

	/**
	 * @return the regionRemoteService
	 */
	public static RegionRemoteService getRegionRemoteService() {

		RegionRemoteService regionRemoteService = new RegionRemoteServiceImpl();
		regionRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return regionRemoteService;
	}

	/**
	 * @return the countryRemoteService
	 */
	public static CountryRemoteService getCountryRemoteService() {

		CountryRemoteService countryRemoteService = new CountryRemoteServiceImpl();
		countryRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return countryRemoteService;
	}

	/**
	 * @return the customerAddressRemoteService
	 */
	public static CustomerAddressRemoteService getCustomerAddressRemoteService() {

		CustomerAddressRemoteService customerAddressRemoteService = new CustomerAddressRemoteServiceImpl();
		customerAddressRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return customerAddressRemoteService;
	}

	/**
	 * @return the customerRemoteService
	 */
	public static CustomerRemoteService getCustomerRemoteService() {

		CustomerRemoteService customerRemoteService = new CustomerRemoteServiceImpl();
		customerRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return customerRemoteService;
	}

	/**
	 * @return the productLinkRemoteService
	 */
	public static ProductLinkRemoteService getProductLinkRemoteService() {

		ProductLinkRemoteService productLinkRemoteService = new ProductLinkRemoteServiceImpl();
		productLinkRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return productLinkRemoteService;
	}

	/**
	 * @return the productMediaRemoteService
	 */
	public static ProductMediaRemoteService getProductMediaRemoteService() {

		ProductMediaRemoteService productMediaRemoteService = new ProductMediaRemoteServiceImpl();
		productMediaRemoteService
				.setSoapClient(MagentoSoapClient.getInstance());

		return productMediaRemoteService;
	}

	/**
	 * @return the productAttributeRemoteService
	 */
	public static ProductAttributeRemoteService getProductAttributeRemoteService() {

		ProductAttributeRemoteService productAttributeRemoteService = new ProductAttributeRemoteServiceImpl();
		productAttributeRemoteService.setSoapClient(MagentoSoapClient
				.getInstance());

		return productAttributeRemoteService;
	}

	/**
	 * @return the productRemoteService
	 */
	public static ProductRemoteService getProductRemoteService() {

		ProductRemoteService productRemoteService = new ProductRemoteServiceImpl();
		productRemoteService.setSoapClient(MagentoSoapClient.getInstance());
		productRemoteService
				.setCategoryRemoteService(getCategoryRemoteService());
		productRemoteService
				.setProductMediaRemoteService(getProductMediaRemoteService());
		productRemoteService
				.setProductLinkRemoteService(getProductLinkRemoteService());

		return productRemoteService;
	}

	/**
	 * @return the categoryRemoteService
	 */
	public static CategoryRemoteService getCategoryRemoteService() {

		CategoryRemoteService categoryRemoteService = new CategoryRemoteServiceImpl();
		categoryRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return categoryRemoteService;
	}

	/**
	 * @return the categoryAttributeRemoteService
	 */
	public static CategoryAttributeRemoteService getCategoryAttributeRemoteService() {

		CategoryAttributeRemoteService categoryAttributeRemoteService = new CategoryAttributeRemoteServiceImpl();
		categoryAttributeRemoteService.setSoapClient(MagentoSoapClient.getInstance());

		return categoryAttributeRemoteService;
	}

}
