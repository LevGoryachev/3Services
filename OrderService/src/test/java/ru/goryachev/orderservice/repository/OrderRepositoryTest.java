package ru.goryachev.orderservice.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.goryachev.orderservice.app.WebAppInit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * CRUD JPA Repository testing with real DB.
 * Necessary to use Lifecycle.PER_CLASS (@TestInstance) to work with common id (testId variable is end-to-end id).
 * An entity with autogenerated ID suppose to be created in the first method (1).
 * The ID will be used in each method (2,4,5).
 * The entity with ID suppose to be deleted in the last one (5).
 * Setting WebEnvironment.NONE does not start the embedded servlet container (MultiChief does not use embedded).
 * @author Lev Goryachev
 * @version 1.0
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = WebAppInit.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRepositoryTest {

    Long testId;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
        order.setCustomerName("testValueAbc");
        order.setAddress("testValueDef");
        order.setSumm(BigDecimal.valueOf(1000.10));

        ru.goryachev.orderservice.model.Order savedConstruction = orderRepository.save(order);
        this.testId = savedConstruction.getId();
        Assertions.assertThat(savedConstruction.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findByIdTest(){
        ru.goryachev.orderservice.model.Order order = orderRepository.findById(testId).get();
        Assertions.assertThat(order.getId()).isEqualTo(testId);
    }
    @Test
    @Order(3)
    public void findAllTest(){
        List<ru.goryachev.orderservice.model.Order> orders = orderRepository.findAll();
        Assertions.assertThat(orders.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateTest(){

        ru.goryachev.orderservice.model.Order order = orderRepository.findById(testId).get();
        order.setAddress("testValueChanged");
        ru.goryachev.orderservice.model.Order orderUpdated =  orderRepository.save(order);
        Assertions.assertThat(orderUpdated.getAddress()).isEqualTo("testValueChanged");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteTest(){

        ru.goryachev.orderservice.model.Order order = orderRepository.findById(testId).get();
        orderRepository.delete(order);

        //orderRepo.deleteById(1L);

        ru.goryachev.orderservice.model.Order orderEmpty = null;
        Optional<ru.goryachev.orderservice.model.Order> optionalConstruction = orderRepository.findById(testId);

        if(optionalConstruction.isPresent()){
            orderEmpty = optionalConstruction.get();
        }
        Assertions.assertThat(orderEmpty).isNull();
    }
}