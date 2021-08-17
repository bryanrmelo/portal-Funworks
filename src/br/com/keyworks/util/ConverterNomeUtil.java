package br.com.keyworks.util;

public class ConverterNomeUtil {

	public static String converterPrimeiroNome(String nome) throws NullPointerException {

		String[] res = nome.split("[.]", 0);

		String output = res[0].substring(0, 1).toUpperCase() + res[0].substring(1);

		return output;
	}

	public static String converterUltimoNome(String nome) throws NullPointerException {

		String[] res = nome.split("[.]", 0);

		String output = res[1].substring(0, 1).toUpperCase() + res[1].substring(1);

		return output;
	}

	public static String converterParaLogin(String nome) throws NullPointerException {

		String[] res = nome.split("[ ]", 0);

		String output = res[0].substring(0, 1).toLowerCase() + res[0].substring(1) + "." + res[1].substring(0, 1).toLowerCase() + res[1].substring(1);

		return output;
	}

}
