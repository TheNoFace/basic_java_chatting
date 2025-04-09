package com.example.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;



import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private ResponseEntity<Map<String, Object>> emptySuccess() {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public String  handleAllExceptions(Exception ex) {
        log.error("Unhandled Exception", ex);
        return "redirect:/login";
    }

    
}
