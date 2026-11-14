package com.aula.pos.inscricao_service.dto;


import com.aula.pos.inscricao_service.enums.StatusInscricao;

import java.time.LocalDateTime;
import java.util.UUID;

public record InscricaoResponse(
        UUID id,
        UsuarioResponse usuario,
        EventoResponse evento,
        StatusInscricao statusInscricao,
        LocalDateTime dataHoraInscricao
) {
}
