/**
 *
 */
package code.google.magja.service;

import code.google.magja.model.BaseMagentoModel;
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

}
