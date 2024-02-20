package com.javabootcamp.demofinnhub.service.impl;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.javabootcamp.demofinnhub.entity.Stock;
import com.javabootcamp.demofinnhub.entity.StockPrice;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.infra.RedisHelper;
import com.javabootcamp.demofinnhub.infra.enums.Code;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;
import com.javabootcamp.demofinnhub.model.dto.finnhub.QuoteDTO;
import com.javabootcamp.demofinnhub.model.mapper.FinnhubMapper;
import com.javabootcamp.demofinnhub.repository.StockPriceRepository;
import com.javabootcamp.demofinnhub.repository.StockRepository;
import com.javabootcamp.demofinnhub.repository.StockSymbolRepository;
import com.javabootcamp.demofinnhub.service.CompanyService;
import com.javabootcamp.demofinnhub.service.StockPriceService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FinnhubMapper finnhubMapper;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockSymbolRepository symbolRepository;

    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private StockPriceRepository priceRepository;

    @Value("${api.finnhub.domain}")
    private String domain;

    @Value("${api.finnhub.base-url}")
    private String baseUrl;

    @Value("${api.finnhub.endpoints.stock.profile2}")
    private String companyProfile2Endpoint;
    
    @Value("${api.finnhub.token}")
    private String token;

    @Value(value = "${redis-key.company-profile2}")
    private String redisKeyForProfile2;

    @Override
    public CompanyProfile2DTO getCompanyProfile(String symbol) throws FinnhubException {
        String url = UriComponentsBuilder.newInstance() //
            .scheme("https")
            .host(domain)
            .pathSegment(baseUrl)
            .path(companyProfile2Endpoint)
            .queryParam("symbol", symbol)
            .queryParam("token", token)
            .build()
            .toUriString();

        String key = RedisHelper.formatKey(redisKeyForProfile2, symbol);
        try {
            CompanyProfile2DTO profile = restTemplate.getForObject(url, CompanyProfile2DTO.class);
            
            if (Objects.nonNull(profile)) {
                redisHelper.set(key, profile, 600000000);
            } else {
                profile = (CompanyProfile2DTO) redisHelper.get(key);
                if (profile == null)
                    throw new FinnhubException(Code.FINNHUB_PROFILE2_NOTFOUND);
            }
            return profile;
        } catch (RestClientException e) {
            CompanyProfile2DTO profileFromRedis = (CompanyProfile2DTO) redisHelper.get(key);
            if (profileFromRedis == null)
                throw new FinnhubException(Code.FINNHUB_PROFILE2_NOTFOUND);
            return profileFromRedis;
        }
    }

    @Override
    public void refresh() throws FinnhubException {
        symbolRepository.findAll().stream()
            .forEach(symbol -> {
                try {
                    CompanyProfile2DTO newProfile = this.getCompanyProfile(symbol.getSymbol());
                    Optional<Stock> oldStock = stockRepository.findByStockSymbol(symbol);
                    if (oldStock.isPresent()) {

                        Stock stock = oldStock.get();
                        stock.setCountry(newProfile.getCountry());
                        stock.setCompanyName(newProfile.getCompanyName());
                        stock.setLogo(newProfile.getLogo());
                        stock.setMarketCap(newProfile.getMarketCap());
                        stock.setCurrency(newProfile.getCurrency());

                        if (newProfile != null && newProfile.getTicker().equals(symbol.getSymbol())) {
                            stock.setStockStatus("A");
                        } else {
                            stock.setStockStatus("I");
                        }
                        stockRepository.save(stock);

                        QuoteDTO quote = stockPriceService.getQuote(symbol.getSymbol());
                        StockPrice stockPrice = finnhubMapper.map(quote);
                        stockPrice.setStock(stock);
                        priceRepository.save(stockPrice);
                    } else {
                        System.out.println(symbol.getSymbol() + " not found.");
                    }
                } catch (FinnhubException e) {
                    System.out.println("RestClientException:" + symbol.getSymbol());
                }
                
            });
    }
}
