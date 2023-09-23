package com.tekbit.loan.calculator.payload.request;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "All details about the Loan Request.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @Schema(description = "The loan amount", name = "loanAmount", defaultValue = "100000", example = "100000", minimum = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal loanAmount;

    @Schema(description = "The loan period based on year", name = "loanPeriod", defaultValue = "5", example = "5", minimum = "1")
    private Integer loanPeriod;

    @Schema(description = "The loan type", name = "loanType", defaultValue = "HOUSING_LOAN", example = "HOUSING_LOAN", requiredMode = Schema.RequiredMode.REQUIRED)
    private LoanType loanType;

    @Schema(description = "The interest rate", name = "interestRate", defaultValue = "3.5", example = "3.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal interestRate;

    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setLoanAmount(this.loanAmount);
        loan.setLoanPeriod(this.loanPeriod);
        loan.setLoanType(this.loanType);
        loan.setInterestRate(this.interestRate);
        return loan;
    }
}
