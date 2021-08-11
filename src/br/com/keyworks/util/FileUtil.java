package br.com.keyworks.util;

import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {

	public static void downloadFile(String nomeArquivo, byte[] arquivoBinario) {

		FacesContext faces = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();

		response.addHeader("Content-Disposition:", "inline; filename=" + nomeArquivo + "\"");
		response.setContentType("application/pdf");

		response.setContentLength(arquivoBinario.length);

		try (OutputStream out = response.getOutputStream()) {
			out.write(arquivoBinario);
			out.flush();
		} catch (IOException e) {
			System.out.println("Error in downloadFile: " + e.getMessage());
			e.printStackTrace();
		}

		faces.responseComplete();
	}
}
