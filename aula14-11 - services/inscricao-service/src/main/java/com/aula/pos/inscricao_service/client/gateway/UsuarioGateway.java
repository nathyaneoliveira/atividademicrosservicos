package com.aula.pos.inscricao_service.client.gateway;

import com.aula.pos.inscricao_service.client.UsuarioClient;
import com.aula.pos.inscricao_service.dto.UsuarioResponse;
import com.aula.pos.inscricao_service.exception.FallbackException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioGateway {
    private final UsuarioClient usuarioClient;

    @CircuitBreaker(name="buscarUsuario", fallbackMethod = "erroBuscarUsuario")
    public UsuarioResponse findById(UUID id){
        return usuarioClient.findById(id);
    }

    private  UsuarioResponse erroBuscarUsuario(UUID id, Exception ex){
        throw new FallbackException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Serviço de usuário está indisponível"
        );
    }
}
