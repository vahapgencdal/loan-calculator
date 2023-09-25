package com.tekbit.loan.calculator.controller;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Loan API endpoint", description = "Loan API endpoint for Loan Calculator Application")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(
            summary = "Fetch all calculated monthly payments",
            description = "Fetch all calculated monthly payments by loan type."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/monthly/payments")
    public ResponseEntity<List<LoanListResponse>> getSavedMonthlyPayments(@RequestParam("loanType") LoanType loanType) {
        List<LoanListResponse> response = loanService.getSavedMonthlyPayments(loanType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Calculate Monthly Loan Payment",
            description = "Calculate monthly payment by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/monthly/payments/calculate")
    public ResponseEntity<LoanResponse> calculateMonthlyPayment(@RequestBody @Valid LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.calculateMonthlyPayment(loanRequest);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }
}
