package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentasOutDTO implements Serializable{
	private static final long serialVersionUID = 7834286406948884591L;

	private Long idVentas;
	
	private Long idUsuarios;
	
	private Long idPuja;

	private String folio;

	private BigDecimal costoEnvio;
	
	private BigDecimal precioExcento;
	
	private Byte enviado;
	
	private String guia;
	
	private String empresa;
	
	private Long creado;
	
	private Long actualizado;
	
	private List<VentadetalladaOutDTO> ventadetalladaOutDTO;
}
