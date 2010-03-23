/**
 *
 */
package code.google.magja.service;

import code.google.magja.service.category.CategoryAttributeRemoteService;
import code.google.magja.service.category.CategoryAttributeRemoteServiceImpl;
import code.google.magja.service.category.CategoryRemoteService;
import code.google.magja.service.category.CategoryRemoteServiceImpl;
import code.google.magja.service.country.CountryRemoteService;
import code.google.magja.service.country.CountryRemoteServiceImpl;
import code.google.magja.service.customer.CustomerAddressRemoteService;
import code.google.magja.service.customer.CustomerAddressRemoteServiceImpl;
import code.google.magja.service.customer.CustomerRemoteService;
import code.google.magja.service.customer.CustomerRemoteServiceImpl;
import code.google.magja.service.order.OrderRemoteService;
import code.google.magja.service.order.OrderRemoteServiceImpl;
import code.google.magja.service.product.ProductAttributeRemoteService;
import code.google.magja.service.product.ProductAttributeRemoteServiceImpl;
import code.google.magja.service.product.ProductLinkRemoteService;
import code.google.magja.service.product.ProductLinkRemoteServiceImpl;
import code.google.magja.service.product.ProductMediaRemoteService;
import code.google.magja.service.product.ProductMediaRemoteServiceImpl;
import code.google.magja.service.product.ProductRemoteService;
import code.google.magja.service.product.ProductRemoteServiceImpl;
import code.google.magja.service.region.RegionRemoteService;
import code.google.magja.service.region.RegionRemoteServiceImpl;
import code.google.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
public class RemoteServiceFactory {

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
