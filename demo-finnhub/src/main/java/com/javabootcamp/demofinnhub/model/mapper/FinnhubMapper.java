package com.javabootcamp.demofinnhub.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javabootcamp.demofinnhub.entity.Stock;
import com.javabootcamp.demofinnhub.entity.StockPrice;
import com.javabootcamp.demofinnhub.entity.StockSymbol;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;
import com.javabootcamp.demofinnhub.model.dto.webApp.CompanyProfileDTO;
import com.javabootcamp.demofinnhub.model.dto.webApp.StockDTO;

@Component
public class FinnhubMapper {

    @Autowired
    ModelMapper modelMapper;

    public StockDTO map(CompanyProfile2DTO companyProfile, QuoteDTO quote) {
        return StockDTO.builder() //
            .companyProfile(modelMapper.map(companyProfile, CompanyProfileDTO.class)) //
            .currentPrice(quote.getCurrentPrice()) //
            .dayHigh(quote.getDayHigh()) //
            .dayLow(quote.getDayLow()) //
            .dayOpen(quote.getDayOpen()) //
            .prevDayClose(quote.getPreDayClose()) //
            .build();
    }

    public Stock map(CompanyProfile2DTO profile) {
        return Stock.builder() //
            .country(profile.getCountry()) //
            .companyName(profile.getCompanyName()) //
            .ipoDate(profile.getIpoDate()) //
            .logo(profile.getLogo()) //
            .marketCap(profile.getMarketCap()) //
            .currency(profile.getCurrency()) //
            .build();
    }

    public StockPrice map(QuoteDTO quote) {
        return StockPrice.builder() //
            .currentPrice(quote.getCurrentPrice()) //
            .dayHigh(quote.getDayHigh()) //
            .dayLow(quote.getDayLow()) //
            .dayOpen(quote.getDayOpen()) //
            .prevDayClose(quote.getPreDayClose()) //
            .build();
    }

    public StockSymbol map(SymbolDTO symbol) {
        return StockSymbol.builder() //
            .symbol(symbol.getSymbol()) //
            .build();
    }

}
