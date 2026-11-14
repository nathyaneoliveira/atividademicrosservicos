package com.aula.pos.inscricao_service.dto;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        UUID pessoaId,
        String nome,
        String cpf,
        String telefone,
        String email
) {
}
