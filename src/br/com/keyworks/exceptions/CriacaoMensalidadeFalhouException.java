package br.com.keyworks.exceptions;

public class CriacaoMensalidadeFalhouException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Erro gerando mensalidades";
	}
}
