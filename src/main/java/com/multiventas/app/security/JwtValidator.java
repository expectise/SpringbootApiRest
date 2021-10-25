package com.multiventas.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multiventas.app.Constants;
import com.multiventas.app.dao.IUsuariosDAO;
import com.multiventas.app.model.Usuarios;
import com.multiventas.app.modelSecurity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtValidator {
	
	@Autowired
	IUsuariosDAO usuariosDAO;

	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		
		try {
			Claims body = Jwts.parser()
					.setSigningKey(Constants.YOUR_SECRET)
					.parseClaimsJws(token)
					.getBody();
			
				Usuarios usuario = usuariosDAO.findByEmailAndActivo(body.getSubject(), true).orElse(null);
				
				if (usuario != null) {
					String role;
					if (usuario.getEsAdmin()) {
						role = "Admin";
					}else if(usuario.getConfirmado()) {
						role = "Usuario";
					}else {
						role = "Preconfirmado";
					}
					
					if (body.get(Constants.ROLE).toString().equals(role)) {
						jwtUser = new JwtUser();
						jwtUser.setUserName(body.getSubject());
						jwtUser.setId(Long.parseLong((String) body.get(Constants.USER_ID)));
						jwtUser.setRole((String) body.get(Constants.ROLE));	
					}
					
				}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return jwtUser;
	}
	
}