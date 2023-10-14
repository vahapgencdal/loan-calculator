package com.tekbit.loan.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoanController.class)
public class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanService loanService;

    @Test
    void calculateMonthlyPayment() throws Exception {
        LoanRequest loanRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        LoanResponse loanResponse = LoanResponse.builder()
                .monthlyPayment(BigDecimal.valueOf(843.86))
                .build();

        when(loanService.calculate(any())).thenReturn(loanResponse);

        mockMvc.perform(post("/api/v1/loans/monthly/payments/calculate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loanRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyPayment").value(loanResponse.getMonthlyPayment()));
    }

    @Test
    void getAll() throws Exception {
        List<LoanListResponse> loanListResponses = new ArrayList<>();
        loanListResponses.add(LoanListResponse.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN.toString())
                .monthlyPayment(BigDecimal.valueOf(843.86))
                .build());

        when(loanService.getAll(any())).thenReturn(loanListResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/loans/monthly/payments?loanType=BUSINESS_LOAN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].monthlyPayment").value(loanListResponses.get(0).getMonthlyPayment()));
    }

    @Test
    public void testInvalidLoanAmount() throws Exception {
        // Create an invalid LoanRequest with a zero loan amount
        LoanRequest invalidRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.ZERO)
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        // Perform a POST request with the invalid request
        mockMvc.perform(post("/api/v1/loans/monthly/payments/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest()); // Expect a 400 Bad Request
    }

    @Test
    public void testInvalidLoanType() throws Exception {
        // Create an invalid LoanRequest with a null loan type
        LoanRequest invalidRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(15)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();
        invalidRequest.setLoanType(null);

        // Perform a POST request with the invalid request
        mockMvc.perform(post("/api/v1/loans/monthly/payments/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testInvalidLoanPeriod() throws Exception {
        // Create an invalid LoanRequest with a null loan type
        LoanRequest invalidRequest = LoanRequest.builder()
                .loanAmount(BigDecimal.valueOf(100000.0))
                .loanPeriod(123)
                .interestRate(BigDecimal.valueOf(0.06))
                .loanType(LoanType.BUSINESS_LOAN)
                .build();

        // Perform a POST request with the invalid request
        mockMvc.perform(post("/api/v1/loans/monthly/payments/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

}
