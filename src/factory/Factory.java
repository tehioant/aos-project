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
	
	
	public static ArrayList<Request> getResponse(LinkedList<Request> queue){
		
		System.out.println("Request : "+ queue); 
		solver.setPolicy("ApplicationPriority");
		policy = solver.getPolicy();
		System.out.println("Policy : " + policy.getPolicyName()); 
		schedule = policy.getScheduled(queue);
		for(Request item : schedule){
			while(true){
				if(disp.getRessourcesDispatcher(item.payload))
					break;
				else
					continue;
			}
		}
		return schedule;
	}
	
	
	
	
}








