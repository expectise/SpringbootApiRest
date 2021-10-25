package com.multiventas.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbrirPujaDTO implements Serializable{
	private static final long serialVersionUID = -6133751615015839740L;

	@NotNull
	private Long fecPriAbo;
	
	@NotNull
	@Length(min=3, max=45)
	private String titulo;
}
