package com.tekbit.loan.calculator.model;

import com.tekbit.loan.calculator.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "LOANS",uniqueConstraints = { @UniqueConstraint(name = "UniqueLoanAmountPeriodTypeAndRate", columnNames = { "LOAN_AMOUNT", "LOAN_PERIOD", "LOAN_TYPE", "INTEREST_RATE" }) })
public class Loan extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOAN_AMOUNT")
    private BigDecimal loanAmount;

    @Column(name = "LOAN_PERIOD")
    private Integer loanPeriod;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOAN_TYPE")
    private LoanType loanType;

    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;

}
