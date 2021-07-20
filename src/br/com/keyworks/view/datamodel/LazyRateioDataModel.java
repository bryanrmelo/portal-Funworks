package br.com.keyworks.view.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.business.RateioBean;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.Rateio;

public class LazyRateioDataModel extends LazyDataModel<Rateio> {

	private static final long serialVersionUID = 1L;

	private RateioBean session;
	
	public  LazyRateioDataModel(RateioBean session) {
		this.session = session;
	}
	


	
}