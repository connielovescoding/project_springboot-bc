package com.javabootcamp.demofinnhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.service.StockPriceService;

@Service
public class StockPriceServiceImpl implements StockPriceService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.finnhub.domain}")
    private String domain;

    @Value("${api.finnhub.base-url}")
    private String baseUrl;

    @Value("${api.finnhub.endpoints.stock.quote}")
    private String quoteEndpoint;
    
    @Value("${api.finnhub.token}")
    private String token;

    @Override
    public QuoteDTO getQuote(String symbol) {
        String url = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(domain)
            .pathSegment(baseUrl)
            .path(quoteEndpoint)
            .queryParam("symbol", symbol)
            .queryParam("token", token)
            .build()
            .toUriString();
        
        return restTemplate.getForObject(url, QuoteDTO.class);
    }
}
