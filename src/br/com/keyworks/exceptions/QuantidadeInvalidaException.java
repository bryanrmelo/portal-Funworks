package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class QuantidadeInvalidaException extends Exception {
	@Override
	public String getMessage() {
		return "Quantidade precisa ser preenchida!";
	}
}
