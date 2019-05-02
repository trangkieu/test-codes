package com.autogeneral.controller;

import com.autogeneral.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@Controller
public class ExceptionErrorHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(new ErrorMessage("Unexpected Error"), HttpStatus.INTERNAL_SERVER_ERROR);

    }



}
