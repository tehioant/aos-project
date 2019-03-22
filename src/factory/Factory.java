package factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import dispatcher.DispatcherInterface;
import policies.ApplicationPriority;
import policies.Policy;
import requests.Request;
import solver.Solver;

public class Factory {

	
	
	
	
	
	private static final int MAX_REQUEST = 100;
	private static final double TIMER = 10;
	
	public static Solver solver = Solver.getInstance();
	
	public static Policy policy;
	public static ArrayList<Request> schedule;
	public static DispatcherInterface disp = new DispatcherInterface();
	public static String POLICY_TYPE = "ApplicationPriority";
	
	
	
	public static ArrayList<Request> getResponse(LinkedList<Request> queue){
		
		System.out.println("Request : "+ queue); 
		
		// Prepare Policy
		solver.setPolicy(POLICY_TYPE);
		policy = solver.getPolicy();
		System.out.println("Policy : " + policy.getPolicyName()); 
		
		// Schedule requests
		schedule = policy.getScheduled(queue);
		
		// Send schedule to dispatcher
		allocateRessources(schedule);

		return schedule;
	}
	
	
	
	
	public static void allocateRessources(ArrayList<Request> schedule){
		for(Request item : schedule){
			disp.processRequest(item);
		}
	}
	
	
	
	
	public void buildResponse(){
		
	}
	
	
	
	
	public void updateQueue(){
		
	}
	
	
	
	
}








