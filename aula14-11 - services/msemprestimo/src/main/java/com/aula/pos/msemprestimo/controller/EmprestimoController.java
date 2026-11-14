package com.aula.pos.msemprestimo.controller;

import com.aula.pos.msemprestimo.dto.DevolucaoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.service.EmprestimoService;
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
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponse criar(@Valid @RequestBody EmprestimoRequest request) {
        return service.criar(request);
    }

    @GetMapping("/{id}")
    public EmprestimoResponse buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PatchMapping("/{id}/devolucao")
    public EmprestimoResponse devolver(
            @PathVariable UUID id,
            @Valid @RequestBody DevolucaoRequest request
    ) {
        return service.devolver(id, request);
    }
}
