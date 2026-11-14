package com.aula.pos.msequipamento.dto;

import com.aula.pos.msequipamento.model.Condicao;
import jakarta.validation.constraints.NotNull;

public record CondicaoRequest(
        @NotNull Condicao condicao
) {
}
