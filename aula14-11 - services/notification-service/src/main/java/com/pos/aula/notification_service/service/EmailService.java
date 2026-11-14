package com.pos.aula.notification_service.service;

import com.pos.aula.notification_service.dto.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviar(EmailRequest email) {

        System.out.println("===== ENVIO DE E-MAIL =====");
        System.out.println("Destinatário: " + email.destinatario());
        System.out.println("Assunto: " + email.assunto());
        System.out.println("Mensagem: " + email.mensagem());
        System.out.println("E-mail enviado com sucesso (simulação)");
        System.out.println("==========================");
    }
}
