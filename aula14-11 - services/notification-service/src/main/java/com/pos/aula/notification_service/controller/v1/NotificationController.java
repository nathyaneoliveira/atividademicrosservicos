package com.pos.aula.notification_service.controller.v1;

import com.pos.aula.notification_service.dto.EmailRequest;
import com.pos.aula.notification_service.dto.SmsRequest;
import com.pos.aula.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificacoes")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> enviarEmail(
            @Valid @RequestBody EmailRequest request) {

        notificationService.enviarEmail(request);

        return ResponseEntity.ok(
                "E-mail processado com sucesso"
        );
    }

    @PostMapping("/sms")
    public ResponseEntity<String> enviarSms(
            @Valid @RequestBody SmsRequest request) {

        notificationService.enviarSms(request);

        return ResponseEntity.ok(
                "SMS processado com sucesso"
        );
    }
}