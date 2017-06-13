package model.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

public class MoveUp extends MoveAbstract {

	public MoveUp(Compositor compositor, int maxWidth, int maxHeight) {
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
		if (key == KeyEvent.VK_UP) {
			for (Area glyph : areas) {
				if ((((Block) glyph).getLabel().equals("walker"))) {
					if ((getPositionOfArea(glyph) - maxWidth > 0)
							&& (!((Block) areas.get(getPositionOfArea(glyph) - maxWidth)).getBorder().getType()
									.equals("bottom"))
							&& (!((Block) areas.get(getPositionOfArea(glyph) - maxWidth)).getBorder().getType()
									.equals("both"))
							&& (!((Block) areas.get(getPositionOfArea(glyph) - maxWidth)).getBorder().getType()
									.equals("rupa"))) {
						((Block) areas.get(getPositionOfArea(glyph))).setLabel("");
						((Block) areas.get(getPositionOfArea(glyph))).setBoja(Color.red);
						((Block) areas.get(getPositionOfArea(glyph) - maxWidth)).setLabel("walker");
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
