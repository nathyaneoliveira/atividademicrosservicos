package com.aula.pos.inscricao_service.dto;

public record NotificationMessage(
        String tipo,
        String destinatario,
        String assunto,
        String telefone,
        String mensagem
) {}

