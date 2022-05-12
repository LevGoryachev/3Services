package ru.goryachev.ui.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.goryachev.ui.model.Order;
import ru.goryachev.ui.service.ServiceAggregator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Клиент для подключения к сервису заказов OrderService
 * @author Lev Goryachev
 * @version 1.0
 */

@Component
@PropertySource("classpath:application.yml")
public class ConnectorToOrderService {

    private final String DOMAIN_URL;
    private final String API_VERSION;
    private final Integer TIMEOUTS;

    private static final Logger logger = LoggerFactory.getLogger(ServiceAggregator.class);

    private ConnectorToOrderService(@Value("${connector.orderservice.urlscheme.domain}") String domainUrl,
                                    @Value("${connector.orderservice.urlscheme.apiversion}") String apiVersion,
                                    @Value("${connector.orderservice.timeouts}") Integer timeouts) {
        this.DOMAIN_URL = domainUrl;
        this.API_VERSION = apiVersion;
        this.TIMEOUTS = timeouts;
    }

    private WebClient webClient() {
        final var tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUTS)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUTS, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUTS, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder()
                .baseUrl(DOMAIN_URL + API_VERSION)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    public List<Order> getAll(String subDomain) {
        List<Order> result = webClient()
                .get()
                .uri(subDomain)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {})
                .block();
        return result;
    }

    public Order findById(String subDomain, Long id) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(subDomain)
                .append(id);
        String url = urlBuilder.toString();

        Order result = webClient()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return result;
    }

    public Order update(String subDomain, Order order) {

        Order result = webClient()
                .put()
                .uri(subDomain)
                //.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return result;
    }

    public Order create(String subDomain, Order order) {

        Order result = webClient()
                .post()
                .uri(subDomain)
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return result;
    }

    public Order delete(String subDomain, Long id) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(subDomain)
                .append(id);
        String url = urlBuilder.toString();

        Order result = webClient()
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return result;
    }
}