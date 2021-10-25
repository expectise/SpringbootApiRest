package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductoNewDTO  implements Serializable{
	private static final long serialVersionUID = 758346520627011073L;

	@NotNull
	@Length(min=5, max=50)
	private String titulo;
	
	@NotNull
	@Length(min=5, max=500)
	private String descripcion;
	
	@NotNull
	private BigDecimal precio;

	
	@NotNull
	@Pattern(regexp = "(^[a-zA-Z0-9]{6,6}$)")
	private String codigoProducto;
}
