package policies;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Vector;


import requests.Request;

public abstract class Policy {
	
	
	public static final String DEFAULT_MODE = "ApplicationPriority";
	
	public ArrayList<Request> requests;


	public Policy(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	
	public Policy() {
		this(null);
	}
	
	
	
	
	
	
	/**
	 * @return the requests
	 */
	public ArrayList<Request> getRequests() {
		return requests;
	}
	/**
	 * @param requests the requests to set
	 */
	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	
	
	
	
	
	public abstract ArrayList<Vector> sortVectorPragma(ArrayList<Vector> vector);
	public abstract String getPolicyName();
	
	// momentary method
	public abstract PriorityQueue<Request> getScheduled(PriorityQueue<Request> request);

}
