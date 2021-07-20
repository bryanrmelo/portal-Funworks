package br.com.keyworks.view.backing;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.business.ContaBancariaBean;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.ContaBancaria;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.Fornecedor;
import br.com.keyworks.util.FacesMessageUtils;

@Named("contaBancariaEditar")
@ViewScoped
public class ContaBancariaEditarBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaBancariaBean contaBancariaBean;

	@Inject
	private EmpresaBean empresaBusiness;

	private List<Fornecedor> fornecedorList;
	private List<Empresa> empresaList;
	private ContaBancaria contaBancaria;

	@PostConstruct
	private void init() {

		empresaList = empresaBusiness.buscaTodasEmpresasAtivas();

		FacesContext fc = FacesContext.getCurrentInstance();
		String sId = (String) fc.getExternalContext().getRequestParameterMap().get("id");
		if (sId != null) {
			Long id = Long.parseLong(sId);
			buscaContaPagarId(id);
		} else {
			novo();
		}

	}

	public void buscaContaPagarId(Long id) {
		contaBancaria = contaBancariaBean.buscaPorId(id);
		if (contaBancaria == null) {
			novo();
		}
	}

	public void novo() {
		contaBancaria = new ContaBancaria();
		contaBancaria.setSaldoAtual(new BigDecimal(0));
	}

	public void salvar() {

		if (contaBancaria.isAtivo()) {
			ContaBancaria contaBancariaExistente = contaBancariaBean.buscarContaBancariaDescricaoAtiva(contaBancaria.getDescricao(), true);

			if (contaBancariaExistente != null && !contaBancariaExistente.getId().equals(contaBancaria.getId())) {
				FacesMessageUtils.addErrorMessage("Já existe uma conta ativa com essa descrição!");
				return;
			}
		}

		try {
			contaBancariaBean.salva(contaBancaria);
			FacesMessageUtils.addErrorMessage("Registro Salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível salvar o registro!");
		}

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaContasBancarias?pesquisar=true");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public List<Fornecedor> getFornecedorList() {
		return fornecedorList;
	}

	public void setFornecedorList(List<Fornecedor> fornecedorList) {
		this.fornecedorList = fornecedorList;
	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

}
