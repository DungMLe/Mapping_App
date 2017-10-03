
public class Vertex {
	private String vertexName;
	private double distance;
	private boolean isVisited;
	private Vertex previousVertex;
	
	public Vertex(){
		setVertexName("");
		distance = Double.POSITIVE_INFINITY;
		isVisited = false;
	}
	
	public Vertex(String vertex, boolean isVisited){
		setVertexName(vertex);
		distance = Double.POSITIVE_INFINITY;
		this.isVisited = isVisited;
	}
	
	public void setPreviousVertex(Vertex v, double distance){
		if(this.distance >= distance) // Is the path optimal if true then set 
			previousVertex = v;
	}
	
	public Vertex getPreviousVertex(){
		return previousVertex;
	}
	
	public void setDistance(double d){
		if(d <= distance)
			distance = d;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public void setVisited(boolean visited){
		isVisited = visited;
	}
	
	public boolean getVisited(){
		return isVisited;
	}

	public String getVertexName() {
		return vertexName;
	}

	public void setVertexName(String vertexName) {
		this.vertexName = vertexName;
	}
	
	
	
}