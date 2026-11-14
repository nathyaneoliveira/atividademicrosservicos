package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.model.Emprestimo;
import com.aula.pos.msemprestimo.model.StatusEmprestimo;

import java.util.UUID;

public record EmprestimoResponse(
        UUID id,
        UUID equipamentoId,
        StatusEmprestimo status
) {
    public static EmprestimoResponse from(Emprestimo emprestimo) {
        return new EmprestimoResponse(emprestimo.getId(), emprestimo.getEquipamentoId(), emprestimo.getStatus());
    }
}
