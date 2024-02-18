package com.javabootcamp.demofinnhub.contoller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;

public interface DataOperation {
    
    @GetMapping(value = "/profile/{symbol}")
    CompanyProfile2DTO getCompanyProfile(@PathVariable String symbol);

    @GetMapping(value = "quote/{symbol}")
    QuoteDTO getQuote(@PathVariable String symbol);

    @GetMapping(value = "symbols")
    List<SymbolDTO> getAllSymbols();
}
