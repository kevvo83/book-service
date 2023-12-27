package com.kevpersonal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BookTitleNotFoundException.class)
    public ResponseEntity<String> handleBookTitleNotFoundException() {
        return new ResponseEntity<String>("Book Title Not Found", HttpStatus.BAD_REQUEST);
    }

}
