import { Component } from '@angular/core';
import { LoanService } from '../service/loan.service';

@Component({
  selector: 'app-loan-calculator',
  templateUrl: './loan-calculator.component.html',
  styleUrls: ['./loan-calculator.component.css'],
})
export class LoanCalculatorComponent {
  loanData = {
    loanAmount: 0,
    loanPeriod: 0,
    loanType: 'EDUCATION_LOAN',
    interestRate: 0,
  };

  constructor(private loanService: LoanService) {}

  calculateLoan() {
    // Implement loan calculation logic here
    // You can calculate the EMI or total repayment amount based on user input
  }

  submitLoan() {
    this.loanService.createLoan(this.loanData).subscribe((response) => {
      // Handle the response here, e.g., show a success message or navigate to the loan list
    });
  }
}
