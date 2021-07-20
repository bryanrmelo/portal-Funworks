package br.com.keyworks.view.backing;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.business.ContaBancariaBean;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.filter.FilterContaBancaria;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.ContaBancaria;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.RateioEmpresa;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.view.componentes.GridLazyLoader;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.IGridLazyLoader;
import br.com.keyworks.view.componentes.PagedResult;

@Named("contaBancaria")
@ViewScoped
public class ContaBancariaListagemBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	private List<Empresa> empresaList;
	private ContaBancaria contaBancaria;
	private List<RateioEmpresa> rateioEmpresaList;

	private GridLazyLoader<ContaBancaria> gridLazyLoader;

	@Inject
	private FilterContaBancaria filter;

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBancariaBean contaBancariaBusines;

	@PostConstruct
	private void init() {
		empresaList = empresaBusiness.buscaTodasEmpresasAtivas();

		if (getParametro("pesquisar") != null && Boolean.parseBoolean((String) getParametro("pesquisar"))) {
			pesquisar();
		} else {
			filter = new FilterContaBancaria();
		}
	}

	public void excluir(ContaBancaria conta) {

		try {
			contaBancariaBusines.remove(conta);
			FacesMessageUtils.addInfoMessage("Registro removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtils.addErrorMessage("Não foi possível excluir o registro");
		}
	}

	public void pesquisar() {
		gridLazyLoader = new GridLazyLoader<ContaBancaria>(new IGridLazyLoader<ContaBancaria>() {

			@Override
			public PagedResult<ContaBancaria> load(GridLazyLoaderDTO gridLazyLoaderDTO) {

				gridLazyLoaderDTO.getFilters().put("empresa", filter.getFilter().getEmpresa());
				gridLazyLoaderDTO.getFilters().put("descricao", filter.getFilter().getDescricao());

				return contaBancariaBusines.buscarContasBancarias(gridLazyLoaderDTO);
			}
		});
	}

	public GridLazyLoader<ContaBancaria> getGridLazyLoader() {
		return gridLazyLoader;
	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public List<RateioEmpresa> getRateioEmpresaList() {
		return rateioEmpresaList;
	}

	public void setRateioEmpresaList(List<RateioEmpresa> rateioEmpresaList) {
		this.rateioEmpresaList = rateioEmpresaList;
	}

	public FilterContaBancaria getFilter() {
		return filter;
	}

	public void setFilter(FilterContaBancaria filter) {
		this.filter = filter;
	}

}
