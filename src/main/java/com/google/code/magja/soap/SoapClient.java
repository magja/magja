package com.google.code.magja.soap;

import java.util.List;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;

public interface SoapClient {

	/**
	 * Call Magento API with multiple arguments.
	 * @param path
	 * @param args
	 * @return
	 * @throws AxisFault
	 */
	public abstract <R> R callArgs(ResourcePath path, Object[] args) throws AxisFault;
	/**
	 * Call Magento API with single argument.
	 * @param path
	 * @param arg
	 * @return
	 * @throws AxisFault
	 */
	public abstract <T, R> R callSingle(ResourcePath path, T arg) throws AxisFault;

	public abstract Object multiCall(List<ResourcePath> path, List<Object> args) throws AxisFault;
}
