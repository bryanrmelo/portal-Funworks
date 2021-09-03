package br.com.keyworks.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.exceptions.MensalidadeExistenteException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.MensalidadeRepository;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class MensalidadeService {

	@Inject
	private UsuarioRepository usuarioRepo;

	@Inject
	private MensalidadeRepository mensalidadeRepo;

	public List<Mensalidade> getDadosExistentes(String nome) {
		try {
			Usuario usuario = usuarioRepo.buscarUsuario(nome);
			return mensalidadeRepo.buscarMensalidadesPorIdUsuario(usuario.getId());
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

	public Long getQuantidadeUsuarios() {
		return usuarioRepo.getQuantidadeUsuarios();
	}

	public byte[] getComprovante(Integer id) {
		return mensalidadeRepo.buscarMensalidadePorId(id).getComprovante();
	}

	public String getNomeComprovante(Integer id) {
		return mensalidadeRepo.buscarMensalidadePorId(id).getNomeComprovante();
	}

	public PagedResult<Mensalidade> buscarMensalidades(GridLazyLoaderDTO gridLazyLoaderDTO) {
		return mensalidadeRepo.buscarMensalidades(gridLazyLoaderDTO);
	}

	public Mensalidade buscarMensalidadesPorId(String id) {
		return mensalidadeRepo.buscarMensalidadePorId(Integer.parseInt(id));
	}

	public Mensalidade buscarMensalidadesPorId(Integer id) {
		return mensalidadeRepo.buscarMensalidadePorId(id);

	}

	public void atualizarMensalidade(Mensalidade mensalidade) {
		mensalidadeRepo.atualizar(mensalidade);

	}

	public Mensalidade buscarMensalidade(Mensalidade mensalidade) {
		return mensalidadeRepo.buscarMensalidadePorId(mensalidade.getId());
	}

	public void criarNovaMensalidade(List<Mensalidade> lista, Date data) throws MensalidadeExistenteException {
		mensalidadeRepo.criarNovaMensalidade(lista, data);

	}

	public void criarMensalidadesParaUsuarioNovo(Usuario usuario) {
		mensalidadeRepo.criarMensalidadesParaUsuarioNovo(usuario);

	}

}
