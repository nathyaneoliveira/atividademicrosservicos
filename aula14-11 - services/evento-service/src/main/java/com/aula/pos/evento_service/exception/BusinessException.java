package com.aula.pos.evento_service.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException{
    public BusinessException(String mensage) {
        super(HttpStatus.UNPROCESSABLE_CONTENT, mensage);
    }
}
