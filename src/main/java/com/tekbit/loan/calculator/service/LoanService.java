package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse calculateMonthlyPayment(LoanRequest request);

    List<LoanListResponse> getSavedMonthlyPayments(LoanType loanType);
}
