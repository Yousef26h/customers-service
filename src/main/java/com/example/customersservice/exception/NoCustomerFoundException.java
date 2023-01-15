package com.example.customersservice.exception;

public class NoCustomerFoundException extends RuntimeException {
    public NoCustomerFoundException(String message) {
        super(message);
    }
}
