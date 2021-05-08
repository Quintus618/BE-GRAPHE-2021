package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
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

public class scenarioTest {

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
    
    private static ShortestPathSolution test_Astar1, test_Astar2, test_Astar3, test_Astar4,
	test_Astar5,test_Astar6;
    
    private static ShortestPathSolution test_Bellman1, test_Bellman2, test_Bellman3, test_Bellman4,
    test_Bellman5,test_Bellman6;
    
    /*CREATION D'UN GRAPHE*/
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
    	
    	//Init ArcInspector
    	ArcInspector arcInspector1 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector2 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector3 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector4 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector5 = ArcInspectorFactory.getAllFilters().get(0);
    	ArcInspector arcInspector6 = ArcInspectorFactory.getAllFilters().get(0);
    	    
    	
    	//Graphe INSA
    	graph1 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr",
    								reader1);
    	//Graphe Bretagne
    	graph2 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/bretagne.mapgr",
				reader2);
    	
    	//Chemin normal Insa
    	data1 = generationPathData(graph1, 50, 500, arcInspector1);
    	//Orgine et Destination confondues INSA (Infaisable par convention utilisée du Bellman-Ford)
    	data2 = generationPathData(graph1, 50, 50, arcInspector2);
    	//Chemin infaisable INSA
    	data3 = generationPathData(graph1, 50, 80, arcInspector3);
    	
    	//Chemin normal Bretagne
    	data4 = generationPathData(graph2, 50, 126989, arcInspector4);
    	//Orgine et Destination confondues Bretagne (Infaisable par convention utilisée du Bellman-Ford)
    	data5 = generationPathData(graph2, 50, 50, arcInspector5);
    	//Chemin infaisable Bretagne
    	data6 = generationPathData(graph2, 616047, 486251, arcInspector6);
    	
        test_Bellman1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Bellman2 = new DijkstraAlgorithm(data2).doRun();
        test_Bellman3 = new DijkstraAlgorithm(data3).doRun(); 
    	
        test_Dijkstra1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Dijkstra2 = new DijkstraAlgorithm(data2).doRun();
        test_Dijkstra3 = new DijkstraAlgorithm(data3).doRun();
        test_Dijkstra4 = new DijkstraAlgorithm(data4).doRun();
        test_Dijkstra5 = new DijkstraAlgorithm(data5).doRun();
        test_Dijkstra6 = new DijkstraAlgorithm(data6).doRun();   
        
        test_Astar1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Astar2 = new DijkstraAlgorithm(data2).doRun();
        test_Astar3 = new DijkstraAlgorithm(data3).doRun();
        test_Astar4 = new DijkstraAlgorithm(data4).doRun();
        test_Astar5 = new DijkstraAlgorithm(data5).doRun();
        test_Astar6 = new DijkstraAlgorithm(data6).doRun();   
    }
    
/*******************************************************************************/
/*******************************************************************************/
    
    /******************************TEST*******************************************/
	@Test
    public void test_chemin_faisable_avec_oracle() throws Exception { 	
        
		/****************Toutes les routes permises**************/
		//Chemin valide INSA
		assertEquals(true, test_Bellman1.getPath().isValid());
		assertEquals(true, test_Dijkstra1.getPath().isValid());
		assertEquals(true, test_Astar1.getPath().isValid());
		
		//Chemin invalide INSA
		assertEquals(false, test_Bellman2.isFeasible());
		assertEquals(false, test_Bellman3.isFeasible());
		assertEquals(false, test_Dijkstra2.isFeasible());
		assertEquals(false, test_Dijkstra3.isFeasible());
		assertEquals(false, test_Astar2.isFeasible());
		assertEquals(false, test_Astar3.isFeasible());
		
		//Chemin valide Bretagne
		assertEquals(true, test_Dijkstra4.getPath().isValid());
		assertEquals(true, test_Astar4.getPath().isValid());
		
		//Chemin invalide Bretagne
		assertEquals(false, test_Dijkstra5.isFeasible());
		assertEquals(false, test_Dijkstra6.isFeasible());
		assertEquals(false, test_Astar5.isFeasible());
		assertEquals(false, test_Astar6.isFeasible());
		
    }
	
	@Test
    public void test_chemin_faisable_sans_oracle() throws Exception { 	
        
		/****************Toutes les routes permises**************/	
		//Chemin valide Bretagne
		assertEquals(true, test_Dijkstra4.getPath().isValid());
		assertEquals(true, test_Astar4.getPath().isValid());
		
		//Chemin invalide Bretagne
		assertEquals(false, test_Dijkstra5.isFeasible());
		assertEquals(false, test_Dijkstra6.isFeasible());
		assertEquals(false, test_Astar5.isFeasible());
		assertEquals(false, test_Astar6.isFeasible());
		
    }

}
