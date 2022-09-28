package com.loiko.alex.dreamcarrent.controller;

import com.loiko.alex.dreamcarrent.model.utils.ErrorMessage;
import com.loiko.alex.dreamcarrent.model.utils.ResponseWrapper;
import com.loiko.alex.dreamcarrent.model.utils.RestErrorList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public @ResponseBody ResponseEntity<ResponseWrapper> handleException(HttpServletRequest request, ResponseWrapper responseWrapper) {
        return ResponseEntity.ok(responseWrapper);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseWrapper> handleIOException(HttpServletRequest request, RuntimeException e) {
        var errorList = new RestErrorList(NOT_ACCEPTABLE, new ErrorMessage(e.getMessage(), e.getMessage()));
        var responseWrapper = new ResponseWrapper(null, singletonMap("status", NOT_ACCEPTABLE), errorList);
        return ResponseEntity.ok(responseWrapper);
    }
}