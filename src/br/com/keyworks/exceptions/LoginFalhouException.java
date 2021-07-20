package br.com.keyworks.exceptions;

@SuppressWarnings("serial")
public class LoginFalhouException extends Exception {
	@Override
	public String getMessage() {
		return "Por favor, insira um usuário e senha corretos para uma conta de equipe que atenda aos requisitos de segurança. Não insira caracteres especiais como $%*&@ ou letras maiúsculas.";
	}
}
