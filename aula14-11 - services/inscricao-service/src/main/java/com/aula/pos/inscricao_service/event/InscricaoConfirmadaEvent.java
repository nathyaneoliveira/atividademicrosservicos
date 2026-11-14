package com.aula.pos.inscricao_service.event;

import java.util.UUID;

public record InscricaoConfirmadaEvent(
        UUID inscricaoId,
        String email,
        String telefone,
        String nomeUsuario,
        String nomeEvento
) {}
