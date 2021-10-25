package com.multiventas.app;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class MultiVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiVentasApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		 ModelMapper modelMapper = new ModelMapper();
	        modelMapper.getConfiguration().setSkipNullEnabled(true);

	        return modelMapper;
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.ionos.mx");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername(Constants.usuarioEmail);
	    mailSender.setPassword(Constants.contraEmail);
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}

}
