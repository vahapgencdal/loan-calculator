package com.tekbit.loan.calculator.service.impl;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
@Qualifier("businessLoanServiceImpl")
public class BusinessLoanServiceImpl implements LoanService {
    Logger LOGGER = LoggerFactory.getLogger(BusinessLoanServiceImpl.class);

    @Override
    public LoanResponse calculateMonthlyPayment(LoanRequest loanRequest) {

        BigDecimal annualRate = loanRequest.getInterestRate().divide(BigDecimal.valueOf(100), 4, MathContext.DECIMAL128.getRoundingMode());

        int numberOfPayments =loanRequest.getLoanPeriod() * 12;

        BigDecimal monthlyInterestRate = annualRate.divide(BigDecimal.valueOf(numberOfPayments), 4, MathContext.DECIMAL128.getRoundingMode());

        BigDecimal monthlyPayment = monthlyInterestRate.multiply(loanRequest.getLoanAmount());

        LOGGER.info("Monthly payment for {}: {}", loanRequest.getLoanType(), monthlyPayment);

        return LoanResponse.builder().monthlyPayment(monthlyPayment).build();
    }

}
