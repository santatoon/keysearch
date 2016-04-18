package santatoon.wand.web.converter;

import java.sql.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter {
	private DateConverter(){}
	
	public static class DateToString implements Converter<Date, String> {
		public String convert(Date date) {
			return (date == null) ? "" : String.valueOf(date.toString());
		}
	}
	
	public static class StringToDate implements Converter<String, Date> {

		public Date convert(String text) {
			return Date.valueOf(text);
		}
	}
}
