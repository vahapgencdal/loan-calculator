package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.GenericResponse;
import com.tekbit.loan.calculator.payload.response.LoanGetResponse;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    void save(LoanRequest request);

    void update(LoanRequest request, Long loanId);

    LoanResponse calculate(LoanRequest request);

    LoanGetResponse get(Long loanId);

    void delete(Long loanId);

    List<LoanListResponse> getAll(Optional<LoanType> loanType);



}
