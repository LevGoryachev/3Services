package ru.goryachev.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

    @Autowired
    public UiController(ServiceAggregator serviceAggregator) {
        this.serviceAggregator = serviceAggregator;
    }

    @GetMapping
    public String viewOrders(Model model) {
        logger.info("UI Controller viewOrders() invocation (GET)");
        model.addAttribute("orders", serviceAggregator.getOrders());
        model.addAttribute("receivedTime", serviceAggregator.getDateTime());
        return "view-orders";
    }
}
