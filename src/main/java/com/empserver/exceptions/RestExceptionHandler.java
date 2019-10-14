package com.empserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler   {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  Error empNotFound(EntityNotFoundException exception) {
        //return exception.getMessage();
        return new Error(4, exception.getMessage());
    }
//    public ResponseEntity<String> empNotFound(EntityNotFoundException exception) {
//        return new ResponseEntity<String>("error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
