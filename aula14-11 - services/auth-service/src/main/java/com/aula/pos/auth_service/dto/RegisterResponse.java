package com.aula.pos.auth_service.dto;

import com.aula.pos.auth_service.enums.Role;
import com.aula.pos.auth_service.model.Usuario;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String nome,
        String cpf,
        String telefone,
        String email,
        Role role
) {
    public static RegisterResponse from(Usuario usuario) {
        return new RegisterResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
