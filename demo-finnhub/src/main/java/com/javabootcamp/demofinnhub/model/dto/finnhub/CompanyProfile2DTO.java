package com.javabootcamp.demofinnhub.model.dto.finnhub;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CompanyProfile2DTO implements Serializable {
    
    private String country;

    private String currency;

    private String exchange;

    @JsonProperty(value = "ipo")
    private LocalDate ipoDate;
    
    @JsonProperty(value = "marketCapitalization")
    private double marketCap;

    @JsonProperty(value = "name")
    private String companyName;

    private String phone;

    private double shareOutstanding;

    private String ticker;

    private String weburl;
    
    private String logo;
    
    private String finnhubIndustry;

}