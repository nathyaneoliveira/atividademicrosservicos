package com.aula.pos.msemprestimo.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmprestimoRequest(
        @NotNull UUID equipamentoId
) {
}
