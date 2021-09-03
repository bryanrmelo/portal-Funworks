package br.com.keyworks.exceptions;

public class MensalidadeExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Já existe uma mensalidade registrada para esse mês";
	}
}
