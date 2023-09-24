package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanResponse;

public interface LoanService {
    LoanResponse calculateMonthlyPayment(LoanRequest loanRequest);
}
