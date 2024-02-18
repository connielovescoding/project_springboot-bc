package com.javabootcamp.demofinnhub.model.dto.webApp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class StockDTO {

    private CompanyProfileDTO companyProfile;

    private double currentPrice;
    
    private double dayHigh;
    
    private double dayLow;
    
    private double dayOpen;
    
    private double prevDayClose;

}
