package com.rix.stock_tracker.controller.web;

import com.rix.stock_tracker.Service.TradeService;
import com.rix.stock_tracker.entity.Trade;
import com.rix.stock_tracker.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/trade")
public class TradeController {
    private static final String TRADE_KEY = "trades";
    private final TradeService tradeService;
    private final TradeRepository repository;

    public TradeController(final TradeService tradeService, final TradeRepository repository) {
        this.tradeService = tradeService;
        this.repository = repository;
    }

    @GetMapping("/all-trades")
    public String allTrades(Model model) {

        model.addAttribute(TRADE_KEY, tradeService.readAllStocks());

        return "trades/all-trades";
    }

    @GetMapping("/delete-trade/{id}")
    public String deleteCategoryById(@PathVariable("id") Long id) {
        log.info("deleting trade by id: [{}]", id);
        tradeService.deleteTradeById(id);

        return "redirect:/trade/all-trades";
    }

    @GetMapping("/add-trade")
    public String showAddTradeForm(Model model) {
        model.addAttribute("trade", new Trade());
        return "trades/add-trade";
    }

    @PostMapping("/process_add-trade")
    public String addTrade(Trade trade) {
        repository.save(trade);

        return "trades/all-trades";
    }
}
