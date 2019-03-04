package factory;

import java.util.LinkedList;
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
	
	
	
	public static LinkedList<Request> getResponse(LinkedList<Request> queue){
		
		System.out.println("Request : "+ queue); 
		solver.setPolicy("ApplicationPriority");
		policy = solver.getPolicy();
		System.out.println("Policy : " + policy.getPolicyName()); 
		return policy.getScheduled(queue);

	}
	
}
