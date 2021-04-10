package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import java.util.Collections;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.AbstractSolution.Status;
public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	protected int nombre_sommets;
	protected int sommets_visites;
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.sommets_visites = 0; //Nous n'avons pas commencé à visiter des sommmets
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        //DEFINITION DE MON TAS, TABLEAU DE LABEL ET GRAPHE
        BinaryHeap<Label> mon_tas = new BinaryHeap<Label>();
        Graph graphe = data.getGraph();
        Label tableauLabels[] = new Label[graphe.size()];
        Arc[] arcsPredecesseurs = new Arc[graphe.size()];
        
        //CONNAITRE ORIGINE ET DESTINATION
        Node origine = data.getOrigin();
        Node destination = data.getDestination();
        
        //AJOUT DU DEPART
        Label depart = newLabel(origine, data);
        tableauLabels[depart.getCourant().getId()] = depart;
        mon_tas.insert(depart); //Phase insertion dans le tas
        depart.setTas(true);
        depart.setCout(0);
        
        boolean cond_arret = false;
        double cout_algo;
        while ((!mon_tas.isEmpty()) && (!cond_arret)) { //Tant qu'on a pas traité tous les sommets	
        	
        	Label noeud_ac = mon_tas.deleteMin(); //On enlève le min du tas
        	notifyNodeMarked(noeud_ac.getCourant());
        	noeud_ac.setMarque(true); //On marque ce noeud actuel
        	
        	if(noeud_ac.getCourant() == destination) { //On est sur la destination
        		cout_algo = noeud_ac.getCout();
        		cond_arret = true;
        	}
        	
        	for(Arc a : noeud_ac.getCourant().getSuccessors()) { //On regarde tous les successeurs
        		
        		if (!data.isAllowed(a)) { //ON SAUTE CETTE DONNEE SI ELLE N'EST PAS PERMISE (VOITURE, PAS VOITURE) 
        			continue;             //Si on est en vélo, dans l'application java, on programme les chemins pour piéton 
        		}
        		
        		if(tableauLabels[a.getDestination().getId()] == null) {
        			notifyDestinationReached(a.getDestination());
        			tableauLabels[a.getDestination().getId()] = newLabel(a.getDestination(), data);
        		}
        		
        		if(tableauLabels[a.getDestination().getId()].getMarque() == false) {
        			
        			if(tableauLabels[a.getDestination().getId()].getCout() > noeud_ac.getCout() + data.getCost(a)) {
        				tableauLabels[a.getDestination().getId()].setCout(noeud_ac.getCout() + (double) data.getCost(a)); //COUT DE Y = COUT DE X + W(X,Y)
        				tableauLabels[a.getDestination().getId()].setPere(noeud_ac.getCourant());
        				
        				if(tableauLabels[a.getDestination().getId()].getTas()) {
        					mon_tas.remove(tableauLabels[a.getDestination().getId()]); //MISE A JOUR DANS LE TAS (REMOVE -> INSERTION)
        				}
        				else {
        					tableauLabels[a.getDestination().getId()].setTas(true);
        				}
        				
        				mon_tas.insert(tableauLabels[a.getDestination().getId()]); //INSERTION DE Y DANS TAS
        				arcsPredecesseurs[a.getDestination().getId()] = a;
        			}
        			
        		}
        	}
        	
        }
        
		// SI LA DESTINATION EST INACCESSIBLE, PAS POSSIBLE
		if (arcsPredecesseurs[data.getDestination().getId()] == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {

			//DESTINATION ATTEINTE
			notifyDestinationReached(data.getDestination());

			//CREATION DU CHEMIN
			ArrayList<Arc> arcs = new ArrayList<>();
			Arc arc = arcsPredecesseurs[data.getDestination().getId()];

			while (arc != null) {
				arcs.add(arc);
				arc = arcsPredecesseurs[arc.getOrigin().getId()];
			}

			//ON INVERSE LE CHEMIN POUR COMMENCER DE L'ORIGINE
			Collections.reverse(arcs);

			//GENERATION DE LA SOLUTION
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graphe, arcs));
		}
        return solution;
    }
    
	//ASSOCIER UN LABEL ET UN NOEUD
	protected Label newLabel(Node node, ShortestPathData data) {
		return new Label(node);
	}

}
