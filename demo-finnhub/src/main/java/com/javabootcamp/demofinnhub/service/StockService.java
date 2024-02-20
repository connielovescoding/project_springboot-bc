package com.javabootcamp.demofinnhub.service;

import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.model.dto.webApp.StockDTO;

public interface StockService {
    
    StockDTO stockInfo(String symbol) throws FinnhubException;
}
