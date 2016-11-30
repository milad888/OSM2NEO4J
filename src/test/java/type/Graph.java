package type;

import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Node> gnode=new ArrayList<Node>();
	private ArrayList<Way> gway=new ArrayList<Way>();
	
	
	public ArrayList<Node> getGnode() {
		return gnode;
	}
	public void setGnode(ArrayList<Node> gnode) {
		this.gnode = gnode;
	}
	public ArrayList<Way> getGway() {
		return gway;
	}
	public void setGway(ArrayList<Way> gway) {
		this.gway = gway;
	}
	

}
