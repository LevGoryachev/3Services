package ru.goryachev.timeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Сервис слой предоставления текущего времени
 * Current time service
 * @author Lev Goryachev
 * @version 1.0
 */

@Service
public class TimeService {

    private static final Logger logger = LoggerFactory.getLogger(TimeService.class);

    public LocalDateTime getCurrenTime(){
        logger.info("TimeService getCurrenTime() invocation");
        return LocalDateTime.now();
    }
}
