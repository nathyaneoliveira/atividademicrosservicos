package com.aula.pos.auth_service.dto;

import com.aula.pos.auth_service.enums.Role;
import com.aula.pos.auth_service.model.Usuario;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String cpf,
        String telefone,
        String email,
        Role role
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
