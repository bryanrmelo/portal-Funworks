package br.com.keyworks.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.SistemaVersao;

@Stateless
public class SistemaBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	public SistemaBean() {

	}

	public SistemaVersao verificarVersaoSistema() {

		SistemaVersao sistemaVersao = entityManager.findFirstWithNamedQuery(SistemaVersao.VERSAO, SistemaVersao.class);
		return sistemaVersao;
	}

}
