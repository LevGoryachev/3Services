package ru.goryachev.ui.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.goryachev.ui.model.Order;

import java.util.List;

/**
 * Клиент для подключения к сервису заказов OrderService
 * есть все CRUD (AbstractConnector)
 * @author Lev Goryachev
 * @version 1.0
 */

@Component
@PropertySource("classpath:application.yml")
public class ConnectorToOrderService extends RestTemplate {

    protected String domainUrl;
    protected String apiVersion;

    private ConnectorToOrderService(@Value("${urlscheme.3services.orderservice.domain}") String domainUrl,
                                    @Value("${urlscheme.3services.orderservice.apiversion}") String apiVersion) {
        this.domainUrl = domainUrl;
        this.apiVersion = apiVersion;
    }

    public List<Order> getAll(String subDomain) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(domainUrl)
                .append(apiVersion)
                .append(subDomain);
        ResponseEntity<List<Order>> response = this.exchange(urlBuilder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>(){});
        return response.getBody();
    }
}
