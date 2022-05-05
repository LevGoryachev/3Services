package ru.goryachev.ui.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.goryachev.ui")
public class WebAppInit extends SpringBootServletInitializer {

}
