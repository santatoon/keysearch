package santatoon.wand.domain;


public enum Skintype{
	NORMAL(1, "Normal"), DRY(2, "Dry"), OILY(3, "Oily"), COMBINATION(4, "Combination");
	private final int value;
	private final String name;
	
	Skintype(int value, String name){
		this.value = value;
		this.name = name;
	}
	public int getValue(){
		return value;
	}
	public String getName(){
		return name;
	}
	public static Skintype valueOf(int value){
		switch(value){
		case 1 : return NORMAL;
		case 2 : return NORMAL;
		case 3 : return OILY;
		case 4 : return COMBINATION;
		default : throw new AssertionError("Unknown value: " + value);
		}
	}
}
