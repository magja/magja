/**
 *
 */
package code.google.magja.service;

import code.google.magja.service.category.CategoryRemoteService;
import code.google.magja.service.category.CategoryRemoteServiceImpl;
import code.google.magja.service.product.ProductRemoteService;
import code.google.magja.service.product.ProductRemoteServiceImpl;
import code.google.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
public class RemoteServiceFactory {

	/**
	 * @return the productRemoteService
	 */
	public static ProductRemoteService getProductRemoteService() {

		ProductRemoteService productRemoteService = new ProductRemoteServiceImpl();
		productRemoteService.setSoapClient(MagentoSoapClient.getInstance());
		productRemoteService.setCategoryRemoteService(getCategoryRemoteService());

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

}
