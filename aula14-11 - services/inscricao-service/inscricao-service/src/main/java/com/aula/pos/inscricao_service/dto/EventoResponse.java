package com.aula.pos.inscricao_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String nome,
        LocalDateTime dataHoraEvento,
        String local,
        String cidade,
        String estado,
        Integer qtde
) {

}
