package com.aula.pos.evento_service.controller.v1;

import com.aula.pos.evento_service.dto.EventoRequest;
import com.aula.pos.evento_service.dto.EventoResponse;
import com.aula.pos.evento_service.dto.PartialEventoRequest;
import com.aula.pos.evento_service.service.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public Page<EventoResponse> findAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String estado,
            @PageableDefault(size = 10, sort = "dataHoraEvento", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return eventoService.findAll(nome, cidade, estado, pageable);
    }

    @GetMapping("/{id}")
    public EventoResponse findById(@PathVariable UUID id){
        return eventoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponse save(@Valid @RequestBody EventoRequest request){
        return eventoService.save(request);
    }

    @PutMapping("/{id}")
    public EventoResponse update(@PathVariable UUID id, @Valid @RequestBody EventoRequest request){
        return eventoService.update(id, request);
    }

    @PatchMapping("/{id}")
    public EventoResponse partialUpdate(@PathVariable UUID id, @Valid @RequestBody PartialEventoRequest request){
        return eventoService.partialUpdate(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        eventoService.deleteById(id);
    }
}
