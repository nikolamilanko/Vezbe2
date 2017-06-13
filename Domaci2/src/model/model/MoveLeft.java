package model.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

public class MoveLeft extends MoveAbstract {

	public MoveLeft(Compositor compositor, int maxWidth, int maxHeight) {
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
		if (key == KeyEvent.VK_LEFT) {
			for (Area area : areas) {
				if ((((Block) area).getLabel().equals("walker"))) {
					if ((((Block) area).getBorder().getJ() % maxWidth != 0)
							&& (!((Block) areas.get(getPositionOfArea(area))).getBorder().getType().equals("left"))
							&& (!((Block) areas.get(getPositionOfArea(area)-1)).getBorder().getType().equals("rupa"))
							&& (!((Block) areas.get(getPositionOfArea(area))).getBorder().getType()
									.equals("both"))) {
						((Block) areas.get(getPositionOfArea(area))).setLabel("");
						((Block) areas.get(getPositionOfArea(area))).setBoja(Color.red);
						((Block) areas.get(getPositionOfArea(area) - 1)).setLabel("walker");
						steps++;
					}
					break;
				}
			}
			return compositor.compose(areas);
		} else {
			try {
				return moveAbstract.move(areas, key);
			} catch (Exception e) {
				System.out.println("End of chain");
				return compositor.compose(areas);
			}
		}
	}
}
