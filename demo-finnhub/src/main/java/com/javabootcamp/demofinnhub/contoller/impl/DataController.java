package com.javabootcamp.demofinnhub.contoller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javabootcamp.demofinnhub.contoller.DataOperation;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;
import com.javabootcamp.demofinnhub.service.CompanyService;
import com.javabootcamp.demofinnhub.service.StockPriceService;
import com.javabootcamp.demofinnhub.service.StockSymbolService;

@RestController
@RequestMapping(value = "/api/v1")
public class DataController implements DataOperation {
    
    // @Autowired
    // private CompanyService companyService;

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/sma")
    public ResponseEntity<Double> getStockPriceSMA(
            @RequestParam String stockSymbol,
            @RequestParam int period) {
        
        try {
            double sma = stockPriceService.calculateSMA(stockSymbol, period);
            return ResponseEntity.ok(sma);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // @Autowired
    // private StockSymbolService stockSymbolService;

    // @Override
    // public CompanyProfile2DTO getCompanyProfile(String symbol) {
    //     return companyService.getCompanyProfile(symbol);
    // }

    // @Override
    // public QuoteDTO getQuote(String symbol) {
    //     return stockPriceService.getQuote(symbol);
    // }

    // @Override
    // public List<SymbolDTO> getAllSymbols() {
    //     return stockSymbolService.getAllSymbols();
    // }

}
