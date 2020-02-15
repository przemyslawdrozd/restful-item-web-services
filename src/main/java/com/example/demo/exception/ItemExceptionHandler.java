package com.example.demo.exception;

import com.example.demo.model.request.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ItemExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        LOG.error("Item not found");
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreateItemException.class)
        public ResponseEntity<Object> handleANullPointerException(CreateItemException ex, WebRequest request) {
        LOG.error("Invalid data for item");
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUserDatasourceException(Exception ex, WebRequest request) {
        LOG.error("Item throws exception");
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.toString());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}