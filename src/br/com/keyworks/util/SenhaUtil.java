package br.com.keyworks.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.com.keyworks.exceptions.SenhaInvalidaException;

public class SenhaUtil {

	public static boolean validarSenha(String senha, String regex) throws SenhaInvalidaException {

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(senha);

		return m.matches();

	}

}
