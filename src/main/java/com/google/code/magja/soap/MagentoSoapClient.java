/**
 * @author ???
 * 27-08-2011 Multi-instance support - marcolopes@netc.pt
 *
 */
package com.google.code.magja.soap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private final SoapCallFactory callFactory;
    private final SoapReturnParser returnParser;
    private SoapConfig config;
    private Options connectOptions;
    private String sessionId;
    private ServiceClient sender;

    //holds all the created instances by creation order
    private static Map<SoapConfig, MagentoSoapClient> INSTANCES=new LinkedHashMap();

    /**
     * The default constructor for custom connections
     */
    public MagentoSoapClient() {
    	this(new SoapConfig(PropertyLoader.loadProperties(CONFIG_PROPERTIES_FILE)));
    }

    /**
     * Construct soap client using given configuration
     * @param soapConfig
     */
    public MagentoSoapClient(SoapConfig soapConfig) {
        config = soapConfig;
        callFactory = new SoapCallFactory();
        returnParser = new SoapReturnParser();
        try {
            login();
            INSTANCES.put(soapConfig, this);
        } catch (AxisFault e) {
            // do not swallow, rethrow as runtime
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the FIRST created instance
     */
    public static MagentoSoapClient getInstance() {
    	if (INSTANCES.size()==0){
    		return new MagentoSoapClient();
    	}
    	return INSTANCES.values().iterator().next();
    }

    /**
     * @return the already created instance or a newly created one
     * in case it does not exist
     */
    public static MagentoSoapClient getInstance(SoapConfig soapConfig) {
    	if (!INSTANCES.containsKey(soapConfig)){
    		return new MagentoSoapClient(soapConfig);
    	}
		return INSTANCES.get(soapConfig);
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

        if (isLoggedIn()) logout();

        connectOptions = new Options();
        connectOptions.setTo(new EndpointReference(config.getRemoteHost()));
        connectOptions.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        connectOptions.setTimeOutInMilliSeconds(60000);

        // to use the same tcp connection for multiple calls
        // workaround:
        // http://amilachinthaka.blogspot.com/2009/05/improving-axis2-client-http-transport.html
        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(httpConnectionManager);
        connectOptions.setProperty(HTTPConstants.REUSE_HTTP_CLIENT,
                Constants.VALUE_TRUE);
        connectOptions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT,
                httpClient);
        connectOptions.setProperty(HTTPConstants.HTTP_PROTOCOL_VERSION,
        		HTTPConstants.HEADER_PROTOCOL_10);

        sender = new ServiceClient();
        sender.setOptions(connectOptions);

        OMElement loginMethod = callFactory.createLoginCall(this.config.getApiUser(), this.config.getApiKey());
        OMElement loginResult = sender.sendReceive(loginMethod);

        sessionId = loginResult.getFirstChildWithName(LOGIN_RETURN).getText();
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
        try {
            sender.cleanupTransport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finalize();
    }
}
