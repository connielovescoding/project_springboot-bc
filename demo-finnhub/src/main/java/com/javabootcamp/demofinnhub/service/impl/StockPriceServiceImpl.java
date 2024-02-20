package com.javabootcamp.demofinnhub.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.javabootcamp.demofinnhub.entity.StockPrice;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.infra.enums.Code;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.repository.StockPriceRepository;
import com.javabootcamp.demofinnhub.service.StockPriceService;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockPriceRepository priceRepository;

    @Value("${api.finnhub.domain}")
    private String domain;

    @Value("${api.finnhub.base-url}")
    private String baseUrl;

    @Value("${api.finnhub.endpoints.stock.quote}")
    private String quoteEndpoint;
    
    @Value("${api.finnhub.token}")
    private String token;

    @Override
    public QuoteDTO getQuote(String symbol) throws FinnhubException {
        String url = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(domain)
            .pathSegment(baseUrl)
            .path(quoteEndpoint)
            .queryParam("symbol", symbol)
            .queryParam("token", token)
            .build()
            .toUriString();

        try {
            return restTemplate.getForObject(url, QuoteDTO.class);
        } catch (RestClientException e) {
            throw new FinnhubException(Code.FINNHUB_QUOTE_NOTFOUND);
        }
    }

    @Override
    public double calculateSMA(String symbol, int period) {
        Pageable pageable = PageRequest.of(0, period);
        List<StockPrice> stockPrices = priceRepository.findTopNByStockSymbolOrderByDateTimeDesc(symbol, pageable);
        double sum = stockPrices.stream()
                                .mapToDouble(StockPrice::getCurrentPrice)
                                .sum();
        return sum / stockPrices.size();
    }

}
