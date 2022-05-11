package ru.goryachev.ui.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.goryachev.ui.service.ServiceAggregator;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Клиент для подключения к сервису получения даты/времени TimeService
 * @author Lev Goryachev
 * @version 1.0
 */

@Component
@PropertySource("classpath:application.yml")
public class ConnectorToTimeService {

    private final String DOMAIN_URL;
    private final String API_VERSION;
    private final Integer TIMEOUTS;

    private static final Logger logger = LoggerFactory.getLogger(ServiceAggregator.class);

    private ConnectorToTimeService(@Value("${connector.timeservice.urlscheme.domain}") String domainUrl,
                                   @Value("${connector.timeservice.urlscheme.apiversion}") String apiVersion,
                                   @Value("${connector.timeservice.timeouts}") Integer timeouts) {
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

    public LocalDateTime get(String subDomain) {
        LocalDateTime result = webClient().get()
                .uri(subDomain)
                .retrieve()
                .bodyToMono(LocalDateTime.class)
                .block();
        return result;
    }
}