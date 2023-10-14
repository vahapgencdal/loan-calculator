import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoanCalculatorComponent } from './loan-calculator/loan-calculator.component';
import { LoanListComponent } from './loan-list/loan-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/calculator', pathMatch: 'full' },
  { path: 'calculator', component: LoanCalculatorComponent },
  { path: 'loan-list', component: LoanListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
