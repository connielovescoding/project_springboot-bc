package com.javabootcamp.demofinnhub.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.javabootcamp.demofinnhub.entity.StockSymbol;
import com.javabootcamp.demofinnhub.model.dto.finnhub.SymbolDTO;
import com.javabootcamp.demofinnhub.model.mapper.FinnhubMapper;
import com.javabootcamp.demofinnhub.repository.StockSymbolRepository;
import com.javabootcamp.demofinnhub.service.StockSymbolService;

@Service
public class StockSymbolServiceImpl implements StockSymbolService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockSymbolRepository symbolRepository;

    @Autowired
    private FinnhubMapper finnhubMapper;

    @Value("${api.finnhub.domain}")
    private String domain;

    @Value("${api.finnhub.base-url}")
    private String baseUrl;

    @Value("${api.finnhub.endpoints.stock.symbol}")
    private String symbolEndpoint;
    
    @Value("${api.finnhub.token}")
    private String token;
    
    @Override
    public List<SymbolDTO> getAllSymbols() {
        String url = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(domain)
            .pathSegment(baseUrl)
            .path(symbolEndpoint)
            .queryParam("exchange", "US")
            .queryParam("token", token)
            .build()
            .toUriString();
        
        SymbolDTO[] symbols = restTemplate.getForObject(url, SymbolDTO[].class);
        return Arrays.asList(symbols);
    }

    @Override
    public List<StockSymbol> save(List<SymbolDTO> symbols) {
        List<StockSymbol> stockSymbols = symbols.stream() //
            .filter(s -> "Common Stock".equals(s.getType())) //
            .map(s -> finnhubMapper.map(s)) //
            .collect(Collectors.toList());
        return symbolRepository.saveAll(stockSymbols);
    }
    
}
