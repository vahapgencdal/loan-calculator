package com.tekbit.loan.calculator.processor;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;

import java.math.BigDecimal;

public interface LoanProcessor {

    BigDecimal calculateMonthlyPayment(LoanRequest loanRequest);

    LoanType getLoanType();
}
