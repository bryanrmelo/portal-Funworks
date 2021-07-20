package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.view.datamodel.LazyEmpresaDataModel;

@Named("listaEmpresaBack")
@ViewScoped
public class ListaEmpresaBacking extends AbstractBacking{

	@Inject
	private EmpresaBean session;
	
	private static final long serialVersionUID = 1L;
	private LazyDataModel<Empresa> dataModel;
	private String descricao;
	private boolean ativa;
	
	@PostConstruct
	private void init() {
		dataModel = new LazyEmpresaDataModel(session,descricao, ativa);
	}
	
	public LazyDataModel<Empresa> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<Empresa> dataModel) {
		this.dataModel = dataModel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	
	public String novo() {
		return null;
	}
	
	public String listagem() {
		return null;
	}
	
	public void exclui(ActionEvent event) {
	}

}
