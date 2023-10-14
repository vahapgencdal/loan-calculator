package com.tekbit.loan.calculator.repository;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByLoanType(LoanType loanType);

}
