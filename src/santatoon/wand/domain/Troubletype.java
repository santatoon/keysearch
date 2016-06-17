package santatoon.wand.domain;


public enum Troubletype{
	NONE(1, "None"), ATOPY(2, "Atopy"), ACNE(3, "Acne"), SENSITIVE(4, "Sensitive");
	private final int value;
	private final String name;
	
	Troubletype(int value, String name){
		this.value = value;
		this.name = name;
	}
	public int getValue(){
		return value;
	}
	public String getName(){
		return name;
	}
	public static Troubletype valueOf(int value){		
		switch(value){
		case 1 : return NONE;
		case 2 : return ATOPY;
		case 3 : return ACNE;
		case 4 : return SENSITIVE;
		default : throw new AssertionError("Unknown value: " + value);
		}
	}
}
