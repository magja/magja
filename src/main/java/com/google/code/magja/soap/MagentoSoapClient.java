package com.google.code.magja.soap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.utils.PropertyLoader;

public class MagentoSoapClient implements SoapClient {

    public static final String CONFIG_PROPERTIES_FILE = "magento-api";
    private static final QName LOGIN_RETURN = new QName("loginReturn");
    private static final QName LOGOUT_RETURN = new QName("endSessionReturn");
    private static final QName CALL_RETURN = new QName("callReturn");
    private SoapCallFactory callFactory;
    private SoapReturnParser returnParser;
    private SoapConfig config;
    private Options connectOptions;
    private String sessionId;
    private ServiceClient sender;

    /**
     * On the constructor, we will perform the login to magento API, to keep the
     * same session for all operations, if the user want to change the login
     * parameters just have to use the appropriate method
     */
    public MagentoSoapClient() {

        java.util.Properties magentoapi = PropertyLoader.loadProperties(CONFIG_PROPERTIES_FILE);

        callFactory = new SoapCallFactory();
        returnParser = new SoapReturnParser();
        config = new SoapConfig(magentoapi);

    }

    /**
     * Construct soap client using given configuration
     * @param soapConfig
     */
    public MagentoSoapClient(SoapConfig soapConfig) {
        config = soapConfig;
    }

    /**
     * @return the config
     */
    public SoapConfig getConfig() {
        return config;
    }

    /**
     * Use to change the connection parameters to API (url, username, password)
     *
     * @param config
     *            the config to set
     */
    public void setConfig(SoapConfig config) throws AxisFault {
        this.config = config;
        login();
    }

    public Object call(ResourcePath path, Object args) throws AxisFault {

        // login before calls
        if(!isLoggedIn()) {
            login();
        }

        OMElement method = callFactory.createCall(sessionId, path.getPath(),
                args);
        OMElement result = sender.sendReceive(method);

        return returnParser.parse(result.getFirstChildWithName(CALL_RETURN));
    }

    public Object multiCall(List<ResourcePath> path, List<Object> args)
            throws AxisFault {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Connect to the service using the current config
     */
    protected void login() throws AxisFault {

        if( isLoggedIn() ) {
            logout();
        }

        connectOptions = new Options();
        connectOptions.setTo(new EndpointReference(config.getRemoteHost()));
        connectOptions.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        // to use the same tcp connection for multiple calls
        // workaround:
        // http://amilachinthaka.blogspot.com/2009/05/improving-axis2-client-http-transport.html
        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(httpConnectionManager);
        connectOptions.setProperty(HTTPConstants.REUSE_HTTP_CLIENT,
                Constants.VALUE_TRUE);
        connectOptions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT,
                httpClient);

        sender = new ServiceClient();
        sender.setOptions(connectOptions);

        OMElement loginMethod = callFactory.createLoginCall(this.config.getApiUser(), this.config.getApiKey());
        OMElement loginResult = sender.sendReceive(loginMethod);

        String session = loginResult.getFirstChildWithName(LOGIN_RETURN).getText();
    }

    /**
     * Logout from service, throws logout exception if failed
     * @throws AxisFault
     */
    protected void logout() throws AxisFault {

        // first, we need to logout the previous session
        OMElement logoutMethod = callFactory.createLogoutCall(sessionId);
        OMElement logoutResult = sender.sendReceive(logoutMethod);
        Boolean logoutSuccess = Boolean.parseBoolean(logoutResult.getFirstChildWithName(
                LOGOUT_RETURN).getText());
        if (!logoutSuccess) {
            throw new RuntimeException("Error logging out");
        }
        sessionId = null;
    }

    /**
     * Are we currently logged in?
     */
    protected Boolean isLoggedIn() {
        return sessionId != null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        // close the connection to magento api
        // first, we need to logout the previous session
        OMElement logoutMethod = callFactory.createLogoutCall(sessionId);
        OMElement logoutResult = sender.sendReceive(logoutMethod);
        Boolean logoutSuccess = Boolean.parseBoolean(logoutResult.getFirstChildWithName(
                LOGOUT_RETURN).getText());

        super.finalize();
    }
}
