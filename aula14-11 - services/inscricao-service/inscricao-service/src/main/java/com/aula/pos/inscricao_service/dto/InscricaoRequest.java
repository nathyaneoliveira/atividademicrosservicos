package com.aula.pos.inscricao_service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InscricaoRequest(
        @NotNull(message = "Usuario é Obrigatória")
        UUID usuarioId,
        @NotNull(message = "Evento é Obrigatório")
        UUID eventoId
) {
}
