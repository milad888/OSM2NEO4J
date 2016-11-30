package type;

import java.util.ArrayList;

public class Way {
	private String eName;
	private boolean eType;
	private ArrayList<Node> wnode=new ArrayList<Node>();
	private String eDistance;
	
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public boolean iseType() {
		return eType;
	}
	public void seteType(boolean eType) {
		this.eType = eType;
	}

	public String geteDistance() {
		return eDistance;
	}
	public void seteDistance(String eDistance) {
		this.eDistance = eDistance;
	}
	public ArrayList<Node> getNode() {
		return wnode;
	}
	public void setNode(ArrayList<Node> node) {
		this.wnode = node;
	}
	
	
}
