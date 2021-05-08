package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
    private static ArcInspector arcInspector1, arcInspector11, arcInspector2, arcInspector3,
    							arcInspector4, arcInspector5, arcInspector6 ;
    
    private static ShortestPathData data1, data11, data2, data3, data4, data44, data5, data6, data7, data77, data8, data88;
    
    private static ShortestPathSolution test_Dijkstra1, test_Dijkstra11, test_Dijkstra2, test_Dijkstra3, test_Dijkstra4, test_Dijkstra44,
    									test_Dijkstra5,test_Dijkstra6, test_Dijkstra7, test_Dijkstra77, test_Dijkstra8, test_Dijkstra88;
    
    private static ShortestPathSolution test_Astar1, test_Astar11, test_Astar2, test_Astar3, test_Astar4, test_Astar44,
	test_Astar5,test_Astar6, test_Astar7, test_Astar77, test_Astar8, test_Astar88;
    
    private static ShortestPathSolution test_Bellman1, test_Bellman11, test_Bellman2, test_Bellman3, test_Bellman4, test_Bellman44,
    test_Bellman5, test_Bellman55, test_Bellman6;
    
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
    	ArcInspector arcInspector1 = ArcInspectorFactory.getAllFilters().get(0); //Tous les moyens de transports permis Longueur
    	ArcInspector arcInspector11 = ArcInspectorFactory.getAllFilters().get(2); //Tous les moyens de transports permis Temps
    	ArcInspector arcInspector2 = ArcInspectorFactory.getAllFilters().get(4); //Que les piétons pour la longueur
    	ArcInspector arcInspector3 = ArcInspectorFactory.getAllFilters().get(1); //Que les voitures	pour la longueur
    	ArcInspector arcInspector4 = ArcInspectorFactory.getAllFilters().get(5); //Que les piétons pour le temps
    	ArcInspector arcInspector5 = ArcInspectorFactory.getAllFilters().get(3); //Que les voitures	pour le temps 
    	
    	//Graphe INSA
    	graph1 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/insa.mapgr",
    								reader1);
    	//Graphe Bretagne
    	graph2 = generationGraphe("/home/insa/Documents/3A/S6/BE Graphe/Maps-20210320T132136Z-001/Maps/bretagne.mapgr",
				reader2);
    	
    	/****TOUS LES MOYENS DE TRANSPORTS PERMIS****/
    	//TEST LA DISTANCE
    	//Chemin normal Insa
    	data1 = generationPathData(graph1, 50, 500, arcInspector1);
    	//Orgine et Destination confondues INSA (Infaisable par convention utilisée du Bellman-Ford)
    	data2 = generationPathData(graph1, 50, 50, arcInspector1);
    	//Chemin infaisable INSA
    	data3 = generationPathData(graph1, 50, 80, arcInspector1);
    	
    	//Chemin normal Bretagne
    	data4 = generationPathData(graph2, 50, 126989, arcInspector1);
    	//Orgine et Destination confondues Bretagne (Infaisable par convention utilisée du Bellman-Ford)
    	data5 = generationPathData(graph2, 50, 50, arcInspector1);
    	//Chemin infaisable Bretagne
    	data6 = generationPathData(graph2, 616047, 486251, arcInspector1);
    	
        test_Bellman1 = new BellmanFordAlgorithm(data1).doRun(); 
        test_Bellman2 = new BellmanFordAlgorithm(data2).doRun();
        test_Bellman3 = new BellmanFordAlgorithm(data3).doRun(); 
    	
        test_Dijkstra1 = new DijkstraAlgorithm(data1).doRun(); 
        test_Dijkstra2 = new DijkstraAlgorithm(data2).doRun();
        test_Dijkstra3 = new DijkstraAlgorithm(data3).doRun();
        test_Dijkstra4 = new DijkstraAlgorithm(data4).doRun();
        test_Dijkstra5 = new DijkstraAlgorithm(data5).doRun();
        test_Dijkstra6 = new DijkstraAlgorithm(data6).doRun();   
        
        test_Astar1 = new AStarAlgorithm(data1).doRun(); 
        test_Astar2 = new AStarAlgorithm(data2).doRun();
        test_Astar3 = new AStarAlgorithm(data3).doRun();
        test_Astar4 = new AStarAlgorithm(data4).doRun();
        test_Astar5 = new AStarAlgorithm(data5).doRun();
        test_Astar6 = new AStarAlgorithm(data6).doRun(); 
        
        //TEST LE TEMPS
    	//Chemin normal Insa
    	data11 = generationPathData(graph1, 50, 500, arcInspector11);
    	
    	//Chemin normal Bretagne
    	data44 = generationPathData(graph2, 50, 126989, arcInspector11);
    	
    	test_Bellman11 = new BellmanFordAlgorithm(data11).doRun();
    	test_Dijkstra11 = new DijkstraAlgorithm(data11).doRun();
    	test_Astar11 = new AStarAlgorithm(data11).doRun();
    	
    	test_Dijkstra44 = new DijkstraAlgorithm(data44).doRun();
    	test_Astar44 = new AStarAlgorithm(data44).doRun();
        
    	
    	//DISTANCE
        /****UNIQUEMENT PIETONS ET VELOS****/
        //INSA
        data7 = generationPathData(graph1, 284, 1012, arcInspector2);
        test_Bellman4 = new BellmanFordAlgorithm(data7).doRun();
        test_Dijkstra7 = new DijkstraAlgorithm(data7).doRun();
        test_Astar7 = new AStarAlgorithm(data7).doRun();

        
        /****ROUTES POUR LES VOITURES****/
        //INSA
        data8 = generationPathData(graph1, 284, 1012, arcInspector3);
        test_Bellman5 = new BellmanFordAlgorithm(data8).doRun();
        test_Dijkstra8 = new DijkstraAlgorithm(data8).doRun();
        test_Astar8 = new AStarAlgorithm(data8).doRun();
        
        
        //TEMPS
        /****UNIQUEMENT PIETONS ****/
        //INSA
        data77 = generationPathData(graph1, 284, 1012, arcInspector4);
        test_Bellman44 = new BellmanFordAlgorithm(data77).doRun();
        test_Dijkstra77 = new DijkstraAlgorithm(data77).doRun();
        test_Astar77 = new AStarAlgorithm(data77).doRun();

        
        /****ROUTES POUR LES VOITURES****/
        //INSA
        data88 = generationPathData(graph1, 284, 1012, arcInspector3);
        test_Bellman55 = new BellmanFordAlgorithm(data88).doRun();
        test_Dijkstra88 = new DijkstraAlgorithm(data88).doRun();
        test_Astar88 = new AStarAlgorithm(data88).doRun();
    }
    
