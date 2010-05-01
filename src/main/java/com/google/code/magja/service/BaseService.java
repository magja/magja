package com.google.code.magja.service;

import com.google.code.magja.MagjaException;
import com.google.code.magja.Mage_Api_Model_Server_V2_HandlerPortType;
import com.google.code.magja.MagentoService;
import com.google.code.magja.MagentoServiceLocator;

import java.net.URL;

/**
 *
 * @author kwilson
 */
public abstract class BaseService {

    protected final MagentoService serviceLocator;
    private final URL serviceUrl;
    private final String user;
    private final String key;
    private String sessionId;

    public BaseService(URL serviceUrl, String user, String key) {
        this.user = user;
        this.key = key;
        this.serviceUrl = serviceUrl;
        this.serviceLocator = new MagentoServiceLocator();
    }

    /**
     * 
     * @return
     */
    protected Mage_Api_Model_Server_V2_HandlerPortType getPort() {
        try {
            return serviceLocator.getMage_Api_Model_Server_V2_HandlerPort(this.serviceUrl);
        } catch (Exception ex) {
            throw new MagjaException(ex);
        }
    }

    /**
     * Login to the serviceLocator, does nothing if the user is already logged in
     */
    protected void login() {
        if (!isLoggedIn()) {
            try {
                sessionId = getPort().login(user, key);
            } catch (Exception ex) {
                throw new MagjaException(ex);
            }
        }
    }

    /**
     * Logout of the serviceLocator
     */
    protected void logout() {
        if (isLoggedIn()) {
            try {
                getPort().endSession(sessionId);
            } catch (Exception ex) {
                throw new MagjaException(ex);
            } finally {
                sessionId = null;
            }
        }
    }

    /**
     * Are we logged in?
     * @return
     */
    protected Boolean isLoggedIn() {
        return this.sessionId != null;
    }

    protected String getSessionId() {
        return sessionId;
    }

    @Override
    protected void finalize() throws Throwable {
        logout();
    }
    
}
