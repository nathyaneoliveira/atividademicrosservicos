package com.pos.aula.notification_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.pos.aula.notification_service.config.RabbitMQConfig;
import com.pos.aula.notification_service.dto.EmailRequest;
import com.pos.aula.notification_service.dto.NotificationMessageRequest;
import com.pos.aula.notification_service.dto.SmsRequest;
import com.pos.aula.notification_service.service.NotificationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public NotificationConsumer(
            NotificationService notificationService,
            ObjectMapper objectMapper,
            Validator validator) {

        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @RabbitListener(
            queues = RabbitMQConfig.NOTIFICATION_QUEUE
    )
    public void consumir(byte[] body) {

        try {
            String json = new String(
                    body, StandardCharsets.UTF_8
            );

            NotificationMessageRequest notification =
                    objectMapper.readValue(
                            json,
                            NotificationMessageRequest.class
                    );

            if (notification.tipo() == null) {
                throw new IllegalArgumentException(
                        "Tipo da notificação é obrigatório"
                );
            }

            switch (notification.tipo()) {

                case "EMAIL" -> processarEmail(notification);

                case "SMS" -> processarSms(notification);

                default -> throw new IllegalArgumentException(
                        "Tipo de notificação inválido: "
                                + notification.tipo()
                );
            }

        } catch (JsonProcessingException |
                 IllegalArgumentException ex) {

            System.err.println(
                    "Mensagem inválida: " + ex.getMessage()
            );

            throw new AmqpRejectAndDontRequeueException(
                    "Não foi possível processar a mensagem",
                    ex
            );
        }
    }

    private void processarEmail(NotificationMessageRequest notification) {

        EmailRequest email = new EmailRequest(
                notification.destinatario(),
                notification.assunto(),
                notification.mensagem()
        );

        validar(email);
        notificationService.enviarEmail(email);
    }

    private void processarSms(NotificationMessageRequest notification) {

        SmsRequest sms = new SmsRequest(
                notification.telefone(),
                notification.mensagem()
        );

        validar(sms);
        notificationService.enviarSms(sms);
    }

    private <T> void validar(T request) {

        Set<ConstraintViolation<T>> erros =
                validator.validate(request);

        if (!erros.isEmpty()) {

            String mensagem = erros.iterator()
                    .next()
                    .getMessage();

            throw new IllegalArgumentException(mensagem);
        }
    }
}
