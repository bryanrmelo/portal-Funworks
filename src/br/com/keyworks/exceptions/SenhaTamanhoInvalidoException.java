package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class SenhaTamanhoInvalidoException extends Exception {
	@Override
	public String getMessage() {
		return "Senha precisa ser maior que 8 digítos e menor que 60 digítos!";
	}
}
