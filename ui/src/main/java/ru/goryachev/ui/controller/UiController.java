package ru.goryachev.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.goryachev.ui.service.ServiceAggregator;

/**
 * Веб-интерфейс Web-interface
 * @author Lev Goryachev
 * @version 1
 */

@Controller
@RequestMapping("/orders")
public class UiController {

    private ServiceAggregator serviceAggregator;

    @Autowired
    public UiController(ServiceAggregator serviceAggregator) {
        this.serviceAggregator = serviceAggregator;
    }

    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("orders", serviceAggregator.getOrders());
        model.addAttribute("receivedTime", serviceAggregator.getDateTime());
        return "view-orders";
    }
}
