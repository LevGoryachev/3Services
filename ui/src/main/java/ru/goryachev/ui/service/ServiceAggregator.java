package ru.goryachev.ui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.model.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сервис, который работает с API других сервисов Service that works with APIs
 * Current time service
 * @author Lev Goryachev
 * @version 1.0
 */

@Service
public class ServiceAggregator {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAggregator.class);


    public List<Order> getOrdersMock(){
        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < 15; i++){
            Order orA = new Order();
            orA.setId(1L + i);
            orA.setCustomerName("Ivanov");
            orA.setAddress("Addr" + i);
            orA.setSumm(new BigDecimal(34567 + i*24));
            orA.setCreatedDate(LocalDateTime.now());
            Set<OrderDetail> details = new HashSet<>();
            for (long j = 0; j < 5; j++){
                OrderDetail detailA = new OrderDetail();
                detailA.setItemNumber(1L + j);
                //long code = j * 3L;
                detailA.setSerialNumber("435345345");
                detailA.setProductName("Some Not Unique Name");
                detailA.setQty(7 + j*2);
                details.add(detailA);
            }
            orA.setOrderDetails(details);
            orders.add(orA);
        }
        return orders;
    }

}
