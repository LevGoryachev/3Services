package ru.goryachev.ui.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "ru.goryachev.ui")
@EnableSwagger2
public class WebAppInit extends SpringBootServletInitializer {

}
