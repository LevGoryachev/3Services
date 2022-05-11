package ru.goryachev.ui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.goryachev.ui.exception.MicroServiceNotAnswerException;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.webclient.ConnectorToOrderService;
import ru.goryachev.ui.webclient.ConnectorToTimeService;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис, который работает с API других сервисов Service that works with APIs
 * Current time service
 * @author Lev Goryachev
 * @version 1.0
 */

@Service
@PropertySource("classpath:application.yml")
public class ServiceAggregator {

    @Value("${connector.orderservice.urlscheme.subdomain.orders}")
    private String subDomainOrders;
    @Value("${connector.timeservice.urlscheme.subdomain.time}")
    private String subDomainTime;
    private static final Logger logger = LoggerFactory.getLogger(ServiceAggregator.class);

    private ConnectorToOrderService connectorToOrderService;
    private ConnectorToTimeService connectorToTimeService;

    @Autowired
    public ServiceAggregator(ConnectorToOrderService connectorToOrderService, ConnectorToTimeService connectorToTimeService) {
        this.connectorToOrderService = connectorToOrderService;
        this.connectorToTimeService = connectorToTimeService;
    }

    public Map<String, Object> getAttributes (){

        Map<String, Object> result = new HashMap<>();

        try{
            logger.info("connectorToOrderService.getAll invocation");
            result.put("orderService", connectorToOrderService.getAll(subDomainOrders));
        } catch (Exception e){
            logger.warn("No response from OrderService");
            e.printStackTrace();
            throw new MicroServiceNotAnswerException("OrderService");
        }

        if(!result.isEmpty()) {
            logger.info("connectorToTimeService.getAll invocation");
            try {
                result.put("timeService", connectorToTimeService.get(subDomainTime));
            } catch (Exception e) {
                logger.warn("No response from TimeService");
                e.printStackTrace();
            }
        }

        return result;
    }

    public Order findById (Long id) {
        logger.info("connectorToTimeService.findById invocation");
        return connectorToOrderService.findById(subDomainOrders, id);
    }

    public Order update (Order order){
        logger.info("connectorToTimeService.update invocation");
        Order x = order;
        return connectorToOrderService.update(subDomainOrders, x);
    }

    /*public Object save(String subDomain, Map<String, Object> requestDto) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(domainUrl)
                .append(apiVersion)
                .append(subDomain);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpRequest = new HttpEntity(requestDto, headers);
        ResponseEntity<Object> response = this.exchange(urlBuilder.toString(), HttpMethod.POST, httpRequest, Object.class);
        return response.getBody();
    }

    public Object delete(String subDomain, Long id) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(domainUrl)
                .append(apiVersion)
                .append(subDomain)
                .append(id);
        ResponseEntity<Object> response = this.exchange(urlBuilder.toString(), HttpMethod.DELETE, null, Object.class);
        return response.getBody();
    }*/

}
