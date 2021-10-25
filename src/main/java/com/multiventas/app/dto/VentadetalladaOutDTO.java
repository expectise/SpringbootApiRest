package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VentadetalladaOutDTO implements Serializable{
	private static final long serialVersionUID = -635543089291047041L;

	private Long idVentaDetallada;
	
	private Long idVentas;

	private BigDecimal precio;
	
	private String titulo;

	private String descripcion;
	
	private String codigoProducto;

	private Long creado;

	private Long actualizado;
}
