/**
 *
 */
package com.google.code.magja.service;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {

	/**
	 *
	 */
	public ServiceException() {	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
