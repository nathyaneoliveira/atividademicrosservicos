package com.aula.pos.msequipamento.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipamentoRequest(
        @NotBlank String nome
) {
}
