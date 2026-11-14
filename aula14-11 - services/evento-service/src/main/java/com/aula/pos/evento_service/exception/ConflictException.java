package com.aula.pos.evento_service.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException{
    public ConflictException(String mensage) {
        super(HttpStatus.CONFLICT, mensage);
    }
}
