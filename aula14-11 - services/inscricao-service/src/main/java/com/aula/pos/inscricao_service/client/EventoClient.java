package com.aula.pos.inscricao_service.client;

import com.aula.pos.inscricao_service.dto.EventoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="evento-service")
public interface EventoClient {
    @GetMapping("/api/v1/eventos/{id}")
    EventoResponse findById(@PathVariable UUID id);
}
