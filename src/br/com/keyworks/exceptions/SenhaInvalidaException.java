package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SenhaInvalidaException extends Exception {
	@Override
	public String getMessage() {
		return "A senha precisa ser composta de no mínimo 1 letra minúscula e 1 número e não pode possuir letras maiúsculas ou caracteres especiais!";
	}
}
