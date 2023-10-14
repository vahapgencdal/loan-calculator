package com.tekbit.loan.calculator.processor.impl;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.processor.LoanProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class VehicleLoanProcessor implements LoanProcessor {
    /*
     * This method calculates the monthly payment for a business loan.
     * The formula used is:
     * M = P * (r * (1 + r)^n) / ((1 + r)^n - 1)
     */
    @Override
    public BigDecimal calculateMonthlyPayment(LoanRequest loanRequest) {
        BigDecimal top = ratePerMonth(loanRequest.getInterestRate()).multiply(calculateRatePower(loanRequest.getInterestRate(), loanRequest.getLoanPeriod()));
        BigDecimal bottom = calculateRatePower(loanRequest.getInterestRate(), loanRequest.getLoanPeriod()).subtract(BigDecimal.ONE);
        BigDecimal ratio = top.divide(bottom, 20, RoundingMode.DOWN);

        return loanRequest.getLoanAmount().multiply(ratio).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal ratePerMonth(BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(12), 20, RoundingMode.DOWN);
    }

    private static BigDecimal calculateRatePower(BigDecimal rate, int period) {
        BigDecimal onePlus = BigDecimal.ONE.add(ratePerMonth(rate));
        return onePlus.pow(period * 12);
    }

    @Override
    public LoanType getLoanType() {
        return LoanType.VEHICLE_LOAN;
    }
}
