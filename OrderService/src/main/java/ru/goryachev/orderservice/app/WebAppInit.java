package ru.goryachev.orderservice.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "ru.goryachev.orderservice")
@EnableJpaRepositories (basePackages = "ru.goryachev.orderservice")
@EntityScan (basePackages = "ru.goryachev.orderservice")
public class WebAppInit extends SpringBootServletInitializer {

}
