package com.dku.stock.dbservice.repository;

import com.dku.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer>{
    List<Quote> findByUserName(String username);
}
