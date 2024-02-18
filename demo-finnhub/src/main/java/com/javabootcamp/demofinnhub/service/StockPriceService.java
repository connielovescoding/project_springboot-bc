package com.javabootcamp.demofinnhub.service;

import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;

public interface StockPriceService {
    
    QuoteDTO getQuote(String symbol);
}
