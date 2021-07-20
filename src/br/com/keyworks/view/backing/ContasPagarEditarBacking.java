package br.com.keyworks.view.backing;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import br.com.keyworks.business.TipoRateioBean;
import br.com.keyworks.enumeracoes.TipoContaEnum;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.model.entities.administracao.ContaPagarRateio;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.Fornecedor;
import br.com.keyworks.model.entities.administracao.Rateio;
import br.com.keyworks.model.entities.administracao.RateioEmpresa;
import br.com.keyworks.model.entities.administracao.TipoRateio;
import br.com.keyworks.util.FacesMessageUtils;

@Named("contasPagarEditar")
@ViewScoped
public class ContasPagarEditarBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;
	private Integer TIPO_RATEIO = 0;

	@Inject
	private ContasPagarBean contasPagar;

	@Inject
	private TipoRateioBean tipoRateioBusiness;

	@Inject
	private FornecedorBean fornecedorBusiness;

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBean contaBusiness;

	private List<Fornecedor> fornecedorList;
	private List<TipoRateio> tipoRateioPreConfiguradosList;
	private List<Empresa> empresaList;
	private List<SelectItem> listaContas;
	private List<RateioEmpresa> listaRateioEmpresa;
	private ContaPagar contaPagarSel;

	@PostConstruct
	private void init() {

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
		String sId = (String) fc.getExternalContext().getRequestParameterMap().get("id");
		if (sId != null) {
			Integer id = Integer.parseInt(sId);
			buscaContaPagarId(id);
			preparaPreRateio();
			carregaInputsRateios();
			rateiaEmpresas();
		} else {
			novo();
			carregaInputsRateios();
		}

	}

	public void buscaContaPagarId(Integer id) {
		contaPagarSel = contasPagar.buscaPorId(id);
		if (contaPagarSel == null) {
			novo();
		}
	}

	public void novo() {
		contaPagarSel = new ContaPagar();
		contaPagarSel.setContaPagarRateio(new ContaPagarRateio());
		contaPagarSel.getContaPagarRateio().setTipoRateio(0);
	}

	public void salvar() {

		BigDecimal sumValor = new BigDecimal(0);
		contaPagarSel.getContaPagarRateio().setRateioEmpresa(listaRateioEmpresa);
		for (RateioEmpresa rateio : contaPagarSel.getContaPagarRateio().getRateioEmpresa()) {
			if (rateio.getValor() != null) {
				sumValor = sumValor.add(rateio.getValor());
			}
		}
		if (sumValor.compareTo(new BigDecimal(0)) < 1) {
			FacesMessageUtils.addErrorMessage("Não foi informado um valor de rateio para ao menos uma empresa!");
			return;
		}

		if (contaPagarSel.getContaPagarRateio().getTipoRateio() == 0) {
			sumValor = new BigDecimal(0);
			for (RateioEmpresa rateio : contaPagarSel.getContaPagarRateio().getRateioEmpresa()) {
				if (rateio.getValor() != null) {
					sumValor = sumValor.add(rateio.getValor());
				}

			}
			if (sumValor.compareTo(contaPagarSel.getValor()) != 0) {
				FacesMessageUtils.addErrorMessage("Verifique os dados informados. O valor dos rateios e o valor total são diferentes!");
				return;
			}
		} else {
			Double somaPercentual = 0D;
			for (RateioEmpresa rateio : contaPagarSel.getContaPagarRateio().getRateioEmpresa()) {
				if (rateio.getValor() != null) {
					somaPercentual += rateio.getValor().doubleValue();
				}

			}
			if (somaPercentual != 100) {
				FacesMessageUtils.addErrorMessage("Verifique o percentual de rateio informado!");
				return;
			}
		}

		List<ContaPagar> listaPersist = new ArrayList<ContaPagar>();
		listaPersist.add(contaPagarSel);

		contaPagarSel.getContaPagarRateio()
						.getRateioEmpresa()
						.removeAll(contaPagarSel.getContaPagarRateio().getRateioEmpresa().stream()
										.filter((order) -> (order.getValor() == null || order.getValor().doubleValue() < 0))
										.collect(Collectors.toList()));

		if (contaPagarSel.getId() == null) {
			if (contaPagarSel.isReplicaMeses()) {
				if (contaPagarSel.getQtdReplicaMeses() == null || contaPagarSel.getQtdReplicaMeses() <= 0) {
					FacesMessageUtils.addErrorMessage("Intervalo de réplica de meses não informado corretamente!");
					return;
				}

				// Integer qtdMeses = diferencaMeses(contaPagarSel.getDataReplicaPeriodoInicial(),
				// contaPagarSel.getDataReplicaPeriodoFinal());

				int incrementaMes = 1;
				GregorianCalendar date = new GregorianCalendar();
				for (int i = 0; i < contaPagarSel.getQtdReplicaMeses(); i++) {

					ContaPagar countAux = new ContaPagar();
					countAux.setId(null);
					countAux.setConta(contaPagarSel.getConta());

					date.setTime(contaPagarSel.getDataPrevista());
					date.add(GregorianCalendar.MONTH, incrementaMes);
					countAux.setDataPrevista(date.getTime());

					date.setTime(contaPagarSel.getDataVencimento());
					date.add(GregorianCalendar.MONTH, incrementaMes);
					countAux.setDataVencimento(date.getTime());

					countAux.setDescricao(contaPagarSel.getDescricao());
					countAux.setEmpresa(contaPagarSel.getEmpresa());
					countAux.setFornecedor(contaPagarSel.getFornecedor());
					countAux.setContaPagarRateio(contaPagarSel.getContaPagarRateio());
					countAux.setValor(contaPagarSel.getValor());
					countAux.setReplicaMeses(false);
					countAux.setQtdReplicaMeses(null);
					// countAux.setDataReplicaPeriodoInicial(null);
					// countAux.setDataReplicaPeriodoFinal(null);
					contasPagar.merge(countAux);
					incrementaMes++;
				}
			}
		}

		try {
			contasPagar.salva(contaPagarSel);
			FacesMessageUtils.addErrorMessage("Registro Salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível salvar o registro!");
		}

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listaContasPagar?pesquisar=true");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int diferencaMeses(Date um, Date dois) {

		final double MES_EM_MILISEGUNDOS = 30.0 * 24.0 * 60.0 * 60.0 * 1000.0;

		Double numeroDeMeses = (double) ((dois.getTime() - um.getTime()) / MES_EM_MILISEGUNDOS);

		return numeroDeMeses.intValue();
	}

	public void resetRateios() {
		contaPagarSel.getContaPagarRateio().setRateioPreSelecionado(false);
		contaPagarSel.getContaPagarRateio().setRateioPreSelecionadoId(null);
		for (RateioEmpresa empresaRateio : listaRateioEmpresa) {
			empresaRateio.setValor(null);
		}

		// Preenche com os valores persistidos ao trocar de opção no radio button
		if (contaPagarSel.getId() != null && contaPagarSel.getContaPagarRateio().getTipoRateio() == TIPO_RATEIO) {
			buscaContaPagarId(contaPagarSel.getId());
			listaRateioEmpresa = contaPagarSel.getContaPagarRateio().getRateioEmpresa();

			// Carrega Inputs dos Rateios
			List<Empresa> auxEmpresaList = new ArrayList<Empresa>(empresaList);
			auxEmpresaList = excluiEmpresaJaContidaLista();

			incluiOutrasEmpresasNaLista(auxEmpresaList);

			reordenaListaDescricaoEmpresa();
		}

		preparaPreRateio();

	}

	public void preparaPreRateio() {

		if (contaPagarSel.getContaPagarRateio().isRateioPreSelecionado()) {
			tipoRateioPreConfiguradosList = tipoRateioBusiness.buscaTodosTiposRateios();
			if (contaPagarSel.getId() != null && contaPagarSel.getContaPagarRateio().getRateioPreSelecionadoId() != null) {
				contaPagarSel.getContaPagarRateio().setRateioPreSelecionadoId(
								tipoRateioBusiness.buscaPorId(contaPagarSel.getContaPagarRateio().getRateioPreSelecionadoId().getId()));
			}
		} else {
			tipoRateioPreConfiguradosList = new ArrayList<TipoRateio>();
		}

	}

	public void rateiaEmpresas() {

		if (contaPagarSel.getContaPagarRateio().getRateioPreSelecionadoId() != null) {

			for (RateioEmpresa empresaRateio : listaRateioEmpresa) {
				empresaRateio.setValor(null);
			}

			if (contaPagarSel.getContaPagarRateio().isRateioPreSelecionado()) {
				for (Rateio rateio : contaPagarSel.getContaPagarRateio().getRateioPreSelecionadoId().getRateio()) {
					for (RateioEmpresa empresaRateio : listaRateioEmpresa) {
						if (rateio.getEmpresa().getId() == empresaRateio.getEmpresa().getId()) {
							empresaRateio.setValor(new BigDecimal(rateio.getPercentual()));
						}
					}
				}
			}
		} else {
			carregaInputsRateios();
		}

	}

	public void carregaInputsRateios() {

		TIPO_RATEIO = contaPagarSel.getContaPagarRateio().getTipoRateio();

		List<Empresa> auxEmpresaList = new ArrayList<Empresa>(empresaList);

		if (contaPagarSel.getContaPagarRateio() != null && contaPagarSel.getContaPagarRateio().getRateioEmpresa() != null) {

			listaRateioEmpresa = contaPagarSel.getContaPagarRateio().getRateioEmpresa();
			auxEmpresaList = excluiEmpresaJaContidaLista();

		} else {

			listaRateioEmpresa = new ArrayList<RateioEmpresa>();
		}

		incluiOutrasEmpresasNaLista(auxEmpresaList);

		reordenaListaDescricaoEmpresa();

	}

	private List<Empresa> excluiEmpresaJaContidaLista() {
		List<Empresa> auxEmpresaList = new ArrayList<Empresa>(empresaList);
		for (RateioEmpresa rateioEmpresa : listaRateioEmpresa) {
			if (auxEmpresaList.contains(rateioEmpresa.getEmpresa())) {
				auxEmpresaList.remove(rateioEmpresa.getEmpresa());
			}
		}
		return auxEmpresaList;
	}

	private void incluiOutrasEmpresasNaLista(List<Empresa> auxEmpresaList) {
		for (Empresa emp : auxEmpresaList) {
			if (!emp.isSacado()) {
				RateioEmpresa rateioEmpresa = new RateioEmpresa();
				rateioEmpresa.setEmpresa(emp);
				listaRateioEmpresa.add(rateioEmpresa);
			}
		}
	}

	// Reordena lista pela descrição da empresa
	private void reordenaListaDescricaoEmpresa() {
		Collections.sort(listaRateioEmpresa,
						(RateioEmpresa a, RateioEmpresa b) -> a.getEmpresa().getDescricao().compareTo(b.getEmpresa().getDescricao()));
	}

	public ContaPagar getContaPagarSel() {
		return contaPagarSel;
	}

	public void setContaPagarSel(ContaPagar contaPagarSel) {
		this.contaPagarSel = contaPagarSel;
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

	public List<TipoRateio> getTipoRateioPreConfiguradosList() {
		return tipoRateioPreConfiguradosList;
	}

	public void setTipoRateioPreConfiguradosList(List<TipoRateio> tipoRateioPreConfigurados) {
		this.tipoRateioPreConfiguradosList = tipoRateioPreConfigurados;
	}

	public List<RateioEmpresa> getListaRateioEmpresa() {
		return listaRateioEmpresa;
	}

	public void setListaRateioEmpresa(List<RateioEmpresa> listaRateioEmpresa) {
		this.listaRateioEmpresa = listaRateioEmpresa;
	}

}
