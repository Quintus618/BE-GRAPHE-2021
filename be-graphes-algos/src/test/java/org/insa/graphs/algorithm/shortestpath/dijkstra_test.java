package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
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
    private static ArrayList<Node> nodes;
    
    //Path
    private static Path Comp1, Comp2, Comp3;
	
    private static ArcInspector arcInspector1, arcInspector2, arcInspector3,
    							arcInspector4, arcInspector5, arcInspector6 ;
    
    private static ShortestPathData data1, data2, data3, data4, data5, data6;
    private static ShortestPathSolution test_Dijkstra1, test_Dijkstra2, test_Dijkstra3, test_Dijkstra4,
    									test_Dijkstra5,test_Dijkstra6;
    
    public static Graph generationGraphe(String Mapname, GraphReader reader) throws Exception {
    	Graph graph;
    	reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(Mapname))));
    	graph = reader.read();
    	return graph;
    }
    
    public static ShortestPathData generationPathData(Graph graph, int origine, int Destination, ArcInspector arcInspector) {
    	return new ShortestPathData(graph ,graph.get(origine) ,graph.get(Destination), arcInspector);
    }
    
    //Reconstruction d'un chemin en tableau
    public static ArrayList<Node> tabNoeuds(Path chemin){
    	ArrayList<Node> node = new ArrayList<Node>();
    	node.add(chemin.getOrigin()); //On ajoute l'origine pour avoir un tableau de notre chemin
    	
    	List<Arc> arcs = chemin.getArcs();
    	for(Arc a : arcs) { //On reconstruit le chemin
    		node.add(a.getDestination());
    	}
    	return node;
    }
    
    @BeforeClass
    public static void Init() throws Exception{
    	
    	ArcInspector arcInspector1 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector2 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector3 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector4 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector5 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector6 = ArcInspectorFactory.getAllFilters().get(0);
    	    
    	
    	
    	graph1 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr",
    								reader1);
    	graph2 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/bretagne.mapgr",
				reader2);
    	
    	data1 = generationPathData(graph1, 50, 500, arcInspector1);
    	data2 = generationPathData(graph1, 50, 50, arcInspector2);
    	data3 = generationPathData(graph1, 50, 80, arcInspector3);
    	data4 = generationPathData(graph2, 50, 126989, arcInspector4);
    	data5 = generationPathData(graph2, 50, 50, arcInspector5);
    	data6 = generationPathData(graph2, 616047, 486251, arcInspector6);
    	
        test_Dijkstra1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Dijkstra2 = new DijkstraAlgorithm(data2).doRun();
        test_Dijkstra3 = new DijkstraAlgorithm(data3).doRun();
        test_Dijkstra4 = new DijkstraAlgorithm(data4).doRun();
        test_Dijkstra5 = new DijkstraAlgorithm(data5).doRun();
        test_Dijkstra6 = new DijkstraAlgorithm(data6).doRun();       
    }
    
/*******************************************************************************/
/*******************************************************************************/
    
    @Test
    public void chemin_Valide_Dij() throws Exception {   
        assertEquals(true, test_Dijkstra1.getPath().isValid()); //Chemin normale INSA
        assertEquals(true, test_Dijkstra4.getPath().isValid()); //Chemin normale Bretagne
        
        //Chemins qui ne sont pas valides car infaisables
        assertEquals(false, test_Dijkstra2.isFeasible()); //Chemin origine, destination confondue INSA
        assertEquals(false, test_Dijkstra3.isFeasible()); //Les deux points ne sont pas reliés INSA
        assertEquals(false, test_Dijkstra5.isFeasible()); //Chemin origine, destination confondue Bretagne
        assertEquals(false, test_Dijkstra6.isFeasible()); //Les deux points ne sont pas reliés Bretagne
        
    }
    
    @Test
    public void longueur_chemin_Dij() throws Exception {
        assertEquals(Path.createShortestPathFromNodes(graph1, tabNoeuds(test_Dijkstra1.getPath())).getLength(), (float)test_Dijkstra1.getPath().getLength(),0.0);     
    }
    
    @Test
    public void Temps_chemin_Dij() throws Exception {
        assertEquals(Path.createFastestPathFromNodes(graph1, tabNoeuds(test_Dijkstra1.getPath())).getMinimumTravelTime(), test_Dijkstra1.getPath().getMinimumTravelTime(),0.0);     
    }
    
}
