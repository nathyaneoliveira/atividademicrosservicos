package com.aula.pos.msemprestimo.client;

import com.aula.pos.msemprestimo.dto.CondicaoRequest;
import com.aula.pos.msemprestimo.dto.EquipamentoResponse;
import com.aula.pos.msemprestimo.dto.ReservaRequest;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class EquipamentoGateway {

    private final EquipamentoClient client;

    public EquipamentoGateway(EquipamentoClient client) {
        this.client = client;
    }

    @CircuitBreaker(name = "alterarCondicaoEquipamento", fallbackMethod = "alterarCondicaoFallback")
    public EquipamentoResponse alterarCondicao(UUID id, CondicaoRequest request) {
        return client.alterarCondicao(id, request);
    }

    public EquipamentoResponse alterarReserva(UUID id, boolean reservado) {
        return client.alterarReserva(id, new ReservaRequest(reservado));
    }

    private EquipamentoResponse alterarCondicaoFallback(UUID id, CondicaoRequest request, Throwable exception) {
        if (exception instanceof FeignException feignException
                && feignException.status() >= 400 && feignException.status() < 500) {
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(feignException.status()),
                    "msequipamento rejeitou a alteracao da condicao",
                    feignException
            );
        }
        if (exception instanceof CallNotPermittedException) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Circuit Breaker aberto", exception);
        }
        throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "msequipamento indisponivel; devolucao nao concluida",
                exception
        );
    }
}
