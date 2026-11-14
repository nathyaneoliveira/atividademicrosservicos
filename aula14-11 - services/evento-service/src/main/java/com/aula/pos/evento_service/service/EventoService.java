package com.aula.pos.evento_service.service;

import com.aula.pos.evento_service.dto.EventoRequest;
import com.aula.pos.evento_service.dto.EventoResponse;
import com.aula.pos.evento_service.dto.PartialEventoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventoService {

    Page<EventoResponse> findAll(String nome, String cidade, String estado, Pageable pageable);
    EventoResponse findById(UUID id);
    EventoResponse save(EventoRequest request);
    EventoResponse update(UUID id, EventoRequest request);
    EventoResponse partialUpdate(UUID id, PartialEventoRequest request);
    void deleteById(UUID id);
}
