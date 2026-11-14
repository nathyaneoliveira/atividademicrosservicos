package com.aula.pos.inscricao_service.producer;

import com.aula.pos.inscricao_service.config.RabbitMQConfig;
import com.aula.pos.inscricao_service.dto.NotificationMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
public class NotificationProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public NotificationProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publicar(NotificationMessage notificacao) {
        try {
            byte[] json = objectMapper.writeValueAsBytes(notificacao);
            Message message = MessageBuilder.withBody(json)
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            // A exchange padrão entrega à fila cujo nome corresponde à routing key.
            rabbitTemplate.send("", RabbitMQConfig.NOTIFICATION_QUEUE, message);
        } catch (JacksonException ex) {
            throw new IllegalStateException("Falha ao serializar a notificação", ex);
        }
    }
}

