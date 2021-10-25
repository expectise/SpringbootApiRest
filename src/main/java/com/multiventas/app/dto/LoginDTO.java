package com.multiventas.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO  implements Serializable{
	private static final long serialVersionUID = 5431578747052052318L;
	private String email;
	private String contrasena;

}
