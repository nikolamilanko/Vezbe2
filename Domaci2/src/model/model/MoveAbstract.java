package model.model;

import java.util.LinkedList;
import java.util.List;

public abstract class MoveAbstract {
	protected int maxWidth;
	protected int maxHeight;
	protected Compositor compositor;
	protected int steps =0;
	public MoveAbstract(Compositor compositor,int maxWidth,int maxHeight) {
		this.compositor = compositor;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}
	
	public int getSteps(){
		return steps;
	}
	
	public abstract void setNext(MoveAbstract moveAbstract);
	
	public abstract List<Area> move(LinkedList<Area> areas, int key);
	
	public int getPositionOfArea(Area a){
		return ((Block) a).getBorder().getI()*maxWidth+((Block)a).getBorder().getJ();
	}
	
}
