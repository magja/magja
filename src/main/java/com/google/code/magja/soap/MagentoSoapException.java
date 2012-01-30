/**
 * 
 */
package com.google.code.magja.soap;

/**
 * @author ceefour
 * Thrown by Magja SOAP implementation.
 */
@SuppressWarnings("serial")
public class MagentoSoapException extends RuntimeException {

	public MagentoSoapException() {
		super();
	}

	public MagentoSoapException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MagentoSoapException(String arg0) {
		super(arg0);
	}

	public MagentoSoapException(Throwable arg0) {
		super(arg0);
	}

}
