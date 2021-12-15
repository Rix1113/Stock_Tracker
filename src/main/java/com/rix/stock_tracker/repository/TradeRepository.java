package com.rix.stock_tracker.repository;

import com.rix.stock_tracker.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
