package com.tinpet.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServicio {

	@Autowired
	private JavaMailSender mandaCorreo;
	
	@Async //lo ejecuta aparte del hilo de ejecucion principal
	public void Enviar(String cuerpo, String titulo, String mail) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(mail);
		mensaje.setFrom("noreply@tinpet.com");
		mensaje.setSubject(titulo);
		mensaje.setText(cuerpo);
		
		mandaCorreo.send(mensaje);
	}
}
