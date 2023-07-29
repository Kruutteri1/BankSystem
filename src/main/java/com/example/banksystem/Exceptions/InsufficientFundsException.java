package com.example.banksystem.Exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String massage) {
        super(massage);
    }
}
