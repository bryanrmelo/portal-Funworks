package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SenhaInvalidaException extends Exception {
	@Override
	public String getMessage() {
		return "Senha precisa ser composta de no mínimo 1 letra minuscula e 1 número!";
	}
}
