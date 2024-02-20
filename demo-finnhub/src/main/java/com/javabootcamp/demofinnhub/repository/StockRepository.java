package com.javabootcamp.demofinnhub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javabootcamp.demofinnhub.entity.Stock;
import com.javabootcamp.demofinnhub.entity.StockSymbol;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByStockSymbol(StockSymbol stockSymbol);
    
}
