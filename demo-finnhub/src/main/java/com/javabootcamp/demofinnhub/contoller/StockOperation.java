package com.javabootcamp.demofinnhub.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.javabootcamp.demofinnhub.exception.FinnhubException;
import com.javabootcamp.demofinnhub.infra.ApiResp;
import com.javabootcamp.demofinnhub.model.dto.webApp.StockDTO;
import com.javabootcamp.demofinnhub.model.dto.webApp.SymbolReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface StockOperation {

    @Operation(summary = "Get Finnhub Stock Data",
            description = "This endpoint retrieves a stock data from Finnhub Endpoints (/stock).",
            tags = "Get a Stock",
            parameters = {@Parameter(name = "SymbolReqDTO",
                    description = "Stock Symbol", required = true)})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {@Content(
                            schema = @Schema(implementation = StockDTO.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500",
                    content = {@Content(schema = @Schema())})})
    @GetMapping(value = "/stock")
    @ResponseStatus(value = HttpStatus.OK)
    ApiResp<StockDTO> stockInfo(
            @RequestParam("symbol") SymbolReqDTO symbol)
            throws FinnhubException;

}
