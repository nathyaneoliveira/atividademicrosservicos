package com.aula.pos.msequipamento.dto;

import com.aula.pos.msequipamento.model.Condicao;
import com.aula.pos.msequipamento.model.Equipamento;

import java.util.UUID;

public record EquipamentoResponse(
        UUID id,
        String nome,
        Condicao condicao,
        boolean reservado
) {
    public static EquipamentoResponse from(Equipamento equipamento) {
        return new EquipamentoResponse(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getCondicao(),
                equipamento.isReservado()
        );
    }
}
