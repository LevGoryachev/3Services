package ru.goryachev.ui.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * Клиент для подключения к сервису получения даты/времени TimeService
 * есть все CRUD (AbstractConnector)
 * @author Lev Goryachev
 * @version 1.0
 */

@Component
@PropertySource("classpath:application.yml")
public class ConnectorToTimeService  extends RestTemplate {

    protected String domainUrl;
    protected String apiVersion;

    private ConnectorToTimeService(@Value("${urlscheme.3services.timeservice.domain}") String domainUrl,
                                   @Value("${urlscheme.3services.timeservice.apiversion}") String apiVersion) {
        this.domainUrl = domainUrl;
        this.apiVersion = apiVersion;
    }

    public LocalDateTime getDateTime(String subDomain) {
        StringBuffer urlBuilder = new StringBuffer()
                .append(domainUrl)
                .append(apiVersion)
                .append(subDomain);
        ResponseEntity<LocalDateTime> response = this.exchange(urlBuilder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<LocalDateTime>(){});
        return response.getBody();
    }
}
