package ru.goryachev.ui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.webclient.ConnectorToOrderService;
import ru.goryachev.ui.webclient.ConnectorToTimeService;

import java.time.LocalDateTime;
import java.util.List;

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
        logger.info("ServiceAggregator getOrders() invocation");
        return connectorToOrderService.getAll(subDomainOrders);
    }

    public LocalDateTime getDateTime(){
        logger.info("ServiceAggregator getDateTime() invocation");
        return connectorToTimeService.getDateTime(subDomainTime);
    }
}
