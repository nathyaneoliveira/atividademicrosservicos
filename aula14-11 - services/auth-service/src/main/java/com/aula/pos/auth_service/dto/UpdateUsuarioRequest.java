package com.aula.pos.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateUsuarioRequest(
        @Size(min = 2, max = 150)
        @Pattern(regexp = ".*\\S.*", message = "não deve estar em branco") String nome,
        @Pattern(
                regexp = "^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
                message = "deve conter 11 dígitos"
        ) String cpf,
        @Pattern(
                regexp = "^(?=(?:\\D*\\d){10,15}\\D*$)[\\d+()\\-\\s]+$",
                message = "deve conter entre 10 e 15 dígitos"
        ) String telefone,
        @Email @Pattern(regexp = ".*\\S.*", message = "não deve estar em branco") String email,
        @Size(min = 8, max = 72) @Pattern(regexp = ".*\\S.*", message = "não deve estar em branco") String password
) {
}
