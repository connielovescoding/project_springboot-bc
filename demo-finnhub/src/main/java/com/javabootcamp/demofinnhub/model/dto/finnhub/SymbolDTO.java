package com.javabootcamp.demofinnhub.model.dto.finnhub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SymbolDTO {
    
    private String currency;
    
    @JsonProperty(value = "description")
    private String desc;

    private String displaySymbol;

    @JsonProperty(value = "figi")
    private String figiIdentifier;

    @JsonProperty(value = "mic")
    private String exchangeMic;

    private String symbol;

    private String type;

}