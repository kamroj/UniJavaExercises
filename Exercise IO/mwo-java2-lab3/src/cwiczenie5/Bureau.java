package cwiczenie5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bureau implements Serializable {

	private String name;
	private List<Agent> agents = new ArrayList<Agent>();
	
	public Bureau(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addAgent(Agent agent) {
		agents.add(agent);
	}

	public List<Agent> getAgents() {
		return agents;
	}
	
	
}
