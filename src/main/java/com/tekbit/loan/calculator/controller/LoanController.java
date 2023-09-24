package com.tekbit.loan.calculator.controller;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.repository.LoanRepository;
import com.tekbit.loan.calculator.service.HousingLoanService;
import com.tekbit.loan.calculator.service.LoanService;
import com.tekbit.loan.calculator.factory.LoanServiceFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Loan API endpoint", description = "Loan API endpoint for Loan Calculator Application")
public class LoanController {

    private final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Operation(
            summary = "Calculate Loan method",
            description = "Calculate Loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/monthly/payment/calculate")
    public ResponseEntity<LoanResponse> calculateMonthlyPayment(@RequestBody LoanRequest loanRequest) {
        LoanService loanService = LoanServiceFactory.getLoanService(loanRequest.getLoanType(), loanRepository);
        LoanResponse loanResponse = loanService.calculateMonthlyPayment(loanRequest);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch all saved loans by loan type",
            description = "Fetch all saved loans by loan type."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/monthly/payments")
    public ResponseEntity<List<LoanListResponse>> getAllSavedLoans(@RequestParam("loanType") LoanType loanType) {
         HousingLoanService housingLoanService = (HousingLoanService) LoanServiceFactory.getLoanService(loanType, loanRepository);
        List<LoanListResponse> response = housingLoanService.getSavedLoans();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
