package br.com.keyworks.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import br.com.keyworks.exceptions.SenhaErradaException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.util.StringToMD5Converter;

@Stateless
public class UsuarioRepository {

	@PersistenceContext
	private EntityManager em;

	public boolean checarSenha(String login, String senha) throws SenhaErradaException {
		senha = StringToMD5Converter.convertStringToMd5(senha);
		String jpql = "select u from Usuario u where login = :login and senha = :senha";
		try {
			Usuario usuario = em.createQuery(jpql, Usuario.class).setParameter("login", login).setParameter("senha", senha).getSingleResult();
			if (usuario != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new SenhaErradaException();
		}
	}

	public Usuario buscarUsuario(String login) throws UsuarioNaoEncontradoException {
		String jpql = "select u from Usuario u where login = :login";
		try {
			Usuario usuario = em.createQuery(jpql, Usuario.class).setParameter("login", login).getSingleResult();
			return usuario;
		} catch (Exception e) {
			throw new UsuarioNaoEncontradoException();
		}
	}

	public Long getQuantidadeUsuarios() {
		String jpql = "SELECT COUNT(m) FROM Mensalidade m";
		return (Long) em.createQuery(jpql).getSingleResult();

	}

	@Transactional
	public void alterar(Usuario usuario) {
		em.merge(usuario);
	}

	@Transactional
	public void registrar(Usuario usuario) {
		em.persist(usuario);

	}

}
