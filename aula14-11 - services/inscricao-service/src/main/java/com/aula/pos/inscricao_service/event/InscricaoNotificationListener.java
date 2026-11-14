package com.aula.pos.inscricao_service.event;

import com.aula.pos.inscricao_service.dto.NotificationMessage;
import com.aula.pos.inscricao_service.producer.NotificationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component
public class InscricaoNotificationListener {
    private static final Logger log = LoggerFactory.getLogger(InscricaoNotificationListener.class);
    private final NotificationProducer producer;

    public InscricaoNotificationListener(NotificationProducer producer) {
        this.producer = producer;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aposConfirmarInscricao(InscricaoConfirmadaEvent evento) {
        String mensagem = "Olá, " + evento.nomeUsuario() +
                "! Sua inscrição no evento '" + evento.nomeEvento() + "' foi confirmada.";

        if (evento.email() != null && !evento.email().isBlank()) {
            publicarComLog(evento.inscricaoId(), new NotificationMessage(
                    "EMAIL", evento.email(), "Inscrição confirmada", null, mensagem));
        } else {
            log.warn("Inscrição {} sem e-mail; notificação ignorada", evento.inscricaoId());
        }

        if (evento.telefone() != null && !evento.telefone().isBlank()) {
            publicarComLog(evento.inscricaoId(), new NotificationMessage(
                    "SMS", null, null, evento.telefone(), mensagem));
        } else {
            log.warn("Inscrição {} sem telefone; SMS ignorado", evento.inscricaoId());
        }
    }

    private void publicarComLog(UUID inscricaoId, NotificationMessage notificacao) {
        try {
            producer.publicar(notificacao);
            log.info("Publicação solicitada: {} da inscrição {}", notificacao.tipo(), inscricaoId);
        } catch (RuntimeException ex) {
            // O commit já ocorreu: nunca afirmar que a inscrição foi desfeita.
            log.error("Falha ao publicar {} da inscrição {}", notificacao.tipo(), inscricaoId, ex);
        }
    }
}

