package com.pos.aula.notification_service.dto;

public record NotificationMessageRequest(
        String tipo,
        String destinatario,
        String assunto,
        String telefone,
        String mensagem
) {}