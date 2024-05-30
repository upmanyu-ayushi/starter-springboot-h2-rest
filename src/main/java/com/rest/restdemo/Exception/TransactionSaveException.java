package com.rest.restdemo.Exception;

public class TransactionSaveException extends RuntimeException {

    public TransactionSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
