package com.tekbit.loan.calculator.controller;

import com.tekbit.loan.calculator.payload.request.LoanRequest;
import com.tekbit.loan.calculator.payload.response.GenericResponse;
import com.tekbit.loan.calculator.payload.response.LoanGetResponse;
import com.tekbit.loan.calculator.payload.response.LoanListResponse;
import com.tekbit.loan.calculator.payload.response.LoanResponse;
import com.tekbit.loan.calculator.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            summary = "Calculate Loan method",
            description = "Calculate Loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/calculate")
    public ResponseEntity<LoanResponse> calculateLoan(@RequestBody LoanRequest loan) {
        LoanResponse loanResponse = loanService.calculateLoan(loan);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Save calculated Loan as favorite",
            description = "Save Loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public  ResponseEntity<LoanResponse> saveToFavorite(@RequestBody LoanRequest loan) {
        LoanResponse response = loanService.saveLoan(loan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update your favorite Loan",
            description = "Update Loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public  ResponseEntity<LoanResponse> updateFavoriteLoan(@PathVariable(name = "id") Long id, @RequestBody LoanRequest loan) {
        LoanResponse response = loanService.saveLoan(loan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch all your favorite Loans",
            description = "Fetch all your favorite loans by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<LoanListResponse>> getAllFavoriteLoans() {
        List<LoanListResponse> response = loanService.getAllLoans();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch your favorite Loan",
            description = "Fetch your favorite loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<LoanGetResponse> getFavoriteLoan(@PathVariable(name = "id") Long id) {
        LoanGetResponse response = loanService.getLoan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(
            summary = "delete your favorite Loan",
            description = "Delete your favorite loan by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteFavoriteLoan(@PathVariable(name = "id") Long id) {
        GenericResponse response = loanService.deleteLoan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
