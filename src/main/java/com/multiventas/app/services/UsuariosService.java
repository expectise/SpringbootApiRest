package com.multiventas.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiventas.app.dao.IUsuariosDAO;
import com.multiventas.app.dto.LoginDTO;
import com.multiventas.app.dto.LoginDetailsDTO;
import com.multiventas.app.dto.UsuariosDTO;
import com.multiventas.app.dto.UsuariosOutDTO;
import com.multiventas.app.emailer.IEmailService;
import com.multiventas.app.model.Usuarios;
import com.multiventas.app.modelSecurity.JwtUser;
import com.multiventas.app.security.JwtGenerator;
import com.multiventas.app.utils.Converters;
import com.multiventas.app.utils.RandomChars;

@Service
public class UsuariosService implements IUsuariosService{
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IEmailService eMailService;
	
	private JwtGenerator jwtGenerator;
	
	public UsuariosService(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@Override
	public void nuevoUsuario(UsuariosDTO usuariosDTO) {
		
		String ip = httpServletRequest.getRemoteAddr();
		
		Usuarios usuarios = modelMapper.map(usuariosDTO, Usuarios.class);
		
		
		
		usuarios.setIp(ip);
		
		
		
		usuariosDAO.save(usuarios);
		
	}

	@Override
	public boolean usuarioExiste(String email) {
		Usuarios usuario = usuariosDAO.findByEmail(email).orElse(null);
		return usuario == null ? false : true;
	}
	
	public LoginDetailsDTO login(LoginDTO login) {
		
		JwtUser jwtUser = existUser(login);
		
		if (jwtUser != null) {
		String bearer = jwtGenerator.generate(jwtUser);
		
		if (jwtUser.getRole().equals("Preconfirmado")) {
			Usuarios usuarios = usuariosDAO.findByEmailAndActivo(jwtUser.getUserName(), true).orElse(null);
			//String confirmacion = ""+((int)(Math.random()*9000)+1000);
			String confirmacion = RandomChars.generateRandomChars("1234567890", 4);
			
			usuarios.setConfirmacion(confirmacion);
			usuarios.setFecConMsg(new Date());
			
			usuariosDAO.save(usuarios);
			
			eMailService.enviaConfClave(usuarios.getEmail(), confirmacion);
		}
			
		LoginDetailsDTO loginDetails = new LoginDetailsDTO();
		loginDetails.setBearer(bearer);
		loginDetails.setRole(jwtUser.getRole());
		
		return loginDetails;
		} else {
			return null;
		}
		
	}
	
	private JwtUser existUser(LoginDTO login) {
		
		Usuarios usuario = usuariosDAO.findByEmailAndContrasenaAndActivo(login.getEmail(), login.getContrasena(), true).orElse(null);
		
		
		if (usuario != null) {
		JwtUser jwtUser = new JwtUser();
		
		if (usuario.getEsAdmin()) {
			jwtUser.setRole("Admin");
		}else if(usuario.getConfirmado()) {
			jwtUser.setRole("Usuario");
		}else {
			jwtUser.setRole("Preconfirmado");
		}
	
			jwtUser.setUserName(usuario.getEmail());
			jwtUser.setId(usuario.getIdUsuarios());
			return jwtUser;
			
		}else {
			return null;
		}
		
	}

	@Override
	public boolean confirmaEmail(String email, String clave) {
		Usuarios usuarios = usuariosDAO.findByEmail(email).orElse(null);
		
		if (usuarios != null) {
			Date fecMsg = usuarios.getFecConMsg();
			int dif = (int)(new Date().getTime() - fecMsg.getTime())  / (1000 * 60);

			if (dif > 0) {
				return false;
			}else {
				if (usuarios.getConfirmacion().equals(clave)) {
					usuarios.setConfirmado(true);
					usuarios.setFecConfirma(new Date());
					usuariosDAO.save(usuarios);
					return true;
				}else {
					return false;
				}
			}
			
		}else {
			return false;
		}
	}
	
	public boolean recordarContra(String email) {
		Usuarios usuarios = usuariosDAO.findByEmailAndActivo(email, true).orElse(null);

		
		if (usuarios != null) {
			String contra = RandomChars.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",6);
						
			eMailService.enviaContraClave(usuarios.getEmail(), contra);
				    	
			usuarios.setFecContraMsg(new Date());
			usuarios.setRecordarContra(contra);
			usuariosDAO.save(usuarios);
		
		return true;
		
		}else {
			
			return false;
			
		}
	
	}
	
	public boolean resetearContra(String email, String clave) {
		Usuarios usuarios = usuariosDAO.findByEmailAndActivo(email, true).orElse(null);
		
		if (usuarios != null) {
			Date fecMsg = usuarios.getFecContraMsg();
			int dif = (int)(new Date().getTime() - fecMsg.getTime())  / (1000 * 60);

			if (dif > 0) {
				return false;
			}else {
				
				
			    if (usuarios.getRecordarContra().equals(clave)) {
					String contra = RandomChars.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",8);
					
					eMailService.enviaContra(usuarios.getEmail(), contra);
			    	
			    	usuarios.setFecContrafirma(new Date());
					usuarios.setContrasena(contra);
					usuariosDAO.save(usuarios);
					return true;
				}else {
					return false;
				}
			}
			
		}else {
			return false;
		}
	}
	
	

	@Override
	public String getUserRole(Long id) {
		
		Usuarios usuario = usuariosDAO.findByIdUsuariosAndActivo(id, true).orElse(null);

		String role = "";
		
		if (usuario != null) {
		
			if (usuario.getEsAdmin()) {
				role = "Admin";
			}else if(usuario.getConfirmado()) {
				role = "Usuario";
			}else {
				role = "Preconfirmado";
			}
		
		}
		
		return role;
	}

	@Override
	public List<UsuariosOutDTO> getUsuarios() {
		List<Usuarios> usuarios = usuariosDAO.getUsuarios();
		
		if (usuarios != null) {
			List<UsuariosOutDTO> lista = new ArrayList<UsuariosOutDTO>();
			
			for (Usuarios usuario : usuarios) {
				UsuariosOutDTO out = modelMapper.map(usuario, UsuariosOutDTO.class);
				
				out.setActualizado(Converters.dateToTimestamp(usuario.getActualizado()));
				out.setCreado(Converters.dateToTimestamp(usuario.getCreado()));
				out.setFecConfirma(Converters.dateToTimestamp(usuario.getFecConfirma()));
				
				lista.add(out);
			}
			
			return lista;
		}
		
		return null;
	}
	
}
