package com.javabootcamp.demofinnhub.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "finnhub_stock_price")
@Getter
@Setter
@Builder
public class StockPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "datetime")
    private final LocalDateTime dateTime = LocalDateTime.now();

    @Column(name = "current_price", columnDefinition = "NUMERIC(15,2)")
    private Double currentPrice;

    @Column(name = "day_high", columnDefinition = "NUMERIC(15,2)")
    private Double dayHigh;

    @Column(name = "day_low", columnDefinition = "NUMERIC(15,2)")
    private Double dayLow;

    @Column(name = "day_open", columnDefinition = "NUMERIC(15,2)")
    private Double dayOpen;

    @Column(name = "prev_day_close", columnDefinition = "NUMERIC(15,2)")
    private Double prevDayClose;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

}
