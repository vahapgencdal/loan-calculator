package com.tekbit.loan.calculator.service.impl;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.repository.LoanRepository;
import com.tekbit.loan.calculator.service.HousingLoanService;
import com.tekbit.loan.calculator.service.LoanService;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
@Qualifier("housingLoanService")
public class HousingLoanServiceImpl implements HousingLoanService {
    Logger LOGGER = LoggerFactory.getLogger(HousingLoanServiceImpl.class);

     private final LoanRepository loanRepository;

    public HousingLoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanResponse calculateMonthlyPayment(LoanRequest loanRequest) {

        BigDecimal annualRate = loanRequest.getInterestRate().divide(BigDecimal.valueOf(100), 4, MathContext.DECIMAL128.getRoundingMode());

        int numberOfPayments =loanRequest.getLoanPeriod() * 12;

        BigDecimal monthlyInterestRate = annualRate.divide(BigDecimal.valueOf(numberOfPayments), 4, MathContext.DECIMAL128.getRoundingMode());

        BigDecimal monthlyPayment = monthlyInterestRate.multiply(loanRequest.getLoanAmount());

        LOGGER.info("Monthly payment for {}: {}", loanRequest.getLoanType(), monthlyPayment);

        Loan loan = new Loan();
        loan.setLoanType(loanRequest.getLoanType());
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setLoanPeriod(loanRequest.getLoanPeriod());
        loan.setInterestRate(loanRequest.getInterestRate());
        loan.setMonthlyPayment(monthlyPayment);

        loanRepository.save(loan);

        return LoanResponse.builder().monthlyPayment(monthlyPayment).build();
    }

    @Override
    public List<LoanListResponse> getSavedLoans() {
        return loanRepository.findAll().stream().map(LoanListResponse::new).toList();
    }
}
