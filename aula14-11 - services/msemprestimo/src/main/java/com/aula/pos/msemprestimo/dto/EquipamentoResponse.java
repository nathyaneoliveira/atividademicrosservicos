package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.model.Condicao;

import java.util.UUID;

public record EquipamentoResponse(UUID id, String nome, Condicao condicao, boolean reservado) {
}
