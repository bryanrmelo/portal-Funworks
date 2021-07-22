package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class ObservacaoInvalidaException extends Exception {
	@Override
	public String getMessage() {
		return "Observação precisa ser preenchida!";
	}
}
