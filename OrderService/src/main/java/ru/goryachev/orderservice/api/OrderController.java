package ru.goryachev.orderservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.orderservice.model.Order;
import ru.goryachev.orderservice.service.OrderService;

import java.util.List;

/**
 * API работы с Заказами
 * Исключения отлавливаются ru.goryachev.ControllerAdvisor
 * @author Lev Goryachev
 * @version 1
 */

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll () {
        logger.info("OrderController getAll() invocation (GET)");
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getById (@PathVariable Long orderId) {
        logger.info("OrderController getById() invocation (GET)");
        return new ResponseEntity<>(orderService.getById(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody Order order) {
        logger.info("OrderController create() invocation (POST)");
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update (@RequestBody Order modifiedOrder) {
        logger.info("OrderController update() invocation (POST)");
        return new ResponseEntity<>(orderService.save(modifiedOrder), HttpStatus.OK);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Object> delete (@PathVariable Long orderId) {
        logger.info("OrderController delete() invocation (DELETE)");
        return new ResponseEntity<>(orderService.delete(orderId), HttpStatus.OK);
    }
}
