package br.com.keyworks.framework.faces.backing;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class AbstractBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int NRO_REGISTROS_POR_PAGINA = 15;
	private static final String OPCOES_REGISTROS_POR_PAGINA = "3,5,15,30,50";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

	// para retornar a página correta das listagens quando editando registros
	private int first;

	// O número de registros usados na página
	private int nroRegistrosPorPagina = NRO_REGISTROS_POR_PAGINA;

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getNroRegistrosPorPagina() {
		return nroRegistrosPorPagina;
	}

	public void setNroRegistrosPorPagina(int nroRegistrosPorPagina) {
		this.nroRegistrosPorPagina = nroRegistrosPorPagina;
	}

	protected void definePrimeiraPagina() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String sFirst = (String) fc.getExternalContext().getRequestParameterMap().get("first");
		if (sFirst != null) {
			this.setFirst(Integer.parseInt(sFirst));
		}
		String sNro = (String) fc.getExternalContext().getRequestParameterMap().get("nro");
		if (sNro != null) {
			this.setNroRegistrosPorPagina(Integer.parseInt(sNro));
		}
	}

	public String getUsuarioLogado() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (principal != null) {
			return principal.getName();
		}
		return null;
	}

	public String getDataHora() {

		Date dataHora = new Date();
		return sdf.format(dataHora);

	}

	public String getContexto() {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return context.getContextPath();
	}

	public ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	protected void showMessage(String summary, String detail) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		fc.addMessage(null, message);
	}

	protected void showMessage(String clientId, String summary, String detail) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(summary, detail);
		fc.addMessage(clientId, message);
	}

	protected boolean hasMessages() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return !fc.getMessageList().isEmpty();
	}

	public String getOpcoesRegistrosPorPagina() {
		return OPCOES_REGISTROS_POR_PAGINA;
	}

	public String getNenhumRegistroEncontrado() {
		return NENHUM_REGISTRO_ENCONTRADO;
	}

	public Object getParametro(String parametro) {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext().getRequestParameterMap().get(parametro);
	}
}
