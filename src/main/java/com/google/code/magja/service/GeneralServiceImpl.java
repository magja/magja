/**
 *
 */
package com.google.code.magja.service;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public abstract class GeneralServiceImpl<T extends BaseMagentoModel> implements GeneralService<T> {

	protected Boolean debug = false;

	protected MagentoSoapClient soapClient;

	/**
	 * @param soapClient the soapClient to set
	 */
	public void setSoapClient(MagentoSoapClient soapClient) {
		this.soapClient = soapClient;
	}

	/**
	 * test if the product is not null, and if the product id or sku is not null
	 *
	 * @param product
	 * @return
	 */
	protected Boolean validateProduct(Product product) {
		return product != null && (product.getId() != null || product.getSku() != null);
	}

	/**
	 * disable or enable debug informations
	 *
	 * @param Boolean
	 * @return
	 */
	public void setDebug(Boolean b) {
		this.debug = b;
	}

}
