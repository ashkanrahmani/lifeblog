package com.lifeblog.blog.exception;

import com.lifeblog.blog.controller.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalHandler  {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException notFoundException,
                                                                        WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDate(new Date());
        errorDetails.setMessage(notFoundException.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationAPIException.class)
    public ResponseEntity<ErrorDetails> handleApplicationAPIException(ApplicationAPIException apiException,
                                                                        WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDate(new Date());
        errorDetails.setMessage(apiException.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
