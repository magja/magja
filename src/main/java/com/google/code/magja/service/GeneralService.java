/**
 * @author andre
 *
 */
package com.google.code.magja.service;

import java.io.Serializable;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.soap.MagentoSoapClient;

@SuppressWarnings("rawtypes")
public interface GeneralService<T extends BaseMagentoModel> extends Serializable {

	public abstract MagentoSoapClient getSoapClient();

	public abstract void setDebug(Boolean b);
}
