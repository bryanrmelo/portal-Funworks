package br.com.keyworks.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.ConverterNomeUtil;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepo;

	// Editar perfil
	public void editar(Usuario usuario) {
		usuarioRepo.alterar(usuario);

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
			System.out.println("Usuário não encontrado ou null");
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
