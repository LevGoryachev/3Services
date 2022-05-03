package ru.goryachev.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goryachev.orderservice.model.Order;
import ru.goryachev.orderservice.repository.OrderDetailRepository;
import ru.goryachev.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис слой "Заказы"
 * @author Lev Goryachev
 * @version 1.0
 */

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> getAll(){
        logger.info("OrderService getAll() invocation");
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        logger.info("OrderService getById() invocation");
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID " + id + " not found"));
    }

    @Transactional
    public Order save(Order order){
        logger.info("OrderService save() invocation");
        if(order.getId() != null && orderRepository.existsById(order.getId())){
            logger.info("Case: updating the existing Order");
            order.getOrderDetails().forEach(item -> item.setOrderId(order.getId()));
            order.setCreatedDate(orderRepository.getById(order.getId()).getCreatedDate());
            Order savedOrder = orderRepository.save(order);
            if(!order.getOrderDetails().isEmpty()){
                orderDetailRepository.saveAll(savedOrder.getOrderDetails());
            }
            return order;
        }
        logger.info("Case: saving the new Order");
        order.setCreatedDate(LocalDateTime.now());
        order.setId(null);
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderDetails().forEach(item -> item.setOrderId(savedOrder.getId()));
        orderDetailRepository.saveAll(savedOrder.getOrderDetails());
        return savedOrder;
    }

    @Transactional
    public String delete (Long id) {
        logger.info("OrderService delete() invocation");
        orderRepository.deleteById(id);
        return "Order with id " + id + " was deleted";
    }
}
