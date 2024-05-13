package com.blogapp12.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebAppRootListener;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler
{


    @ExceptionHandler(UserNotPresentException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotPresentException e, WebRequest request)
    {
        ErrorFlow er=new ErrorFlow(LocalDateTime.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }



}
