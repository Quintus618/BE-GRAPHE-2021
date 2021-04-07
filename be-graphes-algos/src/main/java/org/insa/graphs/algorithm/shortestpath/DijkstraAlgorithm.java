package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        //Connaitre Origine et Destination
        Node origine = data.getOrigin();
        Node destination = data.getDestination();
        Graph graph = data.getGraph();
        
        //INITIALISATION DE TOUS LES LABELS
        int compteur = 0;
        ArrayList<Label> labels = new ArrayList<>();
        for(Node i :graph.getNodes()) {
        	Label labels[i] = new Label(i);
        }
        
        return solution;
    }

}
