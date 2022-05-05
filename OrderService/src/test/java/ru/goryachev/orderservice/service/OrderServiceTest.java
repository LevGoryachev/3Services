package ru.goryachev.orderservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.goryachev.orderservice.app.WebAppInit;
import ru.goryachev.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * CRUD service-layer testing using mocks.
 * Each method in different object (Lifecycle.PER_METHOD)
 * @author Lev Goryachev
 * @version 1.0
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = WebAppInit.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(MockitoExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    public void getByIdTest(){
        ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
        order.setId(2L);
        order.setCustomerName("testValueAbc");
        order.setAddress("testValueDef");
        order.setSumm(BigDecimal.valueOf(12345,67));
        when(orderRepository.findById(2L)).thenReturn(Optional.of(order));
        ru.goryachev.orderservice.model.Order orderRead = orderService.getById(2L);
        Assertions.assertThat(orderRead.getId()).isEqualTo(2L);
    }

    @Test
    @Order(2)
    public void getAllTest(){
        List<ru.goryachev.orderservice.model.Order> orders = new ArrayList<>();
        for (Integer i = 1; i < 5; i++){
            ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
            order.setId(i.longValue());
            order.setCustomerName("testValueAbc" + i);
            order.setAddress("testValueDef"+ i);
            order.setSumm(BigDecimal.valueOf(12345,67));
            orders.add(order);
        }
        when(orderRepository.findAll()).thenReturn(orders);
        List<ru.goryachev.orderservice.model.Order> ordersRead = orderService.getAll();
        Assertions.assertThat(ordersRead.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void deleteTest(){
        Long testId = 2L;
        orderService.delete(testId);
        verify(orderRepository).deleteById(testId);
    }
}