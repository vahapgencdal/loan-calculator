package com.tekbit.loan.calculator.service;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.factory.LoanProcessorFactory;
import com.tekbit.loan.calculator.model.Loan;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.processor.LoanProcessor;
import com.tekbit.loan.calculator.repository.LoanRepository;
import com.tekbit.loan.calculator.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private LoanProcessorFactory loanProcessorFactory;

    @Mock
    private LoanRepository loanRepository;

    @Test
    @DisplayName("Calculate monthly payment: success")
    void calculateMonthlyPaymentSuccess() {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();
        LoanProcessor loanProcessor = mock(LoanProcessor.class);

        when(loanProcessorFactory.getLoanService(any())).thenReturn(loanProcessor);

        BigDecimal expectedMonthlyPayment = BigDecimal.valueOf(843.86);

        when(loanProcessor.calculateMonthlyPayment(loanRequest)).thenReturn(expectedMonthlyPayment);

        LoanResponse actualResponse = loanService.calculateMonthlyPayment(loanRequest);

        assertEquals(expectedMonthlyPayment, actualResponse.getMonthlyPayment());
    }

    @Test
    @DisplayName("Get calculated monthly payments: success")
    void getCalculatedMonthlyPayments() {
        LoanType loanType = LoanType.BUSINESS_LOAN;

        List<Loan> loanList = List.of(
                LoanRequest.builder()
                        .loanAmount(BigDecimal.valueOf(100000.0))
                        .loanPeriod(15)
                        .interestRate(BigDecimal.valueOf(0.06))
                        .loanType(LoanType.BUSINESS_LOAN)
                        .build().toEntity( BigDecimal.valueOf(843.86)),
                LoanRequest.builder()
                        .loanAmount(BigDecimal.valueOf(100000.0))
                        .loanPeriod(15)
                        .interestRate(BigDecimal.valueOf(0.06))
                        .loanType(LoanType.BUSINESS_LOAN)
                        .build().toEntity( BigDecimal.valueOf(843.86))
        );

        when(loanRepository.findByLoanType(loanType)).thenReturn(loanList);


        List<LoanListResponse> expectedList = loanList.stream().map(LoanListResponse::new).toList();

        List<LoanListResponse> actualList = loanService.getSavedMonthlyPayments(loanType);

        assertEquals(expectedList, actualList);
    }
}
