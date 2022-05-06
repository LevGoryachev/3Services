package ru.goryachev.ui.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.goryachev.ui.controller.UiController;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private final static String ENCODING_UTF_8 = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(UiController.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initiating encoding filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Current encoding for request: {}, for response: {}.", servletRequest.getCharacterEncoding(), servletResponse.getCharacterEncoding());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(ENCODING_UTF_8);
        response.setCharacterEncoding(ENCODING_UTF_8);
        logger.info("Set encoding for request: {}, for response: {}.", request.getCharacterEncoding(), response.getCharacterEncoding());

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}