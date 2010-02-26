package Soap;

import java.util.List;

import org.apache.axis2.AxisFault;

import Magento.ResourcePath;

public interface SoapClient {

	public Boolean login() throws AxisFault;

	public Boolean logout() throws AxisFault;

	public Object call(ResourcePath path, Object args) throws AxisFault;

	public Object multiCall(List<ResourcePath> path, List<Object> args) throws AxisFault;
}
