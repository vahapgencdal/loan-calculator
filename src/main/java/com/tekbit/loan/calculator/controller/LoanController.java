package com.tekbit.loan.calculator.controller;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.ApiResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<LoanResponse> calculateLoan(@RequestBody LoanRequest loan) {
        LoanResponse loanResponse = loanService.calculateLoan(loan);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<LoanResponse> saveLoan(@RequestBody LoanRequest loan) {
        LoanResponse response = loanService.saveLoan(loan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<LoanResponse> updateLoan(@PathVariable(name = "id") Long id, @RequestBody LoanRequest loan) {
        LoanResponse response = loanService.saveLoan(loan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll() {
        List<LoanResponse> response = loanService.getAllLoans();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> get(@PathVariable(name = "id") Long id) {
        LoanResponse response = loanService.getLoan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable(name = "id") Long id) {
        ApiResponse response = loanService.deleteLoan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
