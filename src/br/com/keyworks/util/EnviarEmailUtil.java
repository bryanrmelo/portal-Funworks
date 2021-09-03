package br.com.keyworks.util;

import java.time.Month;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EnviarEmailUtil {

	private static String senderAddress;

	private static String senderName;

	public static void send(String destino, String hash) throws EmailException {
		try {
			final HtmlEmail email = new HtmlEmail();
			configureMail(email, destino);
			String texto = "http://localhost:8080/portal/public/novaSenha.xhtml?login=" + hash;

			String nome = ConverterNomeUtil.converterPrimeiroNome(destino) + " " + ConverterNomeUtil.converterUltimoNome(destino);

			setMailVoContent(texto, email, nome);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void send(String destino, Month mes) throws EmailException {
		try {
			final HtmlEmail email = new HtmlEmail();
			configureMail(email, destino);

			String nome = ConverterNomeUtil.converterPrimeiroNome(destino) + " " + ConverterNomeUtil.converterUltimoNome(destino);

			setMailVoContent(email, nome, mes);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private static void configureMail(final Email email, String destino) throws EmailException {

		email.setHostName("192.168.1.213");
		email.setSmtpPort(Integer.valueOf("25"));
		email.setCharset("utf-8");
		senderAddress = "keyworks@desenvolvimento.com.br";
		senderName = "keyworks@desenvolvimento.com.br";
		email.setFrom(senderAddress, senderName);
		email.setSubject("Recuperação de Senha - Funworks");
		email.addTo(destino + "@keyworks.com.br");
	}

	private static void setMailVoContent(final String texto, final HtmlEmail email, String nome) throws EmailException {

		StringBuilder builder = new StringBuilder();
		builder.append("<br><br>");
		builder.append("<div style=\"float: left; width:100%; \"> ");
		builder.append("    <div style=\"float: left;width:300px;\">");
		builder.append("		<h2 style=\"text-align: center;\">Olá " + nome + "!</h2>");
		builder.append("		<p>Você recentemente pediu a redefinição da sua senha no Portal da Funworks. Clique no link abaixo para prosseguir.");
		builder.append("		<div>");
		builder.append("			<a href=\"" + texto + "\">Link</a>");
		builder.append("		</div>");
		builder.append("		<p>O link vai continuar válido pelos próximos 15 minutos.</p>");
		builder.append("		<p>Se você não pediu uma redefinição de senha, ignore este email.</p>");
		builder.append("		<h4 style=\"margin: 0; margin-top: 30px;\">Att,</h4>");
		builder.append("		<h4 style=\"margin: 0;\">Funworks &copy</h4>");
		builder.append("    </div>");
		builder.append("</div>");
		email.setHtmlMsg("<html>" + builder.toString() + "</html>");

	}

	private static void setMailVoContent(final HtmlEmail email, String nome, Month mes) throws EmailException {

		StringBuilder builder = new StringBuilder();
		builder.append("<br><br>");
		builder.append("<div style=\"float: left; width:100%; \"> ");
		builder.append("    <div style=\"float: left;width:300px;\">");
		builder.append("		<h2 style=\"text-align: center;\">Olá " + nome + "!</h2>");
		builder.append("		<p>O seu pagamento do mês de " + mes + " foi realizado.");
		builder.append("		<h4 style=\"margin: 0; margin-top: 30px;\">Att,</h4>");
		builder.append("		<h4 style=\"margin: 0;\">Funworks &copy</h4>");
		builder.append("    </div>");
		builder.append("</div>");
		email.setHtmlMsg("<html>" + builder.toString() + "</html>");

	}
}