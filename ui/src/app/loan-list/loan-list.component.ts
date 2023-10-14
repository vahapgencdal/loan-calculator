import { Component, OnInit } from '@angular/core';
import { LoanService } from '../service/loan.service';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.css'],
})
export class LoanListComponent implements OnInit {
  loans: any[] = [];

  constructor(private loanService: LoanService) {}

  ngOnInit() {
    this.loadLoans();
  }

  loadLoans() {
    this.loanService.getLoans().subscribe((data) => {
      this.loans = data;
    });
  }

  deleteLoan(loanId: number) {
    this.loanService.deleteLoan(loanId).subscribe(() => {
      this.loadLoans();
    });
  }
}
