package com.multiventas.app.emailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.multiventas.app.Constants;


@Component
public class EmailService implements IEmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	private static final String from = Constants.from;
	
	
	public void enviaConfClave(String email, String clave) {
		try {
			String Titulo = "Tu Clave de confirmación";
			String Mensaje = "Tu Clave de confirmación es: " + clave;
	        
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom(from);
	        message.setTo(email); 
	        message.setSubject(Titulo); 
	        message.setText(Mensaje);
	        emailSender.send(message);
		}catch(MailSendException e) {
			System.out.println(e);
		}
		
	}
	
	
	public void enviaContraClave(String email, String clave) {
		
		String Titulo = "Tu Clave de confirmación para cambio de contraseña";
		String Mensaje = "Tu Clave de confirmación para cambio de contraseña es: " + clave;
        
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(from);
        message.setTo(email); 
        message.setSubject(Titulo); 
        message.setText(Mensaje);
        emailSender.send(message);
	
	}
	
	
	public void enviaContra(String email, String clave) {
		
		String Titulo = "Tu Contraseña cambió";
		String Mensaje = "Tu nueva contraseña es: " + clave;
        
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(from);
        message.setTo(email); 
        message.setSubject(Titulo); 
        message.setText(Mensaje);
        emailSender.send(message);
	
	}

}
