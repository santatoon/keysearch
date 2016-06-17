package santatoon.wand.web.converter;

import org.springframework.core.convert.converter.Converter;

import santatoon.wand.domain.Skintype;


public class SkintypeConverter {
	private SkintypeConverter(){}
	
	public static class SkintypeToString implements Converter<Skintype, String> {
		public String convert(Skintype skintype) {
			return (skintype == null) ? "" : String.valueOf(skintype.getValue());
		}
	}
	
	public static class StringToSkintype implements Converter<String, Skintype> {

		public Skintype convert(String text) {
			return Skintype.valueOf(Integer.valueOf(text));
		}
	}

}
