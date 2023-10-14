package com.tekbit.loan.calculator.controller;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.payload.request.LoanRequest;
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
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loans")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
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
    @GetMapping
    public ResponseEntity<List<LoanListResponse>> getAll(@RequestParam(value = "loanType", required = false) Optional<LoanType> loanType) {
        List<LoanListResponse> response = loanService.getAll(loanType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch calculated monthly payments",
            description = "Fetch calculated monthly payments by loan id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanGetResponse> get(@PathVariable("loanId") Long loanId) {
        LoanGetResponse response = loanService.get(loanId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "update calculated monthly payments",
            description = "update calculated monthly payments by loan type."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{loanId}")
    public ResponseEntity<HttpStatus> update(@PathVariable("loanId") Long loanId, @RequestBody @Valid LoanRequest loanRequest) {
        loanService.update(loanRequest, loanId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a calculated monthly payment",
            description = "Delete a calculated monthly payment by loan id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{loanId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("loanId") Long loanId) {
        loanService.delete(loanId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Calculate Monthly Loan Payment",
            description = "Calculate monthly payment by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/calculate")
    public ResponseEntity<LoanResponse> calculate(@RequestBody @Valid LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.calculate(loanRequest);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Saved Monthly Loan Payment",
            description = "Saved monthly payment by specifying loan amount, loan period, loan type and interest rate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = LoanResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid LoanRequest loanRequest) {
        loanService.save(loanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
