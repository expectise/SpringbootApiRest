package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuariosDTO implements Serializable{
	private static final long serialVersionUID = -2157155584438625212L;
	
	@NotNull
	@Length(min=5, max=50)
	private String nickname;
	@Length(min=5, max=30)
	@NotNull
	private String contrasena;
	@Length(min=3, max=50)
	@NotNull
	private String nombre;
	@Length(min=3, max=50)
	@NotNull
	private String appaterno;
	@Length(min=3, max=50)
	@NotNull
	private String apmaterno;
	@Length(min=10, max=14)
	@NotNull
	private String telefono;
	@NotNull
	@Email
	private String email;
	
	private BigDecimal latitud;
	private BigDecimal longitud;
	
	@NotNull
	private String captchaResponse;
	
}