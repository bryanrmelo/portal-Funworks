package br.com.keyworks.view.backing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import br.com.keyworks.dto.ContaReceberVencimentoDTO;
import br.com.keyworks.dto.FilterContasReceberDTO;
import br.com.keyworks.enumeracoes.TipoContaEnum;
import br.com.keyworks.filter.FilterContasReceber;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.view.componentes.GridLazyLoader;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;

@Named("contasReceber")
@ViewScoped
public class ContasReceberListagemBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	private List<Empresa> empresaList;
	private List<SelectItem> listaContas;
	private ContaReceber contaReceberSel;
	private List<ContaReceberVencimentoDTO> contaReceberVencimentoDTOList;

	@Inject
	private FilterContasReceber filter;

	@Inject
	private ContasReceberBean contasReceber;

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBean contaBusiness;

	@PostConstruct
	private void init() {

		contaReceberVencimentoDTOList = new ArrayList<ContaReceberVencimentoDTO>();
		empresaList = empresaBusiness.buscaTodasEmpresasAtivas();
		carregaContas();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		String state = (String) fc.getExternalContext().getRequestParameterMap().get("state");
		if (state == null) {
			filter.setFilter(new FilterContasReceberDTO());
		} else {
			carregaContasReceber();
		}
	}

	private void carregaContas() {

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
	}

	public void carregaContasReceber() {

		GridLazyLoaderDTO gridLazyLoaderDTO = new GridLazyLoaderDTO();
		gridLazyLoaderDTO.setFilters(new HashMap<String, Object>());
		gridLazyLoaderDTO.getFilters().put("dataPrevistaInicio", filter.getFilter().getDataRecebimentoInicio());
		gridLazyLoaderDTO.getFilters().put("dataPrevistaFinal", filter.getFilter().getDataRecebimentoFinal());
		gridLazyLoaderDTO.getFilters().put("empresa", filter.getFilter().getEmpresa());
		gridLazyLoaderDTO.getFilters().put("descricao", filter.getFilter().getDescricao());
		gridLazyLoaderDTO.getFilters().put("conta", filter.getFilter().getConta());

		List<ContaReceber> list = contasReceber.buscaTodosSemPaginacao(gridLazyLoaderDTO);
		list.forEach((ContaReceber data) -> {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data.getDataRecebimento());
			data.setMesAnoDataRecebimento(calendar.get(GregorianCalendar.MONTH) + 1 + "/" + calendar.get(GregorianCalendar.YEAR));

		});

		contaReceberVencimentoDTOList = new ArrayList<ContaReceberVencimentoDTO>();
		Map<Object, List<ContaReceber>> listAgrupado = list.stream().collect(Collectors.groupingBy(p -> p.getMesAnoDataRecebimento()));

		listAgrupado.forEach((Object data, List<ContaReceber> p) -> {
			addList(data, p);
		});

		Collections.sort(contaReceberVencimentoDTOList,
						(ContaReceberVencimentoDTO a, ContaReceberVencimentoDTO b) -> a.getDataOrder().compareTo(b.getDataOrder()));
		filter.getFilter().setListaFilter(contaReceberVencimentoDTOList);

	}

	public void addList(Object data, List<ContaReceber> list) {
		contaReceberVencimentoDTOList.add(new ContaReceberVencimentoDTO(data.toString(), list));
	}

	public void excluir(ContaReceber conta) {
		try {
			contasReceber.remove(conta);
			FacesMessageUtils.addInfoMessage("Registro removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível excluir o registro");
		}
		
		carregaContasReceber();
	}

	public GridLazyLoader<ContaReceber> contaReceber() {

		return filter.getFilter().getGridLazyLoader();
	}

	public List<ContaReceberVencimentoDTO> contasReceberList() {
		return filter.getFilter().getListaFilter();
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

	public FilterContasReceber getFilter() {
		return filter;
	}

	public void setFilter(FilterContasReceber filter) {
		this.filter = filter;
	}

	public List<ContaReceberVencimentoDTO> getContaReceberVencimentoDTOList() {
		return contaReceberVencimentoDTOList;
	}

	public void setContaReceberVencimentoDTOList(List<ContaReceberVencimentoDTO> contaReceberVencimentoDTOList) {
		this.contaReceberVencimentoDTOList = contaReceberVencimentoDTOList;
	}

}
