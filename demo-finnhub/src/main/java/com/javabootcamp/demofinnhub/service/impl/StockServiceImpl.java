package com.javabootcamp.demofinnhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.infra.enums.Code;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.dto.webApp.StockDTO;
import com.javabootcamp.demofinnhub.model.mapper.FinnhubMapper;
import com.javabootcamp.demofinnhub.service.CompanyService;
import com.javabootcamp.demofinnhub.service.StockPriceService;
import com.javabootcamp.demofinnhub.service.StockService;

@Service
public class StockServiceImpl implements StockService {
    
    @Autowired
    private FinnhubMapper finnhubMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockPriceService priceService;

    @Override
    public StockDTO stockInfo(String symbol) throws FinnhubException {
        CompanyProfile2DTO profile = companyService.getCompanyProfile(symbol);
        QuoteDTO quote = priceService.getQuote(symbol);

        if (profile == null && quote == null) {
            throw new FinnhubException(Code.THIRD_PARTY_SERVER_UNAVAILABLE);
        } else {
            return finnhubMapper.map(profile, quote);
        }
    }
    
}
