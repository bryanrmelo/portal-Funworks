package br.com.keyworks.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.exceptions.ObservacaoInvalidaException;
import br.com.keyworks.exceptions.QuantidadeInvalidaException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.ConverterNomeUtil;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepo;

	// Editar perfil
	public void editar(String login, String nome, String email, String celular, String whatsapp, String nascimento, String estadoCivil,
					String admissao, String genero, String dependentes, Integer qtdDependentes, String animais, Integer qtdAnimais, String obsAnimais,
					String orientacaoAlimentar, String obsOrientacaoAlimentar, String alergias, Integer obsAlergias, String intolerancias,
					Integer obsIntolerancias, String preferencias, String dicas)
					throws UsuarioNaoEncontradoException, QuantidadeInvalidaException, ObservacaoInvalidaException {

		Usuario usuario = usuarioRepo.buscarUsuario(login);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setCelular(celular);
		usuario.setWhatsapp(whatsapp);
		usuario.setNascimento(nascimento);
		usuario.setEstadoCivil(estadoCivil);
		usuario.setAdmissao(admissao);
		usuario.setGenero(genero);
		usuario.setDependentes(dependentes);
		usuario.setQtdDependentes(qtdDependentes);
		usuario.setAnimais(animais);
		usuario.setQtdAnimais(qtdAnimais);
		usuario.setObsAnimais(obsAnimais);
		usuario.setOrientacaoAlimentar(orientacaoAlimentar);
		usuario.setObsOrientacaoAlimentar(obsOrientacaoAlimentar);
		usuario.setAlergias(alergias);
		usuario.setObsAlergias(obsAlergias);
		usuario.setIntolerancias(intolerancias);
		usuario.setObsIntolerancias(obsIntolerancias);
		usuario.setPreferencias(preferencias);
		usuario.setDicas(dicas);
		usuarioRepo.alterar(usuario);

		//
		// if (testarQtd(dependentes, qtdDependentes)) {
		// usuario.setDependentes(dependentes);
		// usuario.setQtdDependentes(0);
		// } else {
		// usuario.setDependentes("sim");
		// usuario.setQtdDependentes(qtdDependentes);
		// }
		//
		// if (testarQtd(animais, qtdAnimais) || testarObs(animais, obsAnimais)) {
		// usuario.setAnimais(animais);
		// usuario.setQtdAnimais(0);
		// usuario.setObsAnimais("");
		// } else {
		// usuario.setAnimais("sim");
		// usuario.setQtdAnimais(qtdAnimais);
		// usuario.setObsAnimais(obsAnimais);
		// }
	}

	public Usuario getDadosExistentes(String login) {
		try {
			return usuarioRepo.buscarUsuario(login);
		} catch (UsuarioNaoEncontradoException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unused")
	private boolean testarQtd(String valor, Integer quantidade) throws QuantidadeInvalidaException {
		if (valor.equals("sim") && quantidade > 0) {
			return true;
		} else {
			throw new QuantidadeInvalidaException();
		}

	}

	@SuppressWarnings("unused")
	private boolean testarObs(String valor, String obs) throws ObservacaoInvalidaException {
		if (valor.equals("sim") && obs.length() > 0) {
			return true;
		} else {
			throw new ObservacaoInvalidaException();
		}
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
			e.printStackTrace();
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

}
