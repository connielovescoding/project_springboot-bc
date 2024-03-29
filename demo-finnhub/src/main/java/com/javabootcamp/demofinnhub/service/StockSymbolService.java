package com.javabootcamp.demofinnhub.service;

import java.util.List;
import com.javabootcamp.demofinnhub.entity.StockSymbol;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;

public interface StockSymbolService {
    
    List<SymbolDTO> getAllSymbols();

    List<StockSymbol> save(List<SymbolDTO> symbols);

}
