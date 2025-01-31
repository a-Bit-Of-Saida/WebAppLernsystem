package edu.fra.uas.API_Gateway.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void throwError(String url, Throwable e) {
        throw new InternalServerErrorException("Error forwarding request to " + url, e);
    }
}
