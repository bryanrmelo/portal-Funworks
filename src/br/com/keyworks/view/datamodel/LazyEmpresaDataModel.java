package br.com.keyworks.view.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.model.entities.administracao.Empresa;

public class LazyEmpresaDataModel extends LazyDataModel<Empresa> {

	private static final long serialVersionUID = 1L;

	private EmpresaBean session;
	private String descricao;
	private boolean ativa;
	
	public  LazyEmpresaDataModel(EmpresaBean session, String descricao, boolean ativa ) {
		this.session = session;
		this.descricao = descricao;
		this.ativa = ativa;
	}
	
	@Override
	public List<Empresa> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<Empresa> retorno = new ArrayList<>();
		
//		retorno = this.session.buscaComDescricaoContendo(descricao, ativa,first, pageSize);
//		retorno = this.session.buscaTodasEmpresas(first, pageSize);
		int quantos = this.session.buscarTodasEmpresas().intValue();
//		int quantos = this.session.contaComDescricaoContendo(descricao, ativa).intValue();
 
		// rowCount
		this.setRowCount(quantos);

		return retorno;
	}

	@Override
	public int getRowCount() {
		int quantos = 0;
//		quantos = session.contaComDescricaoContendo(descricao, ativa).intValue();
		quantos = session.buscarTodasEmpresas().intValue();
		return quantos;
	}

	@Override
	public Empresa getRowData(String rowKey) {
		Integer id = null;
		Empresa empresa = null;
		id = Integer.parseInt(rowKey);
		empresa = this.session.buscaPorId(id);
		return empresa;
	}

	@Override
	public Object getRowKey(Empresa empresa) {
		return empresa.getId();
	}

	
}