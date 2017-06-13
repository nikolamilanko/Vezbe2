package model.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

public class MoveDown extends MoveAbstract {

	public MoveDown(Compositor compositor, int maxWidth, int maxHeight) {
		super(compositor, maxWidth, maxHeight);
		// TODO Auto-generated constructor stub
	}

	private MoveAbstract moveAbstract;

	@Override
	public void setNext(MoveAbstract moveAbstract) {
		this.moveAbstract = moveAbstract;
	}

	@Override
	public List<Area> move(LinkedList<Area> areas, int key) {
		if (key == KeyEvent.VK_DOWN) {
			for (Area area : areas) {
				if ((((Block) area).getLabel().equals("walker"))) {
					if ((getPositionOfArea(area) + maxWidth < maxWidth * maxHeight)
							&& (!((Block) areas.get(getPositionOfArea(area))).getBorder().getType().equals("bottom"))
							&& (!((Block) areas.get(getPositionOfArea(area))).getBorder().getType().equals("both"))
							&& (!((Block) areas.get(getPositionOfArea(area) + maxWidth)).getBorder().getType().equals("rupa"))) {
						((Block) areas.get(getPositionOfArea(area))).setLabel("");
						((Block) areas.get(getPositionOfArea(area))).setBoja(Color.red);
						((Block) areas.get(getPositionOfArea(area) + maxWidth)).setLabel("walker");
						steps++;
					}

					break;
				} 
			}

			return compositor.compose(areas);
		} else {
			return moveAbstract.move(areas, key);
		}
	}
}
