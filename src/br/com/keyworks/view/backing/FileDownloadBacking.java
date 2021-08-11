package br.com.keyworks.view.backing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import br.com.keyworks.services.MensalidadeService;

@Named("fileDownload")
@RequestScoped
public class FileDownloadBacking {

	@Inject
	private MensalidadeService mensalidadeService;

	private StreamedContent comprovanteDownload;

	public FileDownloadBacking() {
		InputStream stream = new ByteArrayInputStream(mensalidadeService.getComprovante(0));
		comprovanteDownload = new DefaultStreamedContent(stream, "application/pdf", "comprovante.pdf");
	}

	public StreamedContent getComprovanteDownload() {
		return comprovanteDownload;
	}

}
