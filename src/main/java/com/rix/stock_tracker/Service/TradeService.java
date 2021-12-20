package com.rix.stock_tracker.Service;

import com.rix.stock_tracker.converter.TradeConverter;
import com.rix.stock_tracker.dto.TradeSummary;
import com.rix.stock_tracker.entity.Trade;
import com.rix.stock_tracker.entity.TradeDetails;
import com.rix.stock_tracker.exceptions.DeletingNonExistentObject;
import com.rix.stock_tracker.repository.TradeDetailRepository;
import com.rix.stock_tracker.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeService {

    private final TradeRepository repository;
    private final TradeDetailRepository detailRepository;
    private final TradeConverter converter;

    public TradeService(final TradeRepository repository, final TradeDetailRepository detailRepository, final TradeConverter converter) {
        this.repository = repository;
        this.detailRepository = detailRepository;
        this.converter = converter;
    }

    public List<TradeSummary> readAllTrades() {
        log.info("reading all stocks");

        var result = repository.findAll()
                .stream()
                .map(converter::fromEntityToDto)
                .toList();

        log.info("number of read stocks: [{}]", result.size());
        log.info("result: {}", result);

        return result;
    }

    public Optional<TradeSummary> readTradeById(Long id) {
        var result = repository.findById(id);
        log.info("item with id: [{}] exists? - [{}]", id, result.isPresent());
        log.debug("received trade: [{}]", result.orElse(null));
        return result.map(trade -> converter.fromEntityToDto(trade));
    }

    public Trade getTradeById(Long id) {
        Optional<Trade> trade = repository.findById(id);
        Trade trade1 = new Trade();
        trade1.setId(trade.get().getId());
        trade1.setName(trade.get().getName());
        trade1.setCreationTimestamp(trade.get().getCreationTimestamp());
        trade1.setUpdateTimestamp(trade.get().getUpdateTimestamp());
        return trade1;
    }

    public TradeDetails readDetailsById(Long id) {
        Optional<Trade> trade = repository.findById(id);
        TradeDetails details = trade.get().getTradeDetails();
        return details;
    }

    @Transactional
    public TradeSummary createNewTrade(TradeSummary newTrade) {
        Trade toSave = converter.fromDtoToEntity(newTrade);
        Trade saved = repository.save(toSave);

        log.info("creating new trade");
        log.info("object before conversion: [{}]", newTrade);
        log.info("object after conversion: [{}]", toSave);
        log.info("saved object: [{}]", saved);

        return converter.fromEntityToDto(saved);
    }

    @Transactional
    public void deleteTradeById(Long idOfTradeToDelete) throws DeletingNonExistentObject {
        log.info("deleting trade with id: [{}]", idOfTradeToDelete);

        if (repository.existsById(idOfTradeToDelete)) {
            repository.deleteById(idOfTradeToDelete);
        } else {
            var exception = new DeletingNonExistentObject(String.format("You're trying to delete non existent category with id: [%d]", idOfTradeToDelete));
            log.warn("problem with deleting trade", exception);
            throw exception;
        }
    }
}
