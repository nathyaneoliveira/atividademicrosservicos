package com.pos.aula.notification_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @NotBlank(message = "O destinatário é obrigatório")
        @Email(message = "E-mail inválido")
        String destinatario,

        @NotBlank(message = "O assunto é obrigatório")
        String assunto,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem
) {}
