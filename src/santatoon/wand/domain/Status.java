package santatoon.wand.domain;


public enum Status{
	ON(true, "ON"), OFF(false, "OFF");
	private final boolean value;
	private final String name;
	
	Status(boolean value, String name){
		this.value = value;
		this.name = name;
	}
	public boolean getValue(){
		return value;
	}
	public String getName(){
		return name;
	}
	public static Status valueOf(boolean value){
		if(value)
			return ON;
		else
			return OFF;
	}
}
