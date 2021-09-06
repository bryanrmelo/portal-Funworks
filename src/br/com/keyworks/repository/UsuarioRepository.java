package br.com.keyworks.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.keyworks.exceptions.SenhaErradaException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.util.StringToMD5Converter;

@Stateless
public class UsuarioRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

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

	public Usuario getUsuario(String login) throws UsuarioNaoEncontradoException {
		String jpql = "select u from Usuario u where login = :login";
		try {
			Usuario usuario = em.createQuery(jpql, Usuario.class).setParameter("login", login).getSingleResult();
			return usuario;
		} catch (Exception e) {
			throw new UsuarioNaoEncontradoException();
		}
	}

	public Long getUsuarioCount() {
		String jpql = "SELECT COUNT(m) FROM Mensalidade m";
		return (Long) em.createQuery(jpql).getSingleResult();

	}

	public Usuario getUsuarioById(Integer id) {
		String jpql = "select u from Usuario u where id = :id";
		return em.createQuery(jpql, Usuario.class).setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		return em.createQuery(jpql).getResultList();
	}

	@Transactional
	public void merge(Usuario usuario) {
		em.merge(usuario);
	}

	@Transactional
	public void persist(Usuario usuario) {
		em.persist(usuario);

	}

}
