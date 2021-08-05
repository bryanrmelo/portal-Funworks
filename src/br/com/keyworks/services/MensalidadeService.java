package br.com.keyworks.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

	public void salvarComprovante(Mensalidade mensalidade) {
		try {
			mensalidade.setComprovante(mensalidade.getComprovanteFile().getContents());
			mensalidadeRepo.atualizar(mensalidade);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}
