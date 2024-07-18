package com.joedev.accountservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseMessage> handleBusinessException(BusinessException e){
        var response = new ResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleExceptions(Exception e){
        return new ResponseEntity<>(
                new ResponseMessage(
                        "An error occurred: " + e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
