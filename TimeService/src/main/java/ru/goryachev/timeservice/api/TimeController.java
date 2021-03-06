package ru.goryachev.timeservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goryachev.timeservice.service.TimeService;

import java.time.LocalDateTime;

/**
 * API получения текущего времени
 * @author Lev Goryachev
 * @version 1
 */

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private TimeService timeService;
    private static final Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public ResponseEntity<LocalDateTime> getTime () {
        logger.info("TimeController getTime() invocation (GET)");
        return new ResponseEntity<>(timeService.getCurrenTime(), HttpStatus.OK);
    }
}
