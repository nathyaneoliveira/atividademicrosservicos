package com.aula.pos.auth_service.dto;

import com.aula.pos.auth_service.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisterRequest(
        @NotBlank @Size(min = 2, max = 150) String nome,
        @NotBlank @Pattern(
                regexp = "^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
                message = "deve conter 11 dígitos"
        ) String cpf,
        @NotBlank @Pattern(
                regexp = "^(?=(?:\\D*\\d){10,15}\\D*$)[\\d+()\\-\\s]+$",
                message = "deve conter entre 10 e 15 dígitos"
        ) String telefone,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 72) String password,
        @NotNull Role role
) {
}
