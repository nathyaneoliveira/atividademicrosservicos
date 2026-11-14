package com.aula.pos.auth_service.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ResourceNotFoundException extends ApiException{
    public ResourceNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Recurso não encontrado: " + id);
    }
}
