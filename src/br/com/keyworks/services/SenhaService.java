package br.com.keyworks.services;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import org.apache.commons.mail.EmailException;
import br.com.keyworks.exceptions.AlteracaoConcluidaException;
import br.com.keyworks.exceptions.AlteracaoFalhouException;
import br.com.keyworks.exceptions.NaoValidoException;
import br.com.keyworks.exceptions.SenhaErradaException;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.exceptions.SenhasNaoCoincidemException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.HistoricoSenha;
import br.com.keyworks.model.entities.administracao.RecuperacaoSenha;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.HistoricoRepository;
import br.com.keyworks.repository.RecuperacaoSenhaRepository;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.EnviarEmailUtil;
import br.com.keyworks.util.RandomStringGenerator;
import br.com.keyworks.util.SenhaUtil;
import br.com.keyworks.util.StringToMD5Converter;

@Stateless
public class SenhaService {

	@Inject
	private UsuarioRepository usuarioRepo;

	@Inject
	private RecuperacaoSenhaRepository recuperacaoSenhaRepo;

	@Inject
	private HistoricoRepository historicoRepo;

	public void recuperar(String login)
					throws EmailException, UsuarioNaoEncontradoException, NullPointerException, PersistenceException, NaoValidoException {
		if (usuarioRepo.buscarUsuario(login) != null) {

			// String hashLogin = StringToMD5Converter.convertStringToMd5(login);
			String hashLogin = StringToMD5Converter.convertStringToMd5(RandomStringGenerator.getAlphaNumericString(8));

			// Testa se ainda não expirou e atualiza
			if (recuperacaoSenhaRepo.validarRecuperacaoSenhaPorLogin(login)) {

				RecuperacaoSenha rpSalvo = recuperacaoSenhaRepo.buscarPorLogin(login);
				RecuperacaoSenha rpSenha = new RecuperacaoSenha(login, hashLogin);
				recuperacaoSenhaRepo.removerRecuperacaoSenha(rpSalvo);
				recuperacaoSenhaRepo.salvarRecuperacaoSenha(rpSenha);

				EnviarEmailUtil.send(rpSenha.getLogin(), rpSenha.getHash());

				// Testa se existe e cria uma nova recuperação e salva
			} else
				if (recuperacaoSenhaRepo.buscarPorLogin(login) == null) {

					RecuperacaoSenha rpSenha = new RecuperacaoSenha(login, hashLogin);

					recuperacaoSenhaRepo.salvarRecuperacaoSenha(rpSenha);
					EnviarEmailUtil.send(rpSenha.getLogin(), rpSenha.getHash());

					// Caso não seja inválido, valida através da criação uma nova requisição válida
				} else {
					RecuperacaoSenha rpSalvo = recuperacaoSenhaRepo.buscarPorLogin(login);
					rpSalvo.setAtivo(true);
					rpSalvo.setDataCriacao(new Date());
					recuperacaoSenhaRepo.removerRecuperacaoSenha(rpSalvo);
					recuperacaoSenhaRepo.salvarRecuperacaoSenha(rpSalvo);

					EnviarEmailUtil.send(rpSalvo.getLogin(), rpSalvo.getHash());
				}
		}

	}

	// Alteração de senha dentro das páginas de login
	public void alterar(String hash, String novaSenha, String confirmarSenha)
					throws UsuarioNaoEncontradoException, AlteracaoFalhouException, AlteracaoConcluidaException, NullPointerException,
					PersistenceException, SenhaInvalidaException, SenhasNaoCoincidemException, NaoValidoException, SenhaTamanhoInvalidoException {
		if (hash != null) {

			if (novaSenha.equals(confirmarSenha)) {

				if (validar(novaSenha)) {

					RecuperacaoSenha rpSalvo = recuperacaoSenhaRepo.buscarPorHash(hash);

					if (rpSalvo.isAtivo() && recuperacaoSenhaRepo.validarRecuperacaoSenhaPorHash(hash)) {

						Usuario usuario = usuarioRepo.buscarUsuario(rpSalvo.getLogin());
						HistoricoSenha historico = new HistoricoSenha(rpSalvo.getLogin());

						usuario.setSenha(StringToMD5Converter.convertStringToMd5(novaSenha));

						usuarioRepo.alterar(usuario);

						rpSalvo.setAtivo(false);

						recuperacaoSenhaRepo.salvarRecuperacaoSenha(rpSalvo);
						historicoRepo.salvarLog(historico);

						throw new AlteracaoConcluidaException();

					} else {
						throw new AlteracaoFalhouException();
					}
				} else {
					throw new SenhaInvalidaException();
				}
			} else {
				throw new SenhasNaoCoincidemException();
			}
		} else {
			throw new UsuarioNaoEncontradoException();
		}
	}

	// Alteração de senha dentro do sistema
	public void alterar(String nome, String senhaAtual, String novaSenha, String confirmarSenha)
					throws AlteracaoConcluidaException, UsuarioNaoEncontradoException, PersistenceException, SenhaInvalidaException,
					SenhasNaoCoincidemException, SenhaErradaException, SenhaTamanhoInvalidoException {

		if (checarSenha(nome, senhaAtual)) {

			if (novaSenha.equals(confirmarSenha)) {

				if (validar(novaSenha)) {

					Usuario usuario = usuarioRepo.buscarUsuario(nome);
					usuario.setSenha(StringToMD5Converter.convertStringToMd5(confirmarSenha));
					usuarioRepo.alterar(usuario);

					HistoricoSenha historico = new HistoricoSenha(nome);
					historicoRepo.salvarLog(historico);

					throw new AlteracaoConcluidaException();
				} else {
					throw new SenhaInvalidaException();
				}
			} else {
				throw new SenhasNaoCoincidemException();
			}
		}

	}

	public boolean checarSenha(String nome, String senha) throws SenhaErradaException {
		return usuarioRepo.checarSenha(nome, senha);
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
