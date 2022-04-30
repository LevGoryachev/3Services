package ru.goryachev.orderservice.api;

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

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll () {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getById (@PathVariable Long id) {
            return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create (@RequestBody Order answer) {
        return new ResponseEntity<>(orderService.save(answer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update (@RequestBody Order modifiedOrder) {
        return new ResponseEntity<>(orderService.save(modifiedOrder), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id) {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }
}
