package com.multiventas.app.emailer;

public interface IEmailService {
	
	public void enviaConfClave(String email, String clave);
	
	public void enviaContraClave(String email, String clave);
	
	public void enviaContra(String email, String clave);

}
