package br.com.kiman.curso.rest.config;

import java.lang.annotation.Annotation; 
import java.lang.reflect.Type;
import java.time.LocalDate;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import br.com.kiman.curso.dto.converter.LocalDateAdapter;


@Provider
public class RestConverterProvider implements ParamConverterProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType == LocalDate.class) {
			return (ParamConverter<T>) new LocalDateAdapter();
		}
		return null;
	}

}
