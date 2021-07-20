package br.com.keyworks.view.backing;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.business.ContaBean;
import br.com.keyworks.business.ContasReceberBean;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.enumeracoes.TipoContaEnum;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.util.FacesMessageUtils;

@Named("contasReceberEditar")
@ViewScoped
public class ContasReceberEditarBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContasReceberBean contasReceber;

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBean contaBusiness;

	private List<Empresa> empresaList;
	private List<SelectItem> listaContas;
	private ContaReceber contaReceberSel;

	@PostConstruct
	private void init() {

		empresaList = empresaBusiness.buscaTodasEmpresasAtivas();
		List<Conta> contaList = contaBusiness.buscaContasPorTipo(TipoContaEnum.RECEITAS.name());

		List<Conta> listPai = new ArrayList<Conta>();
		List<Conta> listFilho = new ArrayList<Conta>();
		for (Conta co : contaList) {
			if (co.getPai() == null) {
				listPai.add(co);
			} else {
				listFilho.add(co);
			}
		}
		listaContas = new ArrayList<SelectItem>();
		for (Conta pai : listPai) {
			List<SelectItem> item = new ArrayList<SelectItem>();
			SelectItemGroup group = new SelectItemGroup(pai.getDescricao());
			for (Conta filho : listFilho) {
				if (pai.getId() != null && pai.getId() == filho.getPai().getId()) {
					item.add(new SelectItem(filho, filho.getDescricao()));
				}
			}
			group.setSelectItems(item.toArray(new SelectItem[item.size()]));
			listaContas.add(group);
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		String sId = (String) fc.getExternalContext().getRequestParameterMap().get("id");
		if (sId != null) {
			Integer id = Integer.parseInt(sId);
			buscaUsuarioId(id);
		} else {
			novo();
		}

	}

	public void buscaUsuarioId(Integer id) {
		contaReceberSel = contasReceber.buscaPorId(id);
		if (contaReceberSel == null) {
			novo();
		}
	}

	public void novo() {
		contaReceberSel = new ContaReceber();
	}

	public void salvar() {

		if (contaReceberSel.getEmpresa() == null) {
			FacesMessageUtils.addErrorMessage("Campo Empresa Obrigatório!");
			return;
		}

		if (contaReceberSel.getConta() == null) {
			FacesMessageUtils.addErrorMessage("Campo Conta Obrigatório!");
			return;
		}

		if (contaReceberSel.getDataRecebimento() == null) {
			FacesMessageUtils.addErrorMessage("Campo Data Recebimento Obrigatório!");
			return;
		}

		if (contaReceberSel.getDescricao() == null) {
			FacesMessageUtils.addErrorMessage("Campo Descrição Obrigatório!");
			return;
		}

		if (contaReceberSel.getValor().compareTo(new BigDecimal(0)) <= 0) {
			FacesMessageUtils.addErrorMessage("Não foi informado um valor total!");
			return;
		}

		try {
			contasReceber.salvar(contaReceberSel);
			FacesMessageUtils.addErrorMessage("Registro Salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível salvar o registro!");
		}

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaContasReceber?state=true");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public List<SelectItem> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<SelectItem> listaContas) {
		this.listaContas = listaContas;
	}

	public ContaReceber getContaReceberSel() {
		return contaReceberSel;
	}

	public void setContaReceberSel(ContaReceber contaReceberSel) {
		this.contaReceberSel = contaReceberSel;
	}

}
