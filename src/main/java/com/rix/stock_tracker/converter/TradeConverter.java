package com.rix.stock_tracker.converter;

import com.rix.stock_tracker.dto.TradeSummary;
import com.rix.stock_tracker.entity.Trade;
import org.springframework.stereotype.Component;

@Component
public class TradeConverter implements MappingOperations<Trade, TradeSummary> {


    @Override
    public TradeSummary fromEntityToDto(Trade entity) {
        return new TradeSummary(entity.getId(), entity.getName(), entity.getCreationTimestamp(), entity.getUpdateTimestamp());
    }

    @Override
    public Trade fromDtoToEntity(TradeSummary dto) {
        return Trade.builder()
                .id(dto.id())
                .name(dto.name())
                .creationTimestamp(dto.created())
                .updateTimestamp(dto.updated())
                .build();
    }
}
