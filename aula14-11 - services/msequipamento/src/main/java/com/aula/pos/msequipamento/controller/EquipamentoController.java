package com.aula.pos.msequipamento.controller;

import com.aula.pos.msequipamento.dto.CondicaoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.dto.ReservaRequest;
import com.aula.pos.msequipamento.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoResponse cadastrar(@Valid @RequestBody EquipamentoRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping("/{id}")
    public EquipamentoResponse buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PatchMapping("/{id}/condicao")
    public EquipamentoResponse alterarCondicao(
            @PathVariable UUID id,
            @Valid @RequestBody CondicaoRequest request
    ) {
        return service.alterarCondicao(id, request);
    }

    @PatchMapping("/{id}/reserva")
    public EquipamentoResponse alterarReserva(
            @PathVariable UUID id,
            @Valid @RequestBody ReservaRequest request
    ) {
        return service.alterarReserva(id, request);
    }
}
