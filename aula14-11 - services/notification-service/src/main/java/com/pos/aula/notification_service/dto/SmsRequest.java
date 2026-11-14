package com.pos.aula.notification_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SmsRequest(
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "^\\+?[1-9]\\d{7,14}$",
                message = "Telefone inválido"
        )
        String telefone,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem
) {}
