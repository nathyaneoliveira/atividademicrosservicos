package com.aula.pos.inscricao_service.handler;

import com.aula.pos.inscricao_service.dto.ErrorResponse;
import com.aula.pos.inscricao_service.exception.ApiException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ErrorResponse handleResourceNotFoundException(ApiException ex){
        return build(ex.getStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map((e) -> new ErrorResponse.FieldErrorResponse(e.getField(), e.getDefaultMessage()))
                .toList();

        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), fields);
    }

    @ExceptionHandler(FeignException.class)
    public ErrorResponse handleFeignException(FeignException ex){
        return build(HttpStatus.BAD_GATEWAY, "Erro ao consultar serviço externo de Cep", null);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception ex){
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }

    private ErrorResponse build(HttpStatus httpStatus, String message, List<ErrorResponse.FieldErrorResponse> fieldError){
        return new ErrorResponse(LocalDateTime.now(), httpStatus, message, fieldError);
    }
}
