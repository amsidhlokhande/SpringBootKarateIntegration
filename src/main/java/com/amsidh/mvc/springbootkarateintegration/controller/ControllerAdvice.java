package com.amsidh.mvc.springbootkarateintegration.controller;

import com.amsidh.mvc.springbootkarateintegration.exception.BadRequestException;
import com.amsidh.mvc.springbootkarateintegration.exception.ForbiddenException;
import com.amsidh.mvc.springbootkarateintegration.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = {BadRequestException.class})
    public String badRequestExceptionHandling(BadRequestException badRequestException) {
        return badRequestException.getMessage();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public String nodFoundExceptionHandling(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public String nodFoundExceptionHandling(ForbiddenException forbiddenException) {
        return forbiddenException.getMessage();
    }
}
