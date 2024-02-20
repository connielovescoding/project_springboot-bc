package com.javabootcamp.demofinnhub.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.javabootcamp.demofinnhub.entity.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
    
    @Query("SELECT sp FROM StockPrice sp WHERE sp.stock.stockSymbol.symbol = :symbol ORDER BY sp.dateTime DESC")
    List<StockPrice> findTopNByStockSymbolOrderByDateTimeDesc(String symbol, Pageable pageable);

    
}
