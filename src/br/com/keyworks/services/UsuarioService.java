package br.com.keyworks.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;
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
	public void editar(Usuario usuario) throws UsuarioNaoEncontradoException, QuantidadeInvalidaException, ObservacaoInvalidaException {
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

	private byte[] converterImagem(UploadedFile imagem) {
		byte[] contents = imagem.getContents();
		return contents;

		// String fileName = imagem.getFileName();
		// String contentType = imagem.getContentType();

		// try {
		//
		// UploadedFile filePath = imagem;
		//
		// // file to bytes[]
		// byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		//
		// bytes[] to file
		// Path path = Paths.get(imagem);
		// Files.write(path, bytes);
		//
		// System.out.println("Done");
		//
		// } catch (IOException e) {
		// e.printStackTrace();
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
