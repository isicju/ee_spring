package com.example.demo.validation;

public class IllegalRequestInputException extends RuntimeException {
    public IllegalRequestInputException(String exception) {
        super(exception);
    }
}
