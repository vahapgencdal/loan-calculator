package com.tekbit.loan.calculator.factory;

import com.tekbit.loan.calculator.enums.LoanType;
import com.tekbit.loan.calculator.processor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LoanProcessorFactory {
    private final Map<LoanType, LoanProcessor> loanServiceMap;

    @Autowired
    private LoanProcessorFactory(List<LoanProcessor> loanServices) {
        loanServiceMap = loanServices.stream().collect(Collectors.toUnmodifiableMap(LoanProcessor::getLoanType, Function.identity()));
    }

    public LoanProcessor getLoanService(LoanType loanType) {
        return   Optional.ofNullable(loanServiceMap.get(loanType)).orElseThrow(IllegalArgumentException::new);
    }
}
