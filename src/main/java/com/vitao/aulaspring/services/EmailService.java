package com.vitao.aulaspring.services;

import javax.mail.internet.MimeMessage;

import com.vitao.aulaspring.domain.Cliente;
import com.vitao.aulaspring.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String email);
}
