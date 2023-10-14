package com.tekbit.loan.calculator.service.impl;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.factory.LoanProcessorFactory;
import com.tekbit.loan.calculator.model.Loan;
import com.tekbit.loan.calculator.payload.exception.RecordNotFound;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.GenericResponse;
import com.tekbit.loan.calculator.payload.response.LoanGetResponse;
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
import java.util.Optional;

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
    public void save(LoanRequest request) {
        LoanProcessor loanProcessor = loanProcessorFactory.getLoanService(request.getLoanType());
        BigDecimal monthlyPayment = loanProcessor.calculateMonthlyPayment(request);
        Loan loan = request.toEntity(monthlyPayment);
        LOGGER.info("Monthly payment saved {}: {}", request.getLoanType(), monthlyPayment);
        loanRepository.save(loan);
    }

    @Override
    public void update(LoanRequest request, Long loanId) {
        Loan loan= loanRepository.findById(loanId).orElseThrow(RecordNotFound::new);
        loan.setLoanAmount(request.getLoanAmount());
        loan.setLoanPeriod(request.getLoanPeriod());
        loan.setLoanType(request.getLoanType());
        loan.setInterestRate(request.getInterestRate());
        LoanProcessor loanProcessor = loanProcessorFactory.getLoanService(request.getLoanType());
        BigDecimal monthlyPayment = loanProcessor.calculateMonthlyPayment(request);
        loan.setMonthlyPayment(monthlyPayment);
        LOGGER.info("Monthly payment updated {}: {}", request.getLoanType(), monthlyPayment);
        loanRepository.save(loan);
    }

    @Override
    public LoanResponse calculate(LoanRequest request) {
        LoanProcessor loanProcessor = loanProcessorFactory.getLoanService(request.getLoanType());
        BigDecimal monthlyPayment = loanProcessor.calculateMonthlyPayment(request);
        Loan loan = request.toEntity(monthlyPayment);
        LOGGER.info("Monthly payment calculated {}: {}", request.getLoanType(), monthlyPayment);
        return LoanResponse.builder().monthlyPayment(monthlyPayment).build();
    }

    @Override
    public List<LoanListResponse> getAll(Optional<LoanType> loanType) {

        return loanType.map(type -> loanRepository.findByLoanType(type).stream().map(LoanListResponse::new).toList())
                .orElseGet(() -> loanRepository.findAll().stream().map(LoanListResponse::new).toList());
    }

    @Override
    public LoanGetResponse get(Long loanId) {
        return loanRepository.findById(loanId).map(LoanGetResponse::new).orElseThrow(RecordNotFound::new);
    }

    @Override
    public void delete(Long loanId) {
        loanRepository.deleteById(loanId);
    }

}
