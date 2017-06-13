package model.model;

import java.util.LinkedList;
import java.util.List;

public class SimpleComposition implements Composition {

	private LinkedList<Area> children;
	private Compositor compositor;
	private int steps = 0;

	MoveAbstract abstract1;
	MoveAbstract abstract2;
	MoveAbstract abstract3;
	MoveAbstract abstract4;

	public SimpleComposition(Compositor compositor, int maxWidth, int MaxHeight) {
		children = new LinkedList<>();
		this.compositor = compositor;
		abstract1 = new MoveLeft(compositor, maxWidth, MaxHeight);
		abstract2 = new MoveRight(compositor, maxWidth, MaxHeight);
		abstract3 = new MoveDown(compositor, maxWidth, MaxHeight);
		abstract4 = new MoveUp(compositor, maxWidth, MaxHeight);
		abstract1.setNext(abstract2);
		abstract2.setNext(abstract3);
		abstract3.setNext(abstract4);
	}

	
	

	public int getSteps(){
		return steps;
	}
	
	

	public void setSteps(int steps) {
		this.steps = steps;
	}




	public List<Area> move(int key) {
		 List<Area> res =  abstract1.move(children, key);
		 steps = abstract1.getSteps() + abstract2.getSteps()+ abstract3.getSteps()+abstract4.getSteps();
		 return res;
	}

	@Override
	public List<Area> insert(Area a) {
		children.addLast(a);
		return compositor.compose(children);
	}



}
