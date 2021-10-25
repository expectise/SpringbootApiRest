package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaisesOutDTO  implements Serializable{
	private static final long serialVersionUID = 2677066648982685938L;

	@NotNull
	private Long idPais;
	
	@NotNull
	@Length(min=3, max=45)
	private String pais;
	
	@NotNull
	private BigDecimal valorEnvio;
	
	@NotNull
	private BigDecimal precioExcento;
	
	@NotNull
	private Long creado;
	
	@NotNull
	private Long actualizado;
	
}
