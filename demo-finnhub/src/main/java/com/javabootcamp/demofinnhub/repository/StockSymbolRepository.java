package com.javabootcamp.demofinnhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javabootcamp.demofinnhub.entity.StockSymbol;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbol, Long> {
    
}
