package br.com.keyworks.repository;

import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import br.com.keyworks.exceptions.NaoValidoException;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.RecuperacaoSenha;

@Stateless
public class RecuperacaoSenhaRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	public RecuperacaoSenha buscarPorLogin(String login) throws NullPointerException {
		try {
			String jpql = "SELECT r FROM RecuperacaoSenha r WHERE login = :login";
			RecuperacaoSenha rp = em.createQuery(jpql, RecuperacaoSenha.class).setParameter("login", login).getSingleResult();
			return rp;
		} catch (NoResultException e) {
			return null;
		}
	}

	public RecuperacaoSenha buscarPorHash(String hash) throws NullPointerException {
		try {
			String jpql = "SELECT r FROM RecuperacaoSenha r WHERE hash = :hash";
			RecuperacaoSenha rp = em.createQuery(jpql, RecuperacaoSenha.class).setParameter("hash", hash).getSingleResult();
			return rp;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean validarRecuperacaoSenhaPorLogin(String login) throws NaoValidoException {
		try {
			RecuperacaoSenha recuperar = buscarPorLogin(login);
			if (recuperar != null) {
				if ((new Date().getTime() - recuperar.getDataCriacao().getTime()) < 900000) {
					return true;
				}
			}
			return false;
		} catch (NoResultException e) {
			return false;
		}
	}

	public boolean validarRecuperacaoSenhaPorHash(String hash) throws NaoValidoException {
		try {
			RecuperacaoSenha recuperar = buscarPorHash(hash);
			if (recuperar != null) {
				if ((new Date().getTime() - recuperar.getDataCriacao().getTime()) < 900000) {
					return true;
				}
			}
			return false;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Transactional
	public void salvarRecuperacaoSenha(RecuperacaoSenha rpSenha) throws PersistenceException {
		em.persist(rpSenha);

	}

	@Transactional
	public void removerRecuperacaoSenha(RecuperacaoSenha rpSenha) {
		em.remove(rpSenha);

	}

}
