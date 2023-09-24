package com.tekbit.loan.calculator.factory;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.repository.LoanRepository;
import com.tekbit.loan.calculator.service.*;
import com.tekbit.loan.calculator.service.impl.*;

public class LoanServiceFactory {
    public static LoanService getLoanService(LoanType loanType, LoanRepository loanRepository) {
        return switch (loanType) {
            case HOUSING_LOAN -> new HousingLoanServiceImpl(loanRepository);
            case SPENDING_LOAN -> new SpendingLoanServiceImpl();
            case VEHICLE_LOAN -> new VehicleLoanServiceImpl();
            case BUSINESS_LOAN -> new BusinessLoanServiceImpl();
            case EDUCATION_LOAN -> new EducationLoanServiceImpl();
            default -> throw new IllegalArgumentException("Invalid loan type");
        };
    }
}
