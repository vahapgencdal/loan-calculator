package com.tekbit.loan.calculator.repository;

import com.tekbit.loan.calculator.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
