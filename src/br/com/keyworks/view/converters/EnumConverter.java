package br.com.keyworks.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "enumConverter")
public class EnumConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
		if (id != null && !id.isEmpty()) {
			return uiComponent.getAttributes().get(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		if (objeto instanceof Enum) {
			Enum e = (Enum) objeto;
			if (e != null) {
				uiComponent.getAttributes().put(e.name(), e);
				return e.name();
			}
		}
		return "";
	}
}