package ru.goryachev.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goryachev.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsById(Long id);
}
