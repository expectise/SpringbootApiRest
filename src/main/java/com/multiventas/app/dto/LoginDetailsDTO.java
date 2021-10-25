package com.multiventas.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDetailsDTO  implements Serializable{
	private static final long serialVersionUID = -4012414545942547898L;
	private String bearer;
	private String role;

}
