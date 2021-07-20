package br.com.keyworks.view.backing;

import java.math.BigDecimal;
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
import br.com.keyworks.business.ContasPagarBean;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.business.FornecedorBean;
import br.com.keyworks.dto.ContaPagarVencimentoDTO;
import br.com.keyworks.dto.FilterContasPagarDTO;
import br.com.keyworks.enumeracoes.TipoContaEnum;
import br.com.keyworks.filter.FilterContasPagar;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.Fornecedor;
import br.com.keyworks.model.entities.administracao.RateioEmpresa;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.view.componentes.GridLazyLoader;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;

@Named("contasPagar")
@ViewScoped
public class ContasPagarListagemBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	private List<Fornecedor> fornecedorList;
	private List<Empresa> empresaList;
	private List<SelectItem> listaContas;
	private ContaPagar contaPagarSel;
	private List<RateioEmpresa> rateioEmpresaList;
	private List<ContaPagarVencimentoDTO> contaPagarVencimentoDTOList;

	@Inject
	private ContasPagarBean contasPagar;

	@Inject
	private FilterContasPagar filter;

	@Inject
	private FornecedorBean fornecedorBusiness;

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBean contaBusiness;

	@PostConstruct
	private void init() {

		contaPagarVencimentoDTOList = new ArrayList<ContaPagarVencimentoDTO>();
		fornecedorList = fornecedorBusiness.buscaTododFornecedoresAtivos();
		empresaList = empresaBusiness.buscaTodasEmpresasAtivas();
		List<Conta> contaList = contaBusiness.buscaContasPorTipo(TipoContaEnum.DESPESAS.name());

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
		String state = (String) fc.getExternalContext().getRequestParameterMap().get("pesquisar");
		if (state == null) {
			filter.setFilter(new FilterContasPagarDTO());
		} else {
			carregaContasPagar();
		}

	}

	public void carregaRateios() {
		rateioEmpresaList = contasPagar.buscaPorId(contaPagarSel.getId()).getContaPagarRateio().getRateioEmpresa();
		rateioEmpresaList = rateioEmpresaList.stream().filter((order) -> order.getValor() != null).collect(Collectors.toList());

		for (RateioEmpresa rat : rateioEmpresaList) {
			if (contaPagarSel.getContaPagarRateio().getTipoRateio() == 1) {
				if (rat.getValor().compareTo(new BigDecimal(0)) >= 0) {
					rat.setValor(contaPagarSel.getValor().multiply((rat.getValor().divide(new BigDecimal(100)))));
				}
			}
		}
	}

	public void editar() {
	}

	public void excluir(ContaPagar conta) {

		try {
			contasPagar.remove(conta);
			FacesMessageUtils.addInfoMessage("Registro removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível excluir o registro");

		}

		conta.getConta();
		carregaContasPagar();
	}

	public void carregaContasPagar() {

		// filter.getFilter().setGridLazyLoader(new GridLazyLoader<ContaPagar>(new IGridLazyLoader<ContaPagar>() {
		//
		// @Override
		// public PagedResult<ContaPagar> load(GridLazyLoaderDTO gridLazyLoaderDTO) {
		//
		// gridLazyLoaderDTO.getFilters().put("dataPrevistaInicio", filter.getFilter().getDataPrevistaInicio());
		// gridLazyLoaderDTO.getFilters().put("dataPrevistaFinal", filter.getFilter().getDataPrevistaFinal());
		// gridLazyLoaderDTO.getFilters().put("dataVencimentoInicio", filter.getFilter().getDataVencimentoInicio());
		// gridLazyLoaderDTO.getFilters().put("dataVencimentoFinal", filter.getFilter().getDataVencimentoFim());
		// gridLazyLoaderDTO.getFilters().put("fornecedor", filter.getFilter().getFornecedor());
		// gridLazyLoaderDTO.getFilters().put("empresa", filter.getFilter().getEmpresa());
		// gridLazyLoaderDTO.getFilters().put("descricao", filter.getFilter().getDescricao());
		// gridLazyLoaderDTO.getFilters().put("conta", filter.getFilter().getConta());
		// gridLazyLoaderDTO.getFilters().put("valorInicial", filter.getFilter().getValorInicial());
		// gridLazyLoaderDTO.getFilters().put("valorFinal", filter.getFilter().getValorFinal());
		//
		// return contasPagar.buscaTodosPaginados(gridLazyLoaderDTO);
		// }
		// }));

		GridLazyLoaderDTO gridLazyLoaderDTO = new GridLazyLoaderDTO();
		gridLazyLoaderDTO.setFilters(new HashMap<String, Object>());
		gridLazyLoaderDTO.getFilters().put("dataPrevistaInicio", filter.getFilter().getDataPrevistaInicio());
		gridLazyLoaderDTO.getFilters().put("dataPrevistaFinal", filter.getFilter().getDataPrevistaFinal());
		gridLazyLoaderDTO.getFilters().put("dataVencimentoInicio", filter.getFilter().getDataVencimentoInicio());
		gridLazyLoaderDTO.getFilters().put("dataVencimentoFinal", filter.getFilter().getDataVencimentoFim());
		gridLazyLoaderDTO.getFilters().put("fornecedor", filter.getFilter().getFornecedor());
		gridLazyLoaderDTO.getFilters().put("empresa", filter.getFilter().getEmpresa());
		gridLazyLoaderDTO.getFilters().put("descricao", filter.getFilter().getDescricao());
		gridLazyLoaderDTO.getFilters().put("conta", filter.getFilter().getConta());
		gridLazyLoaderDTO.getFilters().put("valorInicial", filter.getFilter().getValorInicial());
		gridLazyLoaderDTO.getFilters().put("valorFinal", filter.getFilter().getValorFinal());

		List<ContaPagar> list = contasPagar.buscaTodosSemPaginacao(gridLazyLoaderDTO);
		list.forEach((ContaPagar data) -> {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data.getDataVencimento());
			data.setMesAnoDataVencimento(calendar.get(GregorianCalendar.MONTH) + 1 + "/" + calendar.get(GregorianCalendar.YEAR));
		});

		contaPagarVencimentoDTOList = new ArrayList<ContaPagarVencimentoDTO>();
		Map<Object, List<ContaPagar>> listAgrupado = list.stream().collect(Collectors.groupingBy(p -> p.getMesAnoDataVencimento()));

		listAgrupado.forEach((Object data, List<ContaPagar> p) -> {
			addList(data, p);
		});

		Collections.sort(contaPagarVencimentoDTOList,
						(ContaPagarVencimentoDTO a, ContaPagarVencimentoDTO b) -> a.getDataOrder().compareTo(b.getDataOrder()));
		filter.setFilter(new FilterContasPagarDTO());
		filter.getFilter().setListaFilter(contaPagarVencimentoDTOList);
	}

	public void addList(Object data, List<ContaPagar> list) {

		ContaPagarVencimentoDTO contaPagarVencimento = new ContaPagarVencimentoDTO(data.toString(), list);
		contaPagarVencimentoDTOList.add(contaPagarVencimento);

	}

	public GridLazyLoader<ContaPagar> contasPagar() {

		return filter.getFilter().getGridLazyLoader();
	}

	public List<ContaPagarVencimentoDTO> contasPagarList() {
		return filter.getFilter().getListaFilter();
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

	public List<SelectItem> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<SelectItem> listaContas) {
		this.listaContas = listaContas;
	}

	public ContaPagar getContaPagarSel() {
		return contaPagarSel;
	}

	public void setContaPagarSel(ContaPagar contaPagarSel) {
		this.contaPagarSel = contaPagarSel;
	}

	public List<RateioEmpresa> getRateioEmpresaList() {
		return rateioEmpresaList;
	}

	public void setRateioEmpresaList(List<RateioEmpresa> rateioEmpresaList) {
		this.rateioEmpresaList = rateioEmpresaList;
	}

	public FilterContasPagar getFilter() {
		return filter;
	}

	public void setFilter(FilterContasPagar filter) {
		this.filter = filter;
	}

	public List<ContaPagarVencimentoDTO> getContaPagarVencimentoDTOList() {
		return contaPagarVencimentoDTOList;
	}

	public void setContaPagarVencimentoDTOList(List<ContaPagarVencimentoDTO> contaPagarVencimentoDTOList) {
		this.contaPagarVencimentoDTOList = contaPagarVencimentoDTOList;
	}

}
