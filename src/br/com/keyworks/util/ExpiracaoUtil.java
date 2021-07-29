package br.com.keyworks.util;

import java.util.Date;

public class ExpiracaoUtil {

	public static boolean ValidaExpiracao(Date data) {
		return (new Date().getTime() - data.getTime()) < 1800000;
	}
}
