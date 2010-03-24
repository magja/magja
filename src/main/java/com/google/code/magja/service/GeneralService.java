/**
 *
 */
package code.google.magja.service;

import java.io.Serializable;

import code.google.magja.model.BaseMagentoModel;
import code.google.magja.soap.MagentoSoapClient;

/**
 * @author andre
 *
 */
public interface GeneralService<T extends BaseMagentoModel> extends Serializable {

	public abstract void setSoapClient(MagentoSoapClient soapClient);

}
