package br.com.keyworks.view.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import br.com.keyworks.view.backing.LoginBacking;

@FacesConverter(value = "DateConverter")
public class DateConverter implements javax.faces.convert.Converter {

	@SuppressWarnings("unused")
	@Inject
	private LoginBacking loginService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = formatter.parse(value);
			return date;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		if (value.getClass().equals(String.class)) {
			try {
				SimpleDateFormat format2 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
				date = format2.parse((String) value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			date = (Date) value;
		}
		return format.format(date);
	}
}
