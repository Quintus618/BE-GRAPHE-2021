package org.insa.graphs.model;

public class Label {
	
	private Node courant; 
	private final Node pere;
	
	private boolean marque;
	private double cout;
	
	//Constructeur de la classe Label
	public Label (Node courant) {
		this.courant = courant;
		this.pere = null;
		this.marque = false;
		this.cout = 1.0/0.0;     //Je choisis la convention infini pour un noeud ou le cout est inconnu
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
	
	public double getCout() {
		return this.cout;
	}
	
	//ENSEMBLE DE Set
	public void setCourant(Node Courant) {
		this.courant = Courant;
	}

}