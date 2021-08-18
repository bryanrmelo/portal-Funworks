package br.com.keyworks.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.MensalidadeRepository;
import br.com.keyworks.repository.UsuarioRepository;

@Stateless
public class MensalidadeService {

	@Inject
	private UsuarioRepository usuarioRepo;

	@Inject
	private MensalidadeRepository mensalidadeRepo;

	public List<Mensalidade> getDadosExistentes(String nome) {
		try {
			Usuario usuario = usuarioRepo.buscarUsuario(nome);
			return mensalidadeRepo.buscarMensalidadesPorId(usuario.getId());
		} catch (UsuarioNaoEncontradoException e) {

		}
		return null;
	}

	public List<Mensalidade> getAllDadosExistentes() {
		return mensalidadeRepo.buscarMensalidades();
	}

	public String formatarData(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(data);
	}

	public String formatarValor(Double valor) {
		DecimalFormat format = new DecimalFormat("#");
		return format.format(valor);
	}

	public void salvarObservacao(Mensalidade mensalidade) {
		mensalidadeRepo.atualizar(mensalidade);
	}

	public Mensalidade salvarComprovante(Mensalidade mensalidade, UploadedFile comprovante) {
		try {
			mensalidade.setNomeComprovante(new String(comprovante.getFileName().getBytes(Charset.defaultCharset()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

		}
		mensalidade.setComprovante(comprovante.getContents());
		return mensalidadeRepo.atualizar(mensalidade);
	}

	public int getQuantidadeUsuarios() {
		return usuarioRepo.getQuantidadeUsuarios();
	}

	public byte[] getComprovante(Integer id) {
		return mensalidadeRepo.buscarMensalidade(id).getComprovante();
	}

	public String getNomeComprovante(Integer id) {
		return mensalidadeRepo.buscarMensalidade(id).getNomeComprovante();
	}

	public List<Mensalidade> getAllDadosExistentesComFiltros(Map<String, Object> filtrosSelecionados) {
		return mensalidadeRepo.buscarMensalidadesComFiltro(filtrosSelecionados);
	}

}
