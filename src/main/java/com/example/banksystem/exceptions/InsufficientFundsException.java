package com.example.banksystem.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String massage) {
        super(massage);
    }
}