/*******************************************************************************/
/*******************************************************************************/
    
    /******************************TEST*******************************************/
	@Test
    public void test_chemin_faisable_avec_oracle_INSA() throws Exception { 	
        
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
		
		/**************Que les piétons*************/
		//INSA
		assertEquals(true, test_Dijkstra7.getPath().isValid());
		assertEquals(true, test_Bellman4.getPath().isValid());
		assertEquals(true, test_Astar7.getPath().isValid());
		
		/**************Que les voitures*************/
		//INSA
		assertEquals(true, test_Dijkstra8.getPath().isValid());
		assertEquals(true, test_Bellman5.getPath().isValid());
		assertEquals(true, test_Astar8.getPath().isValid());
    }
	
	@Test
	public void test_chemin_distance_avec_oracle_INSA() throws Exception {		
		//CAS DES CHEMINS VALIDES
		//TOUS LES MOYENS DE TRANSPORTS
		assertEquals(test_Bellman1.getPath().getLength(), test_Dijkstra1.getPath().getLength(), 0.0);
		assertEquals(test_Bellman1.getPath().getLength(), test_Astar1.getPath().getLength(), 0.0);
		
		//QUE LES PIETONS
		assertEquals(test_Bellman4.getPath().getLength(), test_Dijkstra7.getPath().getLength(), 0.0);
		assertEquals(test_Bellman4.getPath().getLength(), test_Astar7.getPath().getLength(), 0.0);
		
		//QUE LES VOITURES
		assertEquals(test_Bellman5.getPath().getLength(), test_Dijkstra8.getPath().getLength(), 0.0);
		assertEquals(test_Bellman5.getPath().getLength(), test_Astar8.getPath().getLength(), 0.0);
	}
	
	@Test
	public void test_chemin_temps_avec_oracle_INSA() throws Exception {		
		//CAS DES CHEMINS VALIDES
		//TOUS LES MOYENS DE TRANSPORTS
		assertEquals(test_Bellman11.getPath().getMinimumTravelTime(), test_Dijkstra11.getPath().getMinimumTravelTime(), 0.0);
		assertEquals(test_Bellman11.getPath().getMinimumTravelTime(), test_Astar11.getPath().getMinimumTravelTime(), 0.0);
		
		//QUE LES PIETONS
		assertEquals(test_Bellman44.getPath().getMinimumTravelTime(), test_Dijkstra77.getPath().getMinimumTravelTime(), 0.0);
		assertEquals(test_Bellman44.getPath().getMinimumTravelTime(), test_Astar77.getPath().getMinimumTravelTime(), 0.0);
		
		//QUE LES VOITURES
		assertEquals(test_Bellman55.getPath().getMinimumTravelTime(), test_Dijkstra88.getPath().getMinimumTravelTime(), 0.0);
		assertEquals(test_Bellman55.getPath().getMinimumTravelTime(), test_Astar88.getPath().getMinimumTravelTime(), 0.0);
	}
	
	@Test
	public void difference_chemin_entre_differents_moyens_de_transport_INSA() throws Exception {
		
		//Différence en distance entre piéton et voiture
		assertFalse(test_Bellman4.getPath().getLength() == test_Bellman5.getPath().getLength());
		assertTrue(test_Bellman4.getPath().getLength() == test_Dijkstra7.getPath().getLength());
		assertFalse(test_Bellman4.getPath().getLength() == test_Dijkstra8.getPath().getLength());
		assertTrue(test_Bellman4.getPath().getLength() == test_Astar7.getPath().getLength());
		assertFalse(test_Bellman4.getPath().getLength() == test_Astar8.getPath().getLength());
		
		//Différence en temps entre piéton et voiture
		assertFalse(test_Bellman44.getPath().getMinimumTravelTime() == test_Bellman55.getPath().getMinimumTravelTime());
		assertTrue(test_Bellman44.getPath().getMinimumTravelTime() == test_Dijkstra77.getPath().getMinimumTravelTime());
		assertFalse(test_Bellman44.getPath().getMinimumTravelTime() == test_Dijkstra88.getPath().getMinimumTravelTime());
		assertTrue(test_Bellman44.getPath().getMinimumTravelTime() == test_Astar77.getPath().getMinimumTravelTime());
		assertFalse(test_Bellman44.getPath().getMinimumTravelTime() == test_Astar88.getPath().getMinimumTravelTime());
	}
	
	@Test
    public void test_chemin_faisable_sans_oracle_Bretagne() throws Exception { 	
        
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
