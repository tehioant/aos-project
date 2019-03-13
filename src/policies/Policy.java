package policies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;


import requests.Request;

public abstract class Policy {
	
	
	public static final String DEFAULT_MODE = "ApplicationPriority";
	
	public LinkedList<Request> requests;


	
	public Policy(LinkedList<Request> requests) {
		this.requests = requests;
	}
	
	
	public Policy() {
		this(null);
	}
	
	
	
	/**
	 * @return the requests
	 */
	public LinkedList<Request> getRequests() {
		return requests;
	}
	/**
	 * @param requests the requests to set
	 */
	public void setRequests(LinkedList<Request> requests) {
		this.requests = requests;
	}
	
	
	
	
	
	public abstract String getPolicyName();
	
	// momentary method
	public abstract ArrayList<Request> getScheduled(LinkedList<Request> queue);

}
