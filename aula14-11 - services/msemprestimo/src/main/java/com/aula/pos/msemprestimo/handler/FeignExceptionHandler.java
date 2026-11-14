package com.aula.pos.msemprestimo.handler;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ProblemDetail> handleFeignException(FeignException exception) {
        var status = exception.status() >= 400 && exception.status() < 500
                ? HttpStatusCode.valueOf(exception.status())
                : HttpStatus.BAD_GATEWAY;
        var problem = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
        return ResponseEntity.status(status).body(problem);
    }
}
