package com.rix.stock_tracker.controller.web;
import com.rix.stock_tracker.Service.CustomUserDetailsService;
import com.rix.stock_tracker.Service.TradeService;
import com.rix.stock_tracker.entity.Trade;
import com.rix.stock_tracker.entity.TradeDetails;
import com.rix.stock_tracker.entity.User;
import com.rix.stock_tracker.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private static final String TRADE_DETAIL_KEY = "tradeDetails";
    private final TradeService tradeService;
    private final TradeRepository repository;
    private final CustomUserDetailsService detailsService;

    public TradeController(final TradeService tradeService, final TradeRepository repository, final CustomUserDetailsService detailsService) {
        this.tradeService = tradeService;
        this.repository = repository;
        this.detailsService = detailsService;
    }

    @GetMapping("/all-trades")
    public String allTrades(Model model) {

        model.addAttribute(TRADE_KEY, tradeService.readAllTrades());

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
        model.addAttribute("trade_details", new TradeDetails());
        return "trades/add-trade";
    }

    @PostMapping("/process_add-trade")
    public String addTrade(Trade trade, TradeDetails tradeDetails) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = detailsService.loadUser(auth.getName());
        trade.setUser(user);

        trade.setTradeDetails(tradeDetails);
        repository.save(trade);

        return "redirect:/trade/all-trades";
    }

    @GetMapping("/show-trade-details/{id}")
    public String showTradeDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute(TRADE_DETAIL_KEY, tradeService.readDetailsById(id));
        return "trades/trade-details";
    }
}
