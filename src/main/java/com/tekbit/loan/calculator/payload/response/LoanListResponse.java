package com.tekbit.loan.calculator.payload.response;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanListResponse {
    private Long id;
    private BigDecimal loanAmount;
    private Integer loanPeriod;
    private String loanType;
    private BigDecimal interestRate;

    public LoanListResponse(Loan loan) {
        this.id = loan.getId();
        this.loanAmount = loan.getLoanAmount();
        this.loanPeriod = loan.getLoanPeriod();
        this.loanType = loan.getLoanType().toString();
        this.interestRate = loan.getInterestRate();
    }
}
