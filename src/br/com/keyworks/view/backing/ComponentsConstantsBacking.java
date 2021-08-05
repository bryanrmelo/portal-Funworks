package br.com.keyworks.view.backing;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import br.com.keyworks.enumeracoes.SimNaoEnum;

@ManagedBean(name = "componentsConstants", eager = true)
@ApplicationScoped
public final class ComponentsConstantsBacking {

	public static final String DATA_GRID_COMMAND_COLUMN_ID = "data-grid-command-column";
	public static final String GRID_ROWS_DEFAULT = "10";
	public static final String GRID_ROWS_PER_PAGE_TEMPLATE = "10,20,50";
	public static final String GRID_CURRENT_PAGE_REPORT_TEMPLATE = "(#{msgs.GRID_DISPLAYING} {startRecord} - {endRecord} #{msgs.GRID_OF} {totalRecords})";
	public static final String GRID_PAGINATOR_TEMPLATE = "{FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown} {CurrentPageReport}";

	public ComponentsConstantsBacking() {
		super();
	}

	public String getGridRowsDefault() {
		return GRID_ROWS_DEFAULT;
	}

	public String getRowsPerPageTemplate() {
		return GRID_ROWS_PER_PAGE_TEMPLATE;
	}

	public String getCurrentPageReportTemplate() {
		return GRID_CURRENT_PAGE_REPORT_TEMPLATE;
	}

	public String getPaginatorTemplate() {
		return GRID_PAGINATOR_TEMPLATE;
	}

	public String getGridCommandColumnId() {
		return DATA_GRID_COMMAND_COLUMN_ID;
	}

	public String getSimNaoBoolean(boolean value) {
		return SimNaoEnum.getById(value).getDescricao();
	}
}
