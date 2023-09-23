package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.GenericResponse;
import com.tekbit.loan.calculator.payload.response.LoanGetResponse;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import java.util.List;

public interface LoanService {
    LoanResponse calculateLoan(LoanRequest loan);

    LoanResponse saveLoan(LoanRequest loan);


    GenericResponse updateLoan(Long id, LoanRequest loan);

    LoanGetResponse getLoan(Long id);

    List<LoanListResponse> getAllLoans();

    GenericResponse deleteLoan(Long id);
}
