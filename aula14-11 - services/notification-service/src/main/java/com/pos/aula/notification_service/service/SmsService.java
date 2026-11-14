package com.pos.aula.notification_service.service;

import com.pos.aula.notification_service.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void enviar(SmsRequest sms) {

        System.out.println("===== ENVIO DE SMS =====");
        System.out.println("Telefone: " + sms.telefone());
        System.out.println("Mensagem: " + sms.mensagem());
        System.out.println("SMS enviado com sucesso (simulação)");
        System.out.println("========================");
    }
}
