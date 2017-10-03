import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private Vertex[] path;
	private double optimalDistance;
	
	public Graph(){
		vertexList = new ArrayList<Vertex>();
		edgeList = new ArrayList<Edge>();
	}
	public boolean loadData(String filename){
		Scanner inFile;
		try{
			File inputFile = new File(filename);
			inFile = new Scanner(inputFile);
		}
		catch(FileNotFoundException e){
			System.out.println("Error 404: File not found...");
			return false;
		}
		while(inFile.hasNext()){
			String startVertex = inFile.next();
			String endVertex = inFile.next();
			String edgeWeight = inFile.next();
			inFile.nextLine();
			Edge edge = new Edge(startVertex,endVertex,Double.parseDouble(edgeWeight));
			edgeList.add(edge);
		}
		// Call initialize vertices method
		initializeVetices();
		
		inFile.close();
		return true;
	}
	// Sort through the edgeList to pick up vertices' name and initialize vertices
	public void initializeVetices(){ 
		
		ArrayList<String> dataPoints = new ArrayList<>();
		// Store all the vertices' name to array called dataPoints including the duplicate names
		for(int i = 1; i < edgeList.size(); i++){
			String s1 = edgeList.get(i).getStartVertexName(); 
			String s2 = edgeList.get(i).getEndVertexName();
				dataPoints.add(s1);
				dataPoints.add(s2);
		}
		
		dataPoints.trimToSize(); // Trim the array to its size
		
		/*Sort through the dataPoints, keep one vertex and exclude all of its duplicates. 
		Store the vertices in the new array*/
		ArrayList<String> vertices = new ArrayList<>();
		int index = 0;
		while(index < dataPoints.size()){
			int index2 = index;
			int count = 0;
			String s3 = dataPoints.get(index);
			while(index2 < dataPoints.size()){
				if(s3.equals(dataPoints.get(index2)))
					count++;
				index2++;
			}
			
			if(count == 1)
				vertices.add(s3);
			index++;
		}
		
		// Initialize vertices
		vertices.trimToSize();
		for(int j = 0; j < vertices.size(); j++){
			vertexList.add( new Vertex(vertices.get(j),false) );
		}
		vertexList.trimToSize();
		
	}
	
	// Apply Dijkstra's algorithm to find the optimal path
	public void findPath(String currentPos, String destination){
		Vertex lowest = new Vertex(); // Create a vertex for the vertex with the lowest distance
		
		// find the vertex, which match to the current position in the vertex list
		for(Vertex v:vertexList){ // Run through the vertex list 
			if( currentPos.equals(v.getVertexName()) ){ // If found the vertex
				v.setDistance(0); // Set its distance to 0
				v.setVisited(true); // set it as visited
				lowest = v; // and assign it to the lowest vertex
			}
		}
		
		// while the lowest has not reach the final destination node
		while(!lowest.getVertexName().equals(destination)){
			lowest = branchOut(lowest); // call the branchOut method to branch out
										// and pass the current lowest vertex
			lowest.setVisited(true);
		}
		
		path = new Vertex[vertexList.size()];
		path[0] = lowest;
		// Set optimal distance to the distance of the destination node
		optimalDistance = lowest.getDistance();
		
		int index = 1;
		while(!lowest.getPreviousVertex().getVertexName().equals(currentPos) ){
			path[index] = lowest.getPreviousVertex();
			//if(lowest.getPreviousVertex() != null)
				lowest = lowest.getPreviousVertex();
			index++;
		}
		path[index] =  lowest.getPreviousVertex();
	}
	
	public Vertex branchOut(Vertex l){
		
		Vertex lowest = new Vertex();
		
		// Look down the edge list and see if there is a start vertex match the vertex l's name
		for(Edge e:edgeList){
			if(l.getVertexName().equals(e.getStartVertexName())) // if find match
				for(Vertex v:vertexList) // look for next vertices, which is next to the l vertex and not visited
					if( e.getEndVertexName().equals(v.getVertexName()) && v.getVisited() != true){
							// Set distance of that vertex to the distance of the vertex before it 
						    // add with the weight between two vertices
							v.setDistance( l.getDistance() + e.getEdgeWeight() );
							// Pass the distance of this vertex to the setPreviousVertex to check
							// if this is optimal path. If true set l vertex to the previous vertex of this
							// if not an optimal path then do not thing
							v.setPreviousVertex(l, l.getDistance() + e.getEdgeWeight());
							lowest = v;
					}
		}
		
		
		//lowest = vertexList.get(0);
		// Compare all the distance of the vertices in the vertex list and find the 
		// one with the least distance
		for(Vertex v:vertexList){
			if(v.getVisited() != true) // this if statement makes sure that do not cross the visited vertex
				if(v.getDistance() <= lowest.getDistance() && v.getDistance() != Double.POSITIVE_INFINITY)
					lowest = v; // Assign the lowest vertex to the one with the lowest distance
		}
		//System.out.println(lowest.getVertexName());
		return lowest;
	}
	public String toString(){
		String s = "";
		
		for(Edge t:edgeList){
			//if(t != null)
				s = s + t.toString() + "\n";
		}
		
		s += "\n***********************************\n";
		s += "Current Location: ";
		for(int i = path.length -1; i >=0; i--){
			if(path[i] != null)
			s += path[i].getVertexName() + " -> ";
		}
		s = s + "Destination\n";
		s += "Total distance = " + optimalDistance;
		return s;
		
	}
	
	public String printMap(){
		String s = "";
		
		for(Edge t:edgeList){
			//if(t != null)
				s = s + t.toString() + "\n";
		}
		s += "\n*****Please Choose Destination*****\n";
		return s;
	}
	
	public static void main(String[] args){
		Graph graph = new Graph();
		boolean load = graph.loadData("MapData.txt");
		graph.findPath("B", "F");
		System.out.println(graph);
		/* load = graph.loadData("MapData.txt");
		graph.findPath("C", "D");
		System.out.println(graph);*/
	}
}
