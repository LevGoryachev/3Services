package ru.goryachev.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goryachev.orderservice.model.Order;
import ru.goryachev.orderservice.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис слой "Заказы"
 * @author Lev Goryachev
 * @version 1.0
 */

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID " + id + " not found"));
    }

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public String delete (Long id) {
        orderRepository.deleteById(id);
        return "Entity with id " + id + " was deleted";
    }
}
