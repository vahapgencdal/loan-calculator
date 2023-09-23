package com.tekbit.loan.calculator.payload.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(String message) {
        super(message);
    }
}
