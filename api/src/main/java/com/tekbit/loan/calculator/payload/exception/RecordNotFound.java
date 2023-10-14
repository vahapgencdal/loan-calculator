package com.tekbit.loan.calculator.payload.exception;

public class RecordNotFound extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Record not found";

    public RecordNotFound(String message) {
        super(message);
    }

    public RecordNotFound() {
        super(DEFAULT_MESSAGE);
    }
}
