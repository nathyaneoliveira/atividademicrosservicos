package com.aula.pos.msequipamento.service;

import com.aula.pos.msequipamento.dto.CondicaoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.dto.ReservaRequest;
import com.aula.pos.msequipamento.model.Equipamento;
import com.aula.pos.msequipamento.repository.EquipamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EquipamentoResponse cadastrar(EquipamentoRequest request) {
        return EquipamentoResponse.from(repository.save(new Equipamento(request.nome())));
    }

    @Transactional(readOnly = true)
    public EquipamentoResponse buscar(UUID id) {
        return EquipamentoResponse.from(buscarEntidade(id));
    }

    @Transactional
    public EquipamentoResponse alterarCondicao(UUID id, CondicaoRequest request) {
        var equipamento = buscarEntidade(id);
        equipamento.alterarCondicao(request.condicao());
        return EquipamentoResponse.from(repository.save(equipamento));
    }

    @Transactional
    public EquipamentoResponse alterarReserva(UUID id, ReservaRequest request) {
        var equipamento = buscarEntidade(id);
        if (request.reservado() && equipamento.isReservado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipamento ja reservado");
        }
        equipamento.alterarReserva(request.reservado());
        return EquipamentoResponse.from(repository.save(equipamento));
    }

    private Equipamento buscarEntidade(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento nao encontrado"));
    }
}
