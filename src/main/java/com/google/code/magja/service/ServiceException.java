/**
 * @author andre
 *
 */
package com.google.code.magja.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID=-3829045040825848638L;

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
