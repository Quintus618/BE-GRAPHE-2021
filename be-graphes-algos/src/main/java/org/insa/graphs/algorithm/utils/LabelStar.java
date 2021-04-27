package org.insa.graphs.algorithm.utils;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractInputData.*;
import org.insa.graphs.algorithm.shortestpath.*;

public class LabelStar extends Label{
	protected double coutEstime;
	
	protected double calculCoutEstime(int IdNoeud, ShortestPathData data) {
		AbstractInputData.Mode m = data.getMode();
		double dist = data.getGraph().get(IdNoeud).getPoint().distanceTo(data.getDestination().getPoint());
		return (m == Mode.LENGTH) ? dist : (dist / ((Math.max(data.getGraph().getGraphInformation().getMaximumSpeed(), data.getMaximumSpeed()) / 3.6)));
	}
	
	public LabelStar(Node noeud, ShortestPathData data) {
		super(noeud);
		this.coutEstime = calculCoutEstime(noeud.getId(), data);
	}
	
	//OBTENIR LE COUT
	public double getTotalCost() {
		return this.cout + this.coutEstime;
	}
}
