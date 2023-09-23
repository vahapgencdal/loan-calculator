package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.ApiResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import java.util.List;

public interface LoanService {
    LoanResponse calculateLoan(LoanRequest loan);

    LoanResponse saveLoan(LoanRequest loan);


    LoanResponse updateLoan(Long id, LoanRequest loan);

    LoanResponse getLoan(Long id);

    List<LoanResponse> getAllLoans();

    ApiResponse deleteLoan(Long id);
}
