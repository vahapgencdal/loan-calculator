package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.payload.response.LoanListResponse;

import java.util.List;

public interface HousingLoanService extends LoanService{
    List<LoanListResponse> getSavedLoans();
}
