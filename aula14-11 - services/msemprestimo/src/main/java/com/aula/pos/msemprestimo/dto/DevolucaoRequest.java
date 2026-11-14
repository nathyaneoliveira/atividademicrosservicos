package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.model.Condicao;
import jakarta.validation.constraints.NotNull;

public record DevolucaoRequest(
        @NotNull Condicao condicao
) {
}
