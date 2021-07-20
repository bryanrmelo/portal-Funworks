package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class AlteracaoFalhouException extends Exception {
	@Override
	public String getMessage() {
		return "Não existe uma requisição de troca de senha com esse usuário ou ela expirou.";
	}
}
