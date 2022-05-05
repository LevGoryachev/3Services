package ru.goryachev.ui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.model.OrderDetail;
import ru.goryachev.ui.webclient.ConnectorToOrderService;
import ru.goryachev.ui.webclient.ConnectorToTimeService;

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
@PropertySource("classpath:application.yml")
public class ServiceAggregator {

    @Value("${urlscheme.3services.orderservice.subdomain.orders}")
    private String subDomainOrders;
    @Value("${urlscheme.3services.timeservice.subdomain.time}")
    private String subDomainTime;
    private static final Logger logger = LoggerFactory.getLogger(ServiceAggregator.class);

    private ConnectorToOrderService connectorToOrderService;
    private ConnectorToTimeService connectorToTimeService;

    @Autowired
    public ServiceAggregator(ConnectorToOrderService connectorToOrderService, ConnectorToTimeService connectorToTimeService) {
        this.connectorToOrderService = connectorToOrderService;
        this.connectorToTimeService = connectorToTimeService;
    }

    public List<Order> getOrders(){
        return connectorToOrderService.getAll(subDomainOrders);
    }

    public LocalDateTime getDateTime(){
        return connectorToTimeService.getDateTime(subDomainTime);
    }


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
                long code = i * 3L - j;
                detailA.setSerialNumber("435345345" + code);
                detailA.setProductName("Some Not Unique Name");
                detailA.setQty(7 + code);
                details.add(detailA);
            }
            orA.setOrderDetails(details);
            orders.add(orA);
        }
        return orders;
    }

    public LocalDateTime getTimeMock(){
        return LocalDateTime.now();
    }



}
