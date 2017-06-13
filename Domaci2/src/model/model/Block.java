package model.model;

import java.awt.Color;

public class Block extends Area {

	private char value;
	
	private String label;
	private Border border;
	private Color boja; 
	
	protected Block( String label, Border border) {
		this.label = label;
		this.border = border;
		boja = Color.white ;
	}
	
	public Block(){
		
	}
	
	

	public Color getBoja() {
		return boja;
	}

	public void setBoja(Color boja) {
		this.boja = boja;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

	public String getLabel() {
		return label;
	}


	

	public void setLabel(String label) {
		this.label = label;
	}



	public Border getBorder() {
		return border;
	}



	public char getValue() {
		return value;
	}
}
