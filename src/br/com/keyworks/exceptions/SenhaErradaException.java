package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SenhaErradaException extends Exception {

	@Override
	public String getMessage() {
		return "Usuário ou senha estão errados!";
	}
}
