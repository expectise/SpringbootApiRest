package com.multiventas.app.services;

import java.util.List;

import com.multiventas.app.dto.LoginDTO;
import com.multiventas.app.dto.LoginDetailsDTO;
import com.multiventas.app.dto.UsuariosDTO;
import com.multiventas.app.dto.UsuariosOutDTO;

public interface IUsuariosService {
	public void nuevoUsuario(UsuariosDTO usuariosDTO);
	
	public boolean usuarioExiste(String email);
	
	public LoginDetailsDTO login(LoginDTO login);
	
	public boolean confirmaEmail(String email, String clave);
	
	public boolean resetearContra(String email, String clave);
	
	public boolean recordarContra(String email);
	
	public String getUserRole(Long id);
	
	public List<UsuariosOutDTO> getUsuarios();
}
