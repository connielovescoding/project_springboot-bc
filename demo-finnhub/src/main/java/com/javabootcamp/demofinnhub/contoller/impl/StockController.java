package com.javabootcamp.demofinnhub.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javabootcamp.demofinnhub.contoller.StockOperation;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.infra.ApiResp;
import com.javabootcamp.demofinnhub.model.dto.webApp.StockDTO;
import com.javabootcamp.demofinnhub.model.dto.webApp.SymbolReqDTO;
import com.javabootcamp.demofinnhub.service.StockService;

@RestController
@RequestMapping(value = "/api/v1")
public class StockController implements StockOperation {
    
    @Autowired
    private StockService stockService;

    public ApiResp<StockDTO> stockInfo(SymbolReqDTO symbol) throws FinnhubException {
        return ApiResp.<StockDTO>builder()
            .ok()
            .data(stockService.stockInfo(symbol.getSymbol()))
            .build();
    }
}
