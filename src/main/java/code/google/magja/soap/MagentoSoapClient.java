package code.google.magja.soap;

import java.util.List;

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

import code.google.magja.magento.ResourcePath;

public class MagentoSoapClient implements SoapClient {

	private static final QName LOGIN_RETURN = new QName("loginReturn");

	private static final QName LOGOUT_RETURN = new QName("endSessionReturn");

	private static final QName CALL_RETURN = new QName("callReturn");

	private SoapCallFactory callFactory;

	private SoapReturnParser returnParser;

	private SoapConfig config;

	private Options connectOptions;

	private String sessionId;

	private ServiceClient sender;


	// @com.google.inject.Inject
	public MagentoSoapClient(SoapCallFactory factory, SoapReturnParser returnParser, SoapConfig config) {
		this.callFactory = factory;
		this.returnParser = returnParser;
		this.config = config;

		try {
			this.sender = new ServiceClient();
		} catch (AxisFault e) {
			this.sender = null;
		}

        connectOptions = new Options();
        connectOptions.setTo(new EndpointReference(config.getRemoteHost()));
        connectOptions.setTransportInProtocol(Constants.TRANSPORT_HTTP);

        // to use the same tcp connection for multiple calls (http://code.google.com/p/magja/issues/detail?id=4)
        // workaround: http://amilachinthaka.blogspot.com/2009/05/improving-axis2-client-http-transport.html
        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(httpConnectionManager);
        connectOptions.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, Constants.VALUE_TRUE);
        connectOptions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, httpClient);
        if(sender != null) this.sender.setOptions(connectOptions);
	}

	@Override
	public Boolean login() throws AxisFault {
		OMElement method = callFactory.createLoginCall(this.config.getApiUser(), this.config.getApiKey());

		if(sender == null) {
        	sender = new ServiceClient();
        	sender.setOptions(connectOptions);
        }
		OMElement result = sender.sendReceive(method);

		String session = result.getFirstChildWithName(LOGIN_RETURN).getText();
		this.sessionId = session;

		return true;
	}

	@Override
	public Boolean logout() throws AxisFault {
		OMElement method = callFactory.createLogoutCall(sessionId);

		if(sender == null) {
        	sender = new ServiceClient();
        	sender.setOptions(connectOptions);
        }
		OMElement result = sender.sendReceive(method);

		return Boolean.parseBoolean(result.getFirstChildWithName(LOGOUT_RETURN).getText());
	}

	@Override
	public Object call(ResourcePath path, Object args) throws AxisFault {
		OMElement method = callFactory.createCall(sessionId, path.getPath(), args);

		if(sender == null) {
        	sender = new ServiceClient();
        	sender.setOptions(connectOptions);
        }
		OMElement result = sender.sendReceive(method);

		return returnParser.parse(result.getFirstChildWithName(CALL_RETURN));
	}

	@Override
	public Object multiCall(List<ResourcePath> path, List<Object> args) throws AxisFault {
		throw new UnsupportedOperationException("not implemented");
	}
}
