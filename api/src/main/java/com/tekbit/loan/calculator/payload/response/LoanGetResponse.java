package com.tekbit.loan.calculator.payload.response;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.Instant;



@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class LoanGetResponse extends PayloadResponse{

    private Long id;
    private BigDecimal loanAmount;
    private Integer loanPeriod;
    private String loanType;
    private BigDecimal interestRate;
    private BigDecimal monthlyPayment;

    public LoanGetResponse(Loan loan) {
        super("VG","VG", "VG", "VG", loan.getCreatedAt(), loan.getUpdatedAt());
        this.id = loan.getId();
        this.loanAmount = loan.getLoanAmount();
        this.loanPeriod = loan.getLoanPeriod();
        this.loanType = loan.getLoanType().toString();
        this.interestRate = loan.getInterestRate();
        this.monthlyPayment = loan.getMonthlyPayment();
    }
}