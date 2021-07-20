package br.com.keyworks.view.converters;


import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	private static final Cache<Object, String> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterAccess(10, TimeUnit.MINUTES).build();

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {

		if (null == entity) {
			return null;
		}

		synchronized (cache) {

			String entityIdentification = cache.getIfPresent(entity);

			if (Strings.isNullOrEmpty(entityIdentification)) {
				entityIdentification = UUID.randomUUID().toString();
				cache.put(entity, entityIdentification);
			}

			return entityIdentification;

		}

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String uuid) {

		if (Strings.isNullOrEmpty(uuid)) {
			return null;
		}

		synchronized (cache) {

			for (Entry<Object, String> entry : cache.asMap().entrySet()) {
				if (entry.getValue().equals(uuid)) {
					return entry.getKey();
				}
			}

		}

		return null;

	}
}