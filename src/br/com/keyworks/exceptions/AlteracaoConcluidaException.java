package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class AlteracaoConcluidaException extends Exception {
	@Override
	public String getMessage() {

		return "Alteração concluída.";
	}
}
