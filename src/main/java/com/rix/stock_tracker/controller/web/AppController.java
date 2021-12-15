package com.rix.stock_tracker.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
}
