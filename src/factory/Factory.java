package factory;

import java.util.PriorityQueue;

import policies.ApplicationPriority;
import policies.Policy;
import requests.Request;
import solver.Solver;

public class Factory {

	
	
	
	
	
	private static final int MAX_REQUEST = 100;
	private static final double TIMER = 10;
	
	public static Solver solver = Solver.getInstance();
	public static Policy policy;
	
	
	
	public static PriorityQueue<Request> getResponse(PriorityQueue<Request> requests){
		
		System.out.println("Request : "+ requests); 
		solver.setPolicy("ApplicationPriority");
		policy = solver.getPolicy();
		System.out.println("Policy : " + policy.getPolicyName()); 
		return policy.getScheduled(requests);

	}
	
}
