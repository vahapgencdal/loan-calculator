package com.tekbit.loan.calculator.payload.request;

import com.tekbit.loan.calculator.enums.LoanType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequest {

    private BigDecimal loanAmount;

    private Integer loanPeriod;

    private LoanType loanType;

    private BigDecimal interestRate;

}
