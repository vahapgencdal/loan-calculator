import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoanService {
  private apiUrl = 'http://localhost:8080/api/v1/loans';

  constructor(private http: HttpClient) {}

  getLoans(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  createLoan(loanData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, loanData);
  }

  updateLoan(loanId: number, loanData: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${loanId}`, loanData);
  }

  deleteLoan(loanId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${loanId}`);
  }

  getLoan(loanId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${loanId}`);
  }

  calculateLoan(loanData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/calculate`, loanData);
  }

}
