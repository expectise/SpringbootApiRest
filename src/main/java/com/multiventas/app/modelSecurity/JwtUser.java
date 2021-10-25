package com.multiventas.app.modelSecurity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUser {
	private String userName;
	private long id;
	private String role;
}
