package Soap;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import Magento.ResourcePath;

public class MagentoSoapClient implements SoapClient {

	private SoapCallFactory callFactory;
	private SoapReturnParser returnParser;
	private SoapConfig config;
	private Options connectOptions;
	private String sessionId;
	private static final QName LOGIN_RETURN = new QName("loginReturn");
	private static final QName LOGOUT_RETURN = new QName("endSessionReturn");
	private static final QName CALL_RETURN = new QName("callReturn");

	// @com.google.inject.Inject
	public MagentoSoapClient(SoapCallFactory factory, SoapReturnParser returnParser, SoapConfig config) {
		this.callFactory = factory;
		this.returnParser = returnParser;
		this.config = config;

		connectOptions = new Options();
		connectOptions.setTo(new EndpointReference(config.getRemoteHost()));
		connectOptions.setTransportInProtocol(Constants.TRANSPORT_HTTP);
	}

	@Override
	public Boolean login() throws AxisFault {
		OMElement method = callFactory.createLoginCall(this.config.getApiUser(), this.config.getApiKey());

		ServiceClient sender = new ServiceClient();
		sender.setOptions(connectOptions);
		OMElement result = sender.sendReceive(method);

		String session = result.getFirstChildWithName(LOGIN_RETURN).getText();
		this.sessionId = session;

		return true;
	}

	@Override
	public Boolean logout() throws AxisFault {
		OMElement method = callFactory.createLogoutCall(sessionId);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(connectOptions);
		OMElement result = sender.sendReceive(method);

		return Boolean.parseBoolean(result.getFirstChildWithName(LOGOUT_RETURN).getText());
	}

	@Override
	public Object call(ResourcePath path, Object args) throws AxisFault {
		OMElement method = callFactory.createCall(sessionId, path.getPath(), args);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(connectOptions);
		OMElement result = sender.sendReceive(method);

		return returnParser.parse(result.getFirstChildWithName(CALL_RETURN));
	}

	@Override
	public Object multiCall(List<ResourcePath> path, List<Object> args) throws AxisFault {
		throw new UnsupportedOperationException("not implemented");
	}
}
