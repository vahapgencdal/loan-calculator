package com.tekbit.loan.calculator.payload.request;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Schema(description = "All details about the Loan Request.")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequest {

    private static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(3.5);

    @Schema(description = "The loan amount", name = "loanAmount", defaultValue = "100000", example = "100000")
    @Range(min = 1, max =9999999, message = "The loan amount must be between {min} - {max}")
    private BigDecimal loanAmount;

    @Schema(description = "The loan period based on year", name = "loanPeriod", defaultValue = "5", example = "5")
    @Range(min = 1, max = 40, message = "The loan period must be between {min} - {max}")
    private Integer loanPeriod;

    @Schema(description = "The loan type", name = "loanType", defaultValue = "HOUSING_LOAN", example = "HOUSING_LOAN")
    @NotNull(message = "Loan type is mandatory")
    private LoanType loanType;

    @Schema(description = "The interest rate", name = "interestRate", defaultValue = "3.5", example = "3.5")
    @Positive(message = "Interest rate must be positive")
    @Range(min = 0, max = 100, message = "Interest rate must be between {min} - {max}")
    private BigDecimal interestRate;

    public Loan toEntity(BigDecimal monthlyPayment) {
        Loan loan = new Loan();
        loan.setLoanAmount(this.loanAmount);
        loan.setLoanPeriod(this.loanPeriod);
        loan.setLoanType(this.loanType);
        loan.setInterestRate(this.interestRate);
        loan.setMonthlyPayment(monthlyPayment);
        return loan;
    }

    public BigDecimal getInterestRate() {
        if (interestRate == null) {
            return INTEREST_RATE;
        }
        return interestRate;
    }
}
