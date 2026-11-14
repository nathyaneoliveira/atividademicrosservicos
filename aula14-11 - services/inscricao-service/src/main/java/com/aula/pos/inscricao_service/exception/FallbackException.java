package com.aula.pos.inscricao_service.exception;

import org.springframework.http.HttpStatus;

public class FallbackException extends ApiException{
    public FallbackException(HttpStatus status, String mensage) {
        super(status, mensage);
    }
}
