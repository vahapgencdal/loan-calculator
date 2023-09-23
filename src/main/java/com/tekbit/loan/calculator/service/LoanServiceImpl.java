package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.ApiResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    @Override
    public LoanResponse calculateLoan(LoanRequest loan) {
        return null;
    }

    @Override
    public LoanResponse saveLoan(LoanRequest loan) {
        return null;
    }

    @Override
    public LoanResponse updateLoan(Long id, LoanRequest loan) {
        return null;
    }

    @Override
    public LoanResponse getLoan(Long id) {
        return null;
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return null;
    }

    @Override
    public ApiResponse deleteLoan(Long id) {
        return null;
    }
}
