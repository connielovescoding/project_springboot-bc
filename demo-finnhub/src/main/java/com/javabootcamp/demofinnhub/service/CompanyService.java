package com.javabootcamp.demofinnhub.service;

import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.model.dto.finnhub.CompanyProfile2DTO;

public interface CompanyService {

    CompanyProfile2DTO getCompanyProfile(String symbol) throws FinnhubException;

    void refresh() throws FinnhubException;
    
}