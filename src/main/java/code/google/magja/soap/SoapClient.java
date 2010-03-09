package code.google.magja.soap;

import java.util.List;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;

public interface SoapClient {

	public abstract Object call(ResourcePath path, Object args) throws AxisFault;

	public abstract Object multiCall(List<ResourcePath> path, List<Object> args) throws AxisFault;
}
