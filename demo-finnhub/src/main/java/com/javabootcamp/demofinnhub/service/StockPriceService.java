package com.javabootcamp.demofinnhub.service;

import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;

public interface StockPriceService {
    
    QuoteDTO getQuote(String symbol) throws FinnhubException;

    double calculateSMA(String stockSymbol, int period);
}
