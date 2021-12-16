package com.rix.stock_tracker.repository;

import com.rix.stock_tracker.entity.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeDetailRepository extends JpaRepository<TradeDetails, Long> {
}
