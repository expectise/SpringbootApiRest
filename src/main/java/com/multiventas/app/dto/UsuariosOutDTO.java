package com.multiventas.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosOutDTO  implements Serializable{
	private static final long serialVersionUID = -1698769949367494607L;

	private Long idUsuarios;

	private String nickname;
	
	private String nombre;
	
	private String appaterno;
	
	private String apmaterno;
	
	private String telefono;
	
	private String email;
	
	private Boolean activo;
	
	private Boolean confirmado;
	
	private Long fecConfirma;

	private Long creado;

	private Long actualizado;
	
	private BigDecimal latitud;
	private BigDecimal longitud;
}
