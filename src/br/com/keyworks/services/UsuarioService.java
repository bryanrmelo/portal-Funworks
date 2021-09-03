package br.com.keyworks.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.enumeracoes.ContaEnum;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.AlteracaoConcluidaException;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.ConverterNomeUtil;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.util.SenhaUtil;
import br.com.keyworks.util.StringToMD5Converter;

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

	public void registrar(Usuario usuario) throws SenhaInvalidaException, SenhaTamanhoInvalidoException, UsuarioNaoEncontradoException {
		if (validar(usuario.getSenha()))
			try {
				ConverterNomeUtil.converterPrimeiroNome(usuario.getLogin());
				usuario.setSenha(StringToMD5Converter.convertStringToMd5(usuario.getSenha()));
				if (usuario.getTipo().equals(ContaEnum.ADMINISTRADOR.getDescricao())) {
					usuario.setTipo(ContaEnum.ADMINISTRADOR.getId());
				} else {
					usuario.setTipo(ContaEnum.PADRAO.getId());
				}

				usuarioRepo.registrar(usuario);

			} catch (NullPointerException e) {
				throw new UsuarioNaoEncontradoException();
			} catch (Exception e) {
				FacesMessageUtils.addErrorMessage("Erro");
			}
	}

	public boolean validar(String senha) throws SenhaInvalidaException, SenhaTamanhoInvalidoException {
		if (!SenhaUtil.validarSenha(senha, "^.{8,60}$")) {
			throw new SenhaTamanhoInvalidoException();
		}
		if (!SenhaUtil.validarSenha(senha, "^(?=.*?[a-z])(?=.*?[0-9])(?!.*?[A-Z])(?!.*[!@#$%^&*()/_+={};':\"|,.<>?]).{8,}$")) {
			throw new SenhaInvalidaException();
		}
		return true;

	}

}
