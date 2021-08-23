package br.com.keyworks.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.AlteracaoConcluidaException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.ConverterNomeUtil;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepo;

	// Editar perfil
	public void editar(Usuario usuario) throws AlteracaoConcluidaException {
		verificarQuantidadeEObservacao(usuario);
		usuarioRepo.alterar(usuario);
		throw new AlteracaoConcluidaException();

	}

	private void verificarQuantidadeEObservacao(Usuario usuario) {
		if (usuario.getDependentes() == null || usuario.getDependentes().equals(SimNaoEnum.NAO.getDescricao())) {
			usuario.setQtdDependentes(null);
		}
		if (usuario.getAnimais() == null || usuario.getAnimais().equals(SimNaoEnum.NAO.getDescricao())) {
			usuario.setObsAnimais(null);
			usuario.setQtdAnimais(null);
		}
		if (usuario.getOrientacaoAlimentar() == null || usuario.getOrientacaoAlimentar().equals(SimNaoEnum.NAO.getDescricao())) {
			usuario.setObsOrientacaoAlimentar(null);
		}
		if (usuario.getAlergias() == null || usuario.getAlergias().equals(SimNaoEnum.NAO.getDescricao())) {
			usuario.setObsAlergias(null);
		}
		if (usuario.getIntolerancias() == null || usuario.getIntolerancias().equals(SimNaoEnum.NAO.getDescricao())) {
			usuario.setObsIntolerancias(null);
		}
	}

	public Usuario getDadosExistentes(String login) {
		try {
			return usuarioRepo.buscarUsuario(login);
		} catch (UsuarioNaoEncontradoException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Gerenciamento de nomes
	public String gerenciarNomeParaView(String opcao, String nome) {
		try {
			if (testarExistenciaNomeCompleto(usuarioRepo.buscarUsuario(nome)) && opcao.equals("completo")) {
				return usuarioRepo.buscarUsuario(nome).getNome();
			} else {
				if (opcao.equals("parcial")) {
					return ConverterNomeUtil.converterPrimeiroNome(nome);
				} else
					if (opcao.equals("completo")) {
						return ConverterNomeUtil.converterPrimeiroNome(nome) + " " + ConverterNomeUtil.converterUltimoNome(nome);
					}
			}
		} catch (NullPointerException | UsuarioNaoEncontradoException e) {
		}
		return null;

	}

	public static boolean testarExistenciaNomeCompleto(Usuario usuario) {
		if (usuario.getNome() != null) {
			return true;
		} else {
			return false;
		}
	}

	public Usuario getUsuario(String nome) {
		try {
			return usuarioRepo.buscarUsuario(nome);
		} catch (UsuarioNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}

}
