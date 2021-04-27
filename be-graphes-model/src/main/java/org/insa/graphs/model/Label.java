package org.insa.graphs.model;

public class Label implements Comparable<Label> { //Permettant de mettre des labels dans mon tas
	
	protected double cout;
	private Node courant; 
	private Node pere;
	private boolean marque;
	private boolean appartientTas;
	
	//Constructeur de la classe Label
	public Label (Node courant) {
		this.courant = courant;
		this.pere = null;
		this.marque = false;
		this.cout = 1.0/0.0;     //Je choisis la convention infini pour un noeud ou le cout est inconnu
		this.appartientTas = false;
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
	
	public boolean getTas() {
		return this.appartientTas;
	}
	
	//Modification en vue de l'implémentation de l'AStar
	public double getTotalCost(){
		return this.cout;
	}
	
	//ENSEMBLE DE Set
	public void setCourant(Node Courant) {
		this.courant = Courant;
	}
	
	public void setPere(Node pere) {
		this.pere = pere;
	}
	
	public void setTas(boolean b) {
		this.appartientTas = b;
	}
	
	public void setCout(double c) {
		this.cout = c;
	}
	
	public void setMarque(boolean b) {
		this.marque = b;
	}
	
	//Methode qui doit être définie car abstract
	public int compareTo(Label autre) {
        int resultat;
        if(this.getTotalCost() < autre.getTotalCost()) { //Modification pour A*
        	resultat = -1;
        }
        else if (this.getTotalCost() == autre.getTotalCost()) { //Modification pour A*
        	resultat = 0;
        }
        else {
        	resultat = 1;
        }
        return resultat;
	}
}