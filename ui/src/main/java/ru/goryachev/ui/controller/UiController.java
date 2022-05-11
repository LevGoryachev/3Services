package ru.goryachev.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.service.ServiceAggregator;

/**
 * Веб-интерфейс Web-interface
 * Exceptions отлавливаются при помощи exception/ControllerAdvisor (RestControllerAdvice)
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
        logger.info("viewOrders() invocation (GET)");
        model.addAttribute("attributes", serviceAggregator.getAttributes());
        return "view-orders";
    }

    @GetMapping("/edit/{orderId}")
    public String editOrders(Model model, @PathVariable("orderId") Long orderId) {
        logger.info("editOrders() invocation (GET) to go to edit-page");
        model.addAttribute("order", serviceAggregator.findById(orderId));
        return "edit-orders";
    }

    @RequestMapping(value = "/edit/update", method = RequestMethod.POST)
    public String updateOrder (@ModelAttribute("order") Order order) {
        Order x = order;
        serviceAggregator.update(x);
        return "redirect:/orders";
    }

    /*@RequestMapping(value = "/createorder")
    public String createOrder () {
        Order order = new Order();
        order.setCustomerName("ExpCreatedCl");
        order.setAddress("ExpCreatedAddr");
        order.setSumm(BigDecimal.valueOf(1000));
        Order x = order;
        serviceAggregator.update(x);
        return "redirect:/orders";
    }*/


}
