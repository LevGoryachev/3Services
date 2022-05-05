package ru.goryachev.ui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Ловит исключения в контроллерах и помещает их в ответ ResponseEntity;
 * Сюда можно добавить свои кастомные Exceptions
 * @author Lev Goryachev
 * @version 1
 */
@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleS(Exception ex, WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        String h = ex.getCause().getMessage();
        String g = ex.getLocalizedMessage();
        String m = ex.getMessage();

        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("cause: ", h);
        responseBody.put("localized message: ", g);
        responseBody.put("message: ", m);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
