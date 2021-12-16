package com.rix.stock_tracker.dto;

public record TradeDetailSummary(Long id,
                                 double close_price,
                                 double commission,
                                 double open_price,
                                 double profit) {
}
