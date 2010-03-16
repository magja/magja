/**
 *
 */
package code.google.magja.service;

import code.google.magja.model.BaseMagentoModel;
import code.google.magja.model.product.Product;
import code.google.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public abstract class GeneralServiceImpl<T extends BaseMagentoModel> implements GeneralService<T> {

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
	protected Boolean validadeProduct(Product product) {
		if (product == null)
			return false;
		else if (product.getId() != null || product.getSku() != null)
			return true;
		else
			return false;
	}

}
