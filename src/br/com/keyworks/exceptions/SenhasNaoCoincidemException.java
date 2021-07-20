package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SenhasNaoCoincidemException extends Exception {
	@Override
	public String getMessage() {
		return "Senhas não coincidem!";
	}
}
