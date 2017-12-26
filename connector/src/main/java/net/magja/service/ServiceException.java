/**
 * @author andre
 *
 */
package net.magja.service;

/**
 * Checked exception thrown by services.
 */
public class ServiceException extends Exception {

  private static final long serialVersionUID = -3829045040825848638L;

  /**
   * Creates exception.
   */
  public ServiceException() {
  }

  /**
   * Creates exception.
   *
   * @param message
   *          exception message.
   */
  public ServiceException(String message) {
    super(message);
  }

  /**
   * Creates exception.
   *
   * @param cause
   *          exception cause.
   */
  public ServiceException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates exception.
   *
   * @param message
   *          exception message
   * @param cause
   *          exception cause.
   */
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

}
