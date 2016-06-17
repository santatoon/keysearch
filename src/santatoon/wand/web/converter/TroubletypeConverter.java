package santatoon.wand.web.converter;

import org.springframework.core.convert.converter.Converter;

import santatoon.wand.domain.Troubletype;


public class TroubletypeConverter {
	private TroubletypeConverter(){}
	
	public static class TroubletypeToString implements Converter<Troubletype, String> {
		public String convert(Troubletype troubletype) {
			return (troubletype == null) ? "" : String.valueOf(troubletype.getValue());
		}
	}
	
	public static class StringToTroubletype implements Converter<String, Troubletype> {

		public Troubletype convert(String text) {
			return Troubletype.valueOf(Integer.valueOf(text));
		}
	}

}
