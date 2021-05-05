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
import org.junit.BeforeClass;
import org.junit.Test;

public class dijkstra_test {

	// Small graphreader use for tests
    private static GraphReader reader1, reader2;
	
    // Small graph use for tests
    private static Graph graph1, graph2;
    
    // List of nodes
    private static Node origine, destination;
	
    private static ArcInspector arcInspector1, arcInspector2, arcInspector3, arcInspector4, 
    							arcInspector5 = new ArcInspectorFactory().getAllFilters().get(0);

    private static ShortestPathData data1, data2, data3, data4, data5;
    private static ShortestPathSolution test_Dijkstra1, test_Dijkstra2, test_Dijkstra3, test_Dijkstra4,
    									test_Dijkstra5;
    
    public static Graph generationGraphe(String Mapname, GraphReader reader) throws Exception {
    	Graph graph;
    	reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Mapname))));
    	graph = reader.read();
    	return graph;
    }
    
    public static ShortestPathData generationPathData(Graph graph, int origine, int Destination, ArcInspector arcInspector) {
    	return new ShortestPathData(graph,graph.get(origine),graph.get(origine), arcInspector);
    }
    
    @BeforeClass
    public static void Init() throws Exception{
    	graph1 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr",
    								reader1);
    	graph2 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/bretagne.mapgr",
				reader2);
    	
    	data1 = generationPathData(graph1, 50, 500, arcInspector1);
    	data2 = generationPathData(graph1, 50, 50, arcInspector1);
    	//data3 = generationPathData(graph1, -1, 50, arcInspector1);
    	//data4 = generationPathData(graph1, 50, -1, arcInspector1);
    	data5 = generationPathData(graph1, 50, 80, arcInspector5);
    	
        test_Dijkstra1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Dijkstra2 = new DijkstraAlgorithm(data2).doRun();
        test_Dijkstra3 = new DijkstraAlgorithm(data3).doRun();
        test_Dijkstra4 = new DijkstraAlgorithm(data4).doRun();
        test_Dijkstra5 = new DijkstraAlgorithm(data5).doRun();
    	
    }
/*******************************************************************************/
/*******************************************************************************/
    
    @Test
    public void chemin_Valide_Dij() throws Exception {   
        
        assertEquals(true, test_Dijkstra1.getPath().isValid()); 
        assertEquals(false, test_Dijkstra1.isFeasible());
    }
    
    /*@Test
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

        assertEquals(true, test_Dijkstra.getPath().isValid());
        
    }
    
    @Test
    public void longueur_chemin_Normal_Dij() throws Exception {
    	
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
        
        assertEquals((float)1653.8992, (float)test_Dijkstra.getPath().getLength(),0.0);     
    }*/
    
}
