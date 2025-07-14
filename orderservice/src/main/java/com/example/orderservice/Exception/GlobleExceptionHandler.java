package com.example.orderservice.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)

    public ResponseEntity<ErrorDetails> handleDataNotFound(DataNotFoundException ex, HttpServletRequest request){
        ErrorDetails error = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
