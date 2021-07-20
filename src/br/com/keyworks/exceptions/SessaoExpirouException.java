package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SessaoExpirouException extends Exception {

	@Override
	public String getMessage() {
		return "Sessão expirou!";
	}
}
