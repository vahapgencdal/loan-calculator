import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoanCalculatorComponent } from './loan-calculator/loan-calculator.component';
import { LoanListComponent } from './loan-list/loan-list.component';

@NgModule({
  declarations: [AppComponent, LoanCalculatorComponent, LoanListComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
