package br.com.kiman.curso.dto.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.ws.rs.ext.ParamConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> implements ParamConverter<LocalDate> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

	@Override
	public LocalDate fromString(String date) {
		return stringToLocalDate(date);
	}

	@Override
	public String toString(LocalDate localDate) {
		return localDateToString(localDate);
	}

	@Override
	public String marshal(LocalDate localDate) throws Exception {
		return localDateToString(localDate);
	}

	@Override
	public LocalDate unmarshal(String date) throws Exception {
		return stringToLocalDate(date);
	}

	private LocalDate stringToLocalDate(String date) {
		if (date != null && date.isEmpty()) {
			return null;
		}
		return Optional.ofNullable(date).map(d -> {
			return FORMATTER.parse(date, LocalDate::from);
		}).orElse(null);
	}

	private String localDateToString(LocalDate localDate) {
		return Optional.ofNullable(localDate).map(d -> {
			return FORMATTER.format(d);
		}).orElse(null);
	}

}
