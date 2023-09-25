package com.tekbit.loan.calculator.service.impl;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.factory.LoanProcessorFactory;
import com.tekbit.loan.calculator.model.Loan;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.processor.LoanProcessor;
import com.tekbit.loan.calculator.repository.LoanRepository;
import com.tekbit.loan.calculator.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    Logger LOGGER = LoggerFactory.getLogger(LoanServiceImpl.class);

    private final LoanProcessorFactory loanProcessorFactory;
    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanProcessorFactory loanProcessorFactory, LoanRepository loanRepository) {
        this.loanProcessorFactory = loanProcessorFactory;
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanResponse calculateMonthlyPayment(LoanRequest request) {
        LoanProcessor loanProcessor = loanProcessorFactory.getLoanService(request.getLoanType());
        BigDecimal monthlyPayment = loanProcessor.calculateMonthlyPayment(request);
        Loan loan = request.toEntity(monthlyPayment);
        loanRepository.save(loan);
        LOGGER.info("Monthly payment saved {}: {}", request.getLoanType(), monthlyPayment);
        return LoanResponse.builder().monthlyPayment(monthlyPayment).build();
    }

    @Override
    public List<LoanListResponse> getSavedMonthlyPayments(LoanType loanType) {
        return loanRepository.findByLoanType(loanType).stream().map(LoanListResponse::new).toList();
    }

}
