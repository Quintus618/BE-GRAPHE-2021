package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;

public class dijkstra_test {

	// Small graphreader use for tests
    private static GraphReader reader;
	
    // Small graph use for tests
    private static Graph graph;
    
    // List of nodes
    private static Node origine, destination;
	
    private static ArcInspector arcInspector = new ArcInspectorFactory().getAllFilters().get(0);

    private static ShortestPathData data;
    private static ShortestPathSolution test_Dijkstra;
    
    @Test
    public void chemin_Normal_Valide_Dij() throws Exception {
    	
    	//IMPORTATION DE LA CARTE INSA ET RECUP DU GRAPHE
    	String nom_Carte = "/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr";
        reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nom_Carte))));
        graph = reader.read();
        
        //CHEMIN QUI MARCHE
        //EST CE QUE LES ALGORITHMES RETOURNENT LES MEMES VALEURS
        origine = graph.get(50);
        destination = graph.get(500);
        data = new ShortestPathData(graph,origine,destination, arcInspector);
        
        test_Dijkstra = new DijkstraAlgorithm(data).doRun();
        
        assertEquals(true, test_Dijkstra.getPath().isValid());
        
    }
    
    @Test
    public void chemin_origine_dest_conf_Valide_Dij() throws Exception {
    	
    	//IMPORTATION DE LA CARTE INSA ET RECUP DU GRAPHE
    	String nom_Carte = "/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr";
        reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nom_Carte))));
        graph = reader.read();
        
        //CHEMIN QUI MARCHE
        //EST CE QUE LES ALGORITHMES RETOURNENT LES MEMES VALEURS
        origine = graph.get(50);
        destination = graph.get(50);
        data = new ShortestPathData(graph,origine,destination, arcInspector);
        
        test_Dijkstra = new DijkstraAlgorithm(data).doRun();
        System.out.println(test_Dijkstra.getPath().isValid());
        assertEquals(true, test_Dijkstra.getPath().isValid());
        
    }
    
}
