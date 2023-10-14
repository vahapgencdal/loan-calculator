package com.tekbit.loan.calculator.processor;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.processor.impl.BusinessLoanProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class BusinessLoanProcessorTest {

    @InjectMocks
    private BusinessLoanProcessor businessLoanProcessor;

    @DisplayName("Calculate monthly payment for business loan: success")
    @Test
    public void calculateMonthlyPaymentSuccess() {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        BigDecimal monthlyPayment = businessLoanProcessor.calculateMonthlyPayment(loanRequest);
        assertEquals(0, monthlyPayment.compareTo(BigDecimal.valueOf(843.86)));
    }

    @DisplayName("Calculate monthly payment for business loan: success 2")
    @Test
    public void calculateMonthlyPaymentSuccess2() {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.00))
                .loanPeriod(10)
                .interestRate(BigDecimal.valueOf(6))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        BigDecimal monthlyPayment = businessLoanProcessor.calculateMonthlyPayment(loanRequest);
        assertEquals(0, monthlyPayment.compareTo(BigDecimal.valueOf(50000.00)));
    }

    @DisplayName("Calculate monthly payment for business loan: failed")
    @Test
    public void calculateMonthlyPaymentFail() {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.00))
                .loanPeriod(10)
                .interestRate(BigDecimal.valueOf(5))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        BigDecimal monthlyPayment = businessLoanProcessor.calculateMonthlyPayment(loanRequest);
        assertEquals(-1, monthlyPayment.compareTo(BigDecimal.valueOf(50000.00)));
    }
}
