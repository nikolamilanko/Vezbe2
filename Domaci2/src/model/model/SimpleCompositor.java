package model.model;

import java.util.LinkedList;

public class SimpleCompositor implements Compositor {
	
	private int ROW_LENGTH;
	
	public void setROW_LENGTH(int rOW_LENGTH) {
		ROW_LENGTH = rOW_LENGTH;
	}

	private LinkedList<Area> rows;
	
	public SimpleCompositor() {
		rows = new LinkedList<>();
	}

	@Override
	public LinkedList<Area> compose(LinkedList<Area> areas) {
		rows.clear();
		if (areas == null || areas.size() == 0) {
			return rows;
		}
		Row lastRow = new Row(ROW_LENGTH);
		for (Area area: areas) {
			if (lastRow.insert(area).isEmpty()) {
				rows.addLast(lastRow);
				lastRow = new Row(ROW_LENGTH);
				lastRow.insert(area);
			}
		}
		rows.add(lastRow);
		return rows;
	}

}
