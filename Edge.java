
public class Edge {
	private Vertex startVertex;
	//private String startVertexName;
	private Vertex endVertex;
	//private String endVertexName;
	private double edgeWeight;
	
	public Edge(String startVertexName, String endVertexName, double weight){
		edgeWeight = weight;
		/*this.startVertexName = startVertexName;
		this.endVertexName = endVertexName;*/
		setStartVertex(new Vertex(startVertexName, false)); 
		setEndVertex(new Vertex(endVertexName, false));
		
	}

	public String toString(){
		String s = "";
		return s + startVertex.getVertexName() + " " + endVertex.getVertexName() + " " + edgeWeight;
	}
	public String getStartVertexName() {
		return startVertex.getVertexName();
	}

	public void setStartVertexName(String startVertexName) {
		startVertex.setVertexName(startVertexName);
	}


	public String getEndVertexName() {
		return endVertex.getVertexName();
	}

	public void setEndVertexName(String endVertexName) {
		endVertex.setVertexName(endVertexName);
	}

	public double getEdgeWeight() {
		return edgeWeight;
	}

	public void setEdgeWeight(double edgeWeight) {
		this.edgeWeight = edgeWeight;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
	}

	public Vertex getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(Vertex endVertex) {
		this.endVertex = endVertex;
	}

}
