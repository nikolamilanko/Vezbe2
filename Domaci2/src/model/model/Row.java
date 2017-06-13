package model.model;

import java.util.LinkedList;
import java.util.List;

public class Row extends Area implements Composition {
	
	private LinkedList<Area> children;
	private final int maxLength;
	
	protected Row(int maxLength) {
		children = new LinkedList<>();
		this.maxLength = maxLength;
	}

	@Override
	public List<Area> insert(Area a) {
		if (children.size() == maxLength) {
			return new LinkedList<>();
		}
		children.addLast(a);
		return children;
	}


	public LinkedList<Area> getChildren() {
		return children;
	}

}
