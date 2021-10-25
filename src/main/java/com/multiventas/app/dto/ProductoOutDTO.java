package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoOutDTO  implements Serializable{
	private static final long serialVersionUID = -2973376513231641193L;

	@NotNull
	private Long idProducto;
	
	@NotNull
	@Length(min=5, max=50)
	private String titulo;
	
	@NotNull
	@Length(min=5, max=500)
	private String descripcion;
	
	@NotNull
	private Long creado;
	
	@NotNull
	private Long actualizado;
	
	@NotNull
	private BigDecimal precio;

	
	@NotNull
	@Length(min=6, max=6)
	private String codigoProducto;
}
