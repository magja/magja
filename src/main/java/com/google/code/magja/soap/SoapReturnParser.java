package com.google.code.magja.soap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;

public class SoapReturnParser {

	private static final String XSD_STRING = ":string";
	private static final String XSD_INTEGER = ":int";
	private static final String XSD_FLOAT = ":float";
	private static final String XSD_BOOLEAN = ":boolean";
	private static final String SOAPXML_MAP = ":Map";
	private static final String SOAPENC_ARRAY = ":Array";
	private static final QName VALUE_NAME = new QName("value");
	private static final QName KEY_NAME = new QName("key");
	private static final QName ITEM_NAME = new QName("item");

	private static final String NAMESPACE_URI_XSI = "http://www.w3.org/2001/XMLSchema-instance";
	private static final String NAMESPACE_URI_XSD = "http://www.w3.org/2001/XMLSchema";
	private static final String NAMESPACE_URI_SOAPXML = "http://xml.apache.org/xml-soap";
	private static final String NAMESPACE_URI_SOAPENC = "http://schemas.xmlsoap.org/soap/encoding/";

	private enum Type {
		StringType, IntegerType, FloatType, BooleanType, ArrayType, MapType
	}

	private OMNamespace xsi;
	private OMNamespace xsd;
	private OMNamespace soapXml;
	private OMNamespace soapEnc;

	@SuppressWarnings("unchecked")
	private void initNamespaces(SOAPEnvelope env) {
		int nsFound = 0;
		for (Iterator<OMNamespace> iNamespaces = env.getAllDeclaredNamespaces(); iNamespaces
				.hasNext();) {
			OMNamespace ns = iNamespaces.next();
			if (this.xsi == null
					&& ns.getNamespaceURI().equalsIgnoreCase(NAMESPACE_URI_XSI)) {
				this.xsi = ns;
				nsFound++;
			} else if (this.xsd == null
					&& ns.getNamespaceURI().equalsIgnoreCase(NAMESPACE_URI_XSD)) {
				this.xsd = ns;
				nsFound++;
			} else if (this.soapXml == null
					&& ns.getNamespaceURI().equalsIgnoreCase(
							NAMESPACE_URI_SOAPXML)) {
				this.soapXml = ns;
				nsFound++;
			} else if (this.soapEnc == null
					&& ns.getNamespaceURI().equalsIgnoreCase(
							NAMESPACE_URI_SOAPENC)) {
				this.soapEnc = ns;
				nsFound++;
			}
		}
		if (nsFound != 4) {
			// throw new RuntimeException("insufficient ns?!?");
		}
	}

	public Object parse(OMElement result) throws AxisFault {
		OMContainer parent = result.getParent();
		// find SoapEnvelope in results parents
		while (parent instanceof OMElement && !(parent instanceof SOAPEnvelope)) {
			parent = ((OMElement) parent).getParent();
		}
		if (parent instanceof SOAPEnvelope) {
			initNamespaces((SOAPEnvelope) parent);
		} else {
			throw new AxisFault("no envelope found");
		}

		// Acual Parsing
		Object value = getValue(result);

		// remove found namespaces, for later use of the parser
		// TODO: refactor to ReturnParserFactory
		this.xsd = null;
		this.soapEnc = null;
		this.soapXml = null;
		this.xsi = null;

		return value;
	}

	private Object getValue(OMElement value) {
		switch (getType(value)) {
		case StringType:
			return value.getText();
		case IntegerType:
			return Integer.parseInt(value.getText());
		case FloatType:
			return Double.parseDouble(value.getText());
		case BooleanType:
			return getBoolean(value);
		case ArrayType:
			return getList(value);
		case MapType:
			return getMap(value);
		default:
			throw new RuntimeException("Unsupported type for element: "
					+ value.getLocalName());
		}
	}

	/**
	 * creates String array of axiom element
	 *
	 * @param stringArray
	 *            (the element supposed to be a string array)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getList(OMElement stringArray) {
		List<Object> list = new LinkedList<Object>();
		for (Iterator<OMElement> iElem = stringArray.getChildren(); iElem
				.hasNext();) {
			list.add(getValue(iElem.next()));
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getMap(OMElement items) {
		Map<String, Object> map = new HashMap<String, Object>();

		for (Iterator<OMElement> iElem = items.getChildrenWithName(ITEM_NAME); iElem
				.hasNext();) {
			OMElement elem = iElem.next();
			String key = elem.getFirstChildWithName(KEY_NAME).getText();
			map.put(key, getValue(elem.getFirstChildWithName(VALUE_NAME)));
		}
		return map;
	}

	/**
	 * Based on the type attributes of the element, decides which internal type
	 * the element should be mapped to
	 *
	 * @param elem
	 * @return the determined type
	 */
	@SuppressWarnings("unchecked")
	private Type getType(OMElement elem) {
		String foundType = "";
		for (Iterator<OMAttribute> iAttr = elem.getAllAttributes(); iAttr
				.hasNext();) {
			OMAttribute test = iAttr.next();
			String localName = test.getLocalName();
			if (localName.equals("type")) {
				if (test.getAttributeValue().equals(
						xsd.getPrefix() + XSD_STRING)) {
					return Type.StringType;
				} else if (test.getAttributeValue().equals(
						xsd.getPrefix() + XSD_INTEGER)) {
					return Type.IntegerType;
				} else if (test.getAttributeValue().equals(
						xsd.getPrefix() + XSD_FLOAT)) {
					return Type.FloatType;
				} else if (test.getAttributeValue().equals(
						xsd.getPrefix() + XSD_BOOLEAN)) {
					return Type.BooleanType;
				} else if (soapXml != null
						&& test.getAttributeValue().endsWith(
								soapXml.getPrefix() + SOAPXML_MAP)) {
					return Type.MapType;
				} else if (test.getAttributeValue().endsWith(
						soapEnc.getPrefix() + SOAPENC_ARRAY)) {
					return Type.ArrayType;
				}
				// <value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				// xsi:nil="true" />
			} else if (localName.equals("nil")) {
				if (test.getAttributeValue().equals("true")
						|| test.getAttributeValue().equals("false")) {
					return Type.BooleanType;
				}
			}
			foundType = test.getLocalName();
		}
		throw new RuntimeException("Unknown type '" + foundType
				+ "' (if empty does not contain type attribute) for tag :"
				+ elem.getLocalName());
	}

	// <value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	// xsi:nil="true" />
	private Boolean getBoolean(OMElement items) {
		for (Iterator<OMAttribute> iAttr = items.getAllAttributes(); iAttr
				.hasNext();) {
			OMAttribute test = iAttr.next();
			String localName = test.getLocalName();
			if (localName.equals("nil")) {
				if (test.getAttributeValue().equals("true")) {
					return null;
				} else if (test.getAttributeValue().equals("false")) {
					return false;
				}
			} else {
				return Boolean.parseBoolean(items.getText());
			}
		}
		return false;
	}
}