package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;

public class scenarioTest {

	// Small graphreader use for tests
    private static GraphReader reader;
	
    // Small graph use for tests
    private static Graph graph;
    
    // List of nodes
    private static Node origine, destination;
	
    private static ArcInspector arcInspector = new ArcInspectorFactory().getAllFilters().get(0);

    private static ShortestPathData data;
    private static ShortestPathSolution test_Bellman;
    private static ShortestPathSolution test_Dijkstra;
    private static ShortestPathSolution test_Astar;
    
    /******************************TEST*******************************************/
	@Test
    public void test_Distance_chemin_faisable() throws Exception { 	
    	//IMPORTATION DE LA CARTE INSA ET RECUP DU GRAPHE
    	String nom_Carte = "/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr";
        reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nom_Carte))));
        graph = reader.read();
        
        //CHEMIN QUI MARCHE
        //EST CE QUE LES ALGORITHMES RETOURNENT LES MEMES VALEURS
        origine = graph.get(50);
        destination = graph.get(500);
        data = new ShortestPathData(graph,origine,destination, arcInspector);
        
        test_Bellman = new BellmanFordAlgorithm(data).doRun();
        test_Dijkstra = new DijkstraAlgorithm(data).doRun();
        test_Astar = new AStarAlgorithm(data).doRun();
        
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Dijkstra.getPath().getLength(), (float)test_Astar.getPath().getLength(), 0.0);
    }
	
	@Test
    public void test_Distance_origine_dest_confondues() throws Exception { 	
    	//IMPORTATION DE LA CARTE INSA ET RECUP DU GRAPHE
    	String nom_Carte = "/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr";
        reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nom_Carte))));
        graph = reader.read();
        
        //CHEMIN QUI MARCHE
        //EST CE QUE LES ALGORITHMES RETOURNENT LES MEMES VALEURS
        origine = graph.get(50);
        destination = graph.get(50);
        data = new ShortestPathData(graph,origine,destination, arcInspector);
        
        test_Bellman = new BellmanFordAlgorithm(data).doRun();
        test_Dijkstra = new DijkstraAlgorithm(data).doRun();
        test_Astar = new AStarAlgorithm(data).doRun();
        
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Dijkstra.getPath().getLength(), (float)test_Astar.getPath().getLength(), 0.0);
    }
	
	@Test
    public void test_Distance_chemin_infaisable() throws Exception { 	
    	//IMPORTATION DE LA CARTE INSA ET RECUP DU GRAPHE
    	String nom_Carte = "/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr";
        reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nom_Carte))));
        graph = reader.read();
        
        //CHEMIN QUI MARCHE
        //EST CE QUE LES ALGORITHMES RETOURNENT LES MEMES VALEURS
        origine = graph.get(50);
        destination = graph.get(850);
        data = new ShortestPathData(graph,origine,destination, arcInspector);
        
        test_Bellman = new BellmanFordAlgorithm(data).doRun();
        test_Dijkstra = new DijkstraAlgorithm(data).doRun();
        test_Astar = new AStarAlgorithm(data).doRun();
        
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Bellman.getPath().getLength(), (float)test_Dijkstra.getPath().getLength(), 0.0);
        assertEquals((float)test_Dijkstra.getPath().getLength(), (float)test_Astar.getPath().getLength(), 0.0);
    }
}
