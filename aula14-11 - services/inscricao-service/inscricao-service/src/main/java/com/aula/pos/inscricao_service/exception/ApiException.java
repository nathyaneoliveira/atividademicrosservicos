package com.aula.pos.inscricao_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException{

    private final HttpStatus status;

    public ApiException(HttpStatus status, String mensage){
        super(mensage);
        this.status = status;
    }
}
