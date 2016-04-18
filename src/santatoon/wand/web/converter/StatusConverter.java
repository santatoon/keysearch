package santatoon.wand.web.converter;

import org.springframework.core.convert.converter.Converter;

import santatoon.wand.domain.Status;


public class StatusConverter {
	private StatusConverter(){}
	
	public static class StatusToString implements Converter<Status, String> {
		public String convert(Status status) {
			return (status == null) ? "" : String.valueOf(status.getValue());
		}
	}
	
	public static class StringToStatus implements Converter<String, Status> {

		public Status convert(String text) {
			return Status.valueOf(Boolean.valueOf(text));
		}
	}

}
