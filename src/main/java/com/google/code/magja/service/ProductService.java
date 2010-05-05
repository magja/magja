/**
 *
 */
package com.google.code.magja.service;

import java.net.URL;
import java.rmi.RemoteException;

import com.google.code.magja.CatalogProductCreateEntity;
import com.google.code.magja.MagjaException;

/**
 * @author andre
 *
 */
public class ProductService extends BaseService {

    public ProductService(URL serviceUrl, String user, String key) {
        super(serviceUrl, user, key);
    }

    public void save(CatalogProductCreateEntity product, String type, String set, String sku) {
        if (!isLoggedIn()) {
            login();
        }

        try {
            getPort().catalogProductCreate(getSessionId(), type, set, sku, product);
        } catch (RemoteException e) {
            throw new MagjaException(e);
        }
    }
}
