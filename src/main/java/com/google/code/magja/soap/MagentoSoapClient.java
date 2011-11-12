/**
 * @author ???
 * 2011-08-27 Multi-instance support - marcolopes@netc.pt
 *
 */
package com.google.code.magja.soap;

import com.google.code.magja.magento.ResourcePath;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MagentoSoapClient implements SoapClient {

    public static final String CONFIG_PROPERTIES_FILE = "magento-api";

    private static final QName LOGIN_RETURN = new QName("loginReturn");

    private static final QName LOGOUT_RETURN = new QName("endSessionReturn");

    private static final QName CALL_RETURN = new QName("callReturn");

    private String configFile;

    private SoapCallFactory callFactory;

    private SoapReturnParser returnParser;

    private SoapConfig config;

    private Options connectOptions;

    private String sessionId;

    private ServiceClient sender;

    private Long lastCall = new Date().getTime();

    private static final Log log = LogFactory.getLog(MagentoSoapClient.class);


    // holds all the created instances by creation order, Multiton Pattern
    private static final Map<SoapConfig, MagentoSoapClient> INSTANCES = new LinkedHashMap<SoapConfig, MagentoSoapClient>();

    /**
     * Returns the default instance, or a newly created one from the
     * magento-api.properties file, if there is no default instance. The default
     * instance is the first one created.
     *
     * @return the default instance or a newly created one
     */
    public static MagentoSoapClient getInstance() {
        return (INSTANCES.size() == 0) ? getInstance(null) : INSTANCES.values()
                .iterator().next();
    }

    /**
     * Returns the instance that was created with the specified configuration or
     * create one if the instance does not exist.
     *
     * @return the already created instance or a new one
     */
    public static MagentoSoapClient getInstance(SoapConfig soapConfig) {

        // if has default instance and soapConfig is null
        if (INSTANCES.size() > 0 && soapConfig == null)
            return INSTANCES.values().iterator().next();

        synchronized (INSTANCES) {


            if (soapConfig == null) {
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/MagentoServiceContext.xml", MagentoSoapClient.class);
                soapConfig = (SoapConfig) context.getBean("soapConfig");

            }

            MagentoSoapClient instance = INSTANCES.get(soapConfig);
            if (instance == null) {
                instance = new MagentoSoapClient(soapConfig);
                INSTANCES.put(soapConfig, instance);
            }
            return instance;
        }
    }

    /**
     * Construct soap client using given configuration
     *
     * @param soapConfig
     */
    private MagentoSoapClient(SoapConfig soapConfig) {
        config = soapConfig;
        callFactory = new SoapCallFactory();
        returnParser = new SoapReturnParser();
        try {
            login();
        } catch (AxisFault e) {
            // do not swallow, rethrow as runtime
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the config
     */
    public SoapConfig getConfig() {
        return config;
    }

    /**
     * Use to change the connection parameters to API (apiUser, apiKey,
     * remoteHost)
     *
     * @param config the config to set
     */
    @Deprecated
    public void setConfig(SoapConfig config) throws AxisFault {
        this.config = config;
        login();
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * com.google.code.magja.soap.SoapClient#call(com.google.code.magja.magento
      * .ResourcePath, java.lang.Object)
      */
    @Override
    public Object call(ResourcePath path, Object args) throws AxisFault {

        lastCall = new Date().getTime();
        OMElement method = callFactory.createCall(sessionId, path.getPath(),
                args);
        OMElement result = null;
        try {
            result = sender.sendReceive(method);
        } catch (AxisFault axisFault) {
        	if (axisFault.getMessage().toUpperCase().indexOf("SESSION EXPIRED") >= 0) {
                log.info("call session expired: ", axisFault);
                login();
                result = sender.sendReceive(method);
            } else {
                throw axisFault;
            }
        }

        return returnParser.parse(result.getFirstChildWithName(CALL_RETURN));
    }

    /*


      * (non-Javadoc)
      *
      * @see com.google.code.magja.soap.SoapClient#multiCall(java.util.List,
      * java.util.List)
      */
    @Override
    public Object multiCall(List<ResourcePath> path, List<Object> args)
            throws AxisFault {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Connect to the service using the current config
     */
    protected void login() throws AxisFault {

        if (isLoggedIn())
            logout();

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
        connectOptions
                .setProperty(HTTPConstants.CACHED_HTTP_CLIENT, httpClient);
        connectOptions.setProperty(HTTPConstants.HTTP_PROTOCOL_VERSION,
                HTTPConstants.HEADER_PROTOCOL_10);

        sender = new ServiceClient();
        sender.setOptions(connectOptions);

        OMElement loginMethod = callFactory.createLoginCall(
                this.config.getApiUser(), this.config.getApiKey());
        log.info("====================================== Logging in user: " + this.config.getApiUser());
        OMElement loginResult = sender.sendReceive(loginMethod);

        sessionId = loginResult.getFirstChildWithName(LOGIN_RETURN).getText();
        log.info("====================================== Logged in sessionId: " + sessionId);
        lastCall = new Date().getTime();
    }

    /**
     * Logout from service, throws logout exception if failed
     *
     * @throws AxisFault
     */
    protected void logout() throws AxisFault {

        log.info("====================================== Log out sessionId: " + sessionId);
        // first, we need to logout the previous session
    	try {
            OMElement logoutMethod = callFactory.createLogoutCall(sessionId);
            OMElement logoutResult = sender.sendReceive(logoutMethod);
            Boolean logoutSuccess = Boolean.parseBoolean(logoutResult
                    .getFirstChildWithName(LOGOUT_RETURN).getText());
            if (!logoutSuccess) {
                throw new RuntimeException("Error logging out");
            }
    	}
    	catch (AxisFault axisFault) {
        	if (axisFault.getMessage().toUpperCase().indexOf("SESSION EXPIRED") < 0) {
        		throw axisFault;
        	}
            log.info("logout session expired: ", axisFault);
    	}
        log.info("====================================== Logging out user: " + this.config.getApiUser());
        sessionId = null;
    }

    /**
     * Are we currently logged in?
     */
    protected Boolean isLoggedIn() {
        return sessionId != null;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#finalize()
      */
    @Override
    protected void finalize() throws Throwable {
        // close the connection to magento api
        // first, we need to logout the previous session
        OMElement logoutMethod = callFactory.createLogoutCall(sessionId);
        OMElement logoutResult = sender.sendReceive(logoutMethod);
        Boolean logoutSuccess = Boolean.parseBoolean(logoutResult
                .getFirstChildWithName(LOGOUT_RETURN).getText());
        try {
            sender.cleanupTransport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finalize();
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
