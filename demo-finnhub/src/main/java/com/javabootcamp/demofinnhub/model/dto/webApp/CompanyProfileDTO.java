package com.javabootcamp.demofinnhub.model.dto.webApp;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanyProfileDTO {
    
    private String country;

    private String companyName;

    private LocalDate ipoDate;

    private String logo;

    private double marketCap;

    private String currency;

}
