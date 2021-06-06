package com.amsidh.mvc.springbootkarateintegration.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
