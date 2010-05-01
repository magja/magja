package com.google.code.magja;

/**
 *
 * @author kwilson
 */
public class MagjaException extends RuntimeException {

    public MagjaException(Throwable cause) {
        super(cause);
    }

    public MagjaException(String message, Throwable cause) {
        super(message, cause);
    }

    public MagjaException(String message) {
        super(message);
    }

}
