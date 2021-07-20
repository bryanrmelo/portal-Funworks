package br.com.keyworks.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class FacesMessageUtils {

	private FacesMessageUtils() {
	}

	public static void addInfoMessage(String messageCode) {
		addInfoMessageImpl("widget-messages", messageCode);
	}

	private static void addInfoMessageImpl(String clientMessageWidgetId, String messageCode) {
		FacesContext.getCurrentInstance().addMessage(clientMessageWidgetId, createFacesMessage(FacesMessage.SEVERITY_INFO, messageCode));
	}

	public static FacesMessage createFacesMessage(Severity severity, String messageCode) {
		return new FacesMessage(severity, messageCode, "");
	}

	public static void addErrorMessage(String messageCode) {
		addErrorMessage("widget-messages", messageCode);
	}

	public static void addErrorMessage(String clientMessageWidgetId, String messageCode) {
		addErrorMessageImpl(clientMessageWidgetId, createFacesMessage(FacesMessage.SEVERITY_ERROR, messageCode));
	}

	private static void addErrorMessageImpl(String clientMessageWidgetId, FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(clientMessageWidgetId, facesMessage);
	}

}
