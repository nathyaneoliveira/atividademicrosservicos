package com.pos.aula.notification_service.service;

import com.pos.aula.notification_service.dto.EmailRequest;
import com.pos.aula.notification_service.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailService emailService;
    private final SmsService smsService;

    public NotificationService(
            EmailService emailService,
            SmsService smsService) {

        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void enviarEmail(EmailRequest request) {
        emailService.enviar(request);
    }

    public void enviarSms(SmsRequest request) {
        smsService.enviar(request);
    }
}
