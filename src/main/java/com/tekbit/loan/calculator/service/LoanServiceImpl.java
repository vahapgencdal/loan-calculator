package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.model.Loan;
import com.tekbit.loan.calculator.payload.exception.LoanNotFoundException;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.GenericResponse;
import com.tekbit.loan.calculator.payload.response.LoanGetResponse;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
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
        Loan loanEntity = loan.toEntity();
        Loan loanEntityResponse = loanRepository.save(loanEntity);
        return new LoanResponse(loanEntityResponse);
    }

    @Override
    public GenericResponse updateLoan(Long id, LoanRequest loan) {

        Loan loanEntity = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

        loanEntity.setLoanAmount(loan.getLoanAmount());
        loanEntity.setInterestRate(loan.getInterestRate());
        loanEntity.setLoanType(loan.getLoanType());
        loanEntity.setLoanPeriod(loan.getLoanPeriod());
        loanRepository.save(loanEntity);

        return new GenericResponse(true, "Loan updated successfully");
    }

    @Override
    public LoanGetResponse getLoan(Long id) {
        return loanRepository.findById(id).map(LoanGetResponse::new).orElseThrow(()-> new LoanNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public List<LoanListResponse> getAllLoans() {
        return loanRepository.findAll().stream().map(LoanListResponse::new).toList();
    }

    @Override
    public GenericResponse deleteLoan(Long id) {
        loanRepository.deleteById(id);
        return new GenericResponse(true, "Loan deleted successfully");
    }
}
