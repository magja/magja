/**
 *
 */
package net.magja.soap;

/**
 * Thrown by Magja SOAP implementation.
 *
 * @author ceefour
 */
public class MagentoSoapException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MagentoSoapException() {
    super();
  }

  public MagentoSoapException(String message, Throwable cause) {
    super(message, cause);
  }

  public MagentoSoapException(String message) {
    super(message);
  }

  public MagentoSoapException(Throwable cause) {
    super(cause);
  }

}
