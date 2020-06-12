package com.christiandias.cursomc.services;

import com.christiandias.cursomc.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

}