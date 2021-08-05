package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.business.SistemaBean;
import br.com.keyworks.conf.FrameworkConstants;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.SistemaVersao;

@Named("sistema")
@ViewScoped
public class SistemaBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Inject
	private SistemaBean sistemaBean;

	private String versaoBanco;
	private String versaoAplicacao;
	@SuppressWarnings("unused")
	private SistemaVersao sistemaVersao;

	private boolean versaoIncorreta = false;

	@PostConstruct
	private void init() {
		versaoAplicacao = FrameworkConstants.VERSAO_SISTEMA;

	}

	public boolean isOpenModalVersaoSistema() {
		return versaoIncorreta;
	}

	public String getVersaoAplicacao() {
		return versaoAplicacao;
	}

	public String getVersaoBanco() {
		return versaoBanco;
	}

	public String renderCall() {
		return "  PF('versaoSistemaDialog').show(); ";
	}

}
