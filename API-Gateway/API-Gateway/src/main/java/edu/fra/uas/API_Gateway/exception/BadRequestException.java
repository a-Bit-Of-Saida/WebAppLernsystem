package edu.fra.uas.API_Gateway.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public static void handleInvalidQuery(Exception e) {
        throw new BadRequestException("Invalid GraphQL query: " + e.getMessage());
    }

}