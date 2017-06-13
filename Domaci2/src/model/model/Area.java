package model.model;

public abstract class Area {
	
	public static Area create(String label, Border border) {
		return new Block(label, border);
	}
}
