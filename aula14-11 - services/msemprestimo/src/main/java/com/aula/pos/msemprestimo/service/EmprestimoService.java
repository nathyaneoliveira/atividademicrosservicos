package com.aula.pos.msemprestimo.service;

import com.aula.pos.msemprestimo.client.EquipamentoGateway;
import com.aula.pos.msemprestimo.dto.CondicaoRequest;
import com.aula.pos.msemprestimo.dto.DevolucaoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.model.Emprestimo;
import com.aula.pos.msemprestimo.repository.EmprestimoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final EquipamentoGateway equipamentoGateway;

    public EmprestimoService(EmprestimoRepository repository, EquipamentoGateway equipamentoGateway) {
        this.repository = repository;
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional
    public EmprestimoResponse criar(EmprestimoRequest request) {
        equipamentoGateway.alterarReserva(request.equipamentoId(), true);
        return EmprestimoResponse.from(repository.save(new Emprestimo(request.equipamentoId())));
    }

    @Transactional(readOnly = true)
    public EmprestimoResponse buscar(UUID id) {
        return EmprestimoResponse.from(buscarEntidade(id));
    }

    @Transactional
    public EmprestimoResponse devolver(UUID id, DevolucaoRequest request) {
        var emprestimo = buscarEntidade(id);
        if (emprestimo.getStatus() == com.aula.pos.msemprestimo.model.StatusEmprestimo.DEVOLVIDO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Emprestimo ja devolvido");
        }

        equipamentoGateway.alterarCondicao(emprestimo.getEquipamentoId(), new CondicaoRequest(request.condicao()));
        equipamentoGateway.alterarReserva(emprestimo.getEquipamentoId(), false);
        emprestimo.devolver();
        return EmprestimoResponse.from(repository.save(emprestimo));
    }

    private Emprestimo buscarEntidade(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprestimo nao encontrado"));
    }
}
