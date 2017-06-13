package model.ui;

import javax.swing.JFrame;

import model.model.SimpleCompositor;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1630569480484011661L;
	
	Labyrinth labyrinth;
	
	public MainFrame() {
		labyrinth = new Labyrinth(new SimpleCompositor());
		getContentPane().add(labyrinth);
		setResizable(true);
		setSize(labyrinth.getMaxWidth()*80,labyrinth.getMaxHeight()*80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
