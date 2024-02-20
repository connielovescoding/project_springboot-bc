package com.javabootcamp.demofinnhub.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.javabootcamp.demofinnhub.entity.Stock;
import com.javabootcamp.demofinnhub.entity.StockPrice;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;
import com.javabootcamp.demofinnhub.model.mapper.FinnhubMapper;
import com.javabootcamp.demofinnhub.repository.StockPriceRepository;
import com.javabootcamp.demofinnhub.repository.StockRepository;
import com.javabootcamp.demofinnhub.repository.StockSymbolRepository;
import com.javabootcamp.demofinnhub.service.CompanyService;
import com.javabootcamp.demofinnhub.service.StockPriceService;
import com.javabootcamp.demofinnhub.service.StockSymbolService;

@Component
public class AppStartRunner implements CommandLineRunner {

    public static final List<String> stockInventory = List.of( //
        "AAPL", "MSFT", "TSLA");

    public static List<String> avaliableSymbols = new ArrayList<>();
    
    @Autowired
    StockSymbolService symbolService;

    @Autowired
    StockSymbolRepository symbolRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockPriceService priceService;

    @Autowired
    StockPriceRepository priceRepository;

    @Autowired
    FinnhubMapper finnhubMapper;

    @Override
    public void run(String... args) throws FinnhubException {
        priceRepository.deleteAll();
        stockRepository.deleteAll();
        symbolRepository.deleteAll();

        List<SymbolDTO> symbols = symbolService.getAllSymbols().stream() //
            .filter(symbol -> stockInventory.contains(symbol.getSymbol())) //
            .collect(Collectors.toList());

        // save symbols
        symbolService.save(symbols).stream() //
            .forEach(symbol -> {
                try {
                    CompanyProfile2DTO profile = companyService.getCompanyProfile(symbol.getSymbol());
                    Stock stock = finnhubMapper.map(profile);
                    stock.setStockSymbol(symbol);
                    Stock storedStock = stockRepository.save(stock);
            
                    QuoteDTO quote = priceService.getQuote(symbol.getSymbol());
                    StockPrice stockPrice = finnhubMapper.map(quote);
                    stockPrice.setStock(storedStock);
                    priceRepository.save(stockPrice);

                    avaliableSymbols.add(symbol.getSymbol());
                } catch (FinnhubException e) {
                    System.out.println("RestClientException: Symbol" + symbol.getSymbol());
                }
            });
        SchedulerTaskConfig.start = true;
    }
    
}
