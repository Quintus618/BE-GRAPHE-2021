package org.insa.graphs.model;

public class Label {
	
	private final Node courant; 
	private final Node pere;
	
	private boolean marque;
	private int cout;
	
	//Constructeur de la classe Label
	public Label (Node courant) {
		this.courant = courant;
		this.pere = null;
		this.marque = false;
		this.cout = -1;     //Je choisis la convention d'un nombre négatif pour dire que le coût n'est pas défini
	}
	
	//Ensemble de GET
	public Node getCourant() {
		return this.courant;
	}
	
	public Node getPere() {
		return this.pere;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public int getCout() {
		return this.cout;
	}

}