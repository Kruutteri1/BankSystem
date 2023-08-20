package com.example.banksystem.exceptions;

/**
 * The ErrorResponse class represents a standardized format for returning error information
 * in the application. It includes details about the error, such as the error code and message,
 * which can be useful for debugging or displaying to the user. Using the ErrorResponse class
 * helps to maintain consistency in error responses across the API, making them more understandable
 * for clients and simplifying error handling on both the server and client sides.
 */

public class ErrorResponse {
    private String message;
    private int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

