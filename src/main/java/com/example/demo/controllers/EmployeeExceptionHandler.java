package com.example.demo.controllers;

import com.example.demo.validation.IllegalRequestInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> all(Exception ex) {
        log.error("Error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\": \"Something went wrong. Please contact administrator.\"}");
    }

    @ExceptionHandler(IllegalRequestInputException.class)
    public ResponseEntity<String> invalidRequestBody(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
