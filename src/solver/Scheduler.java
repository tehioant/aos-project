package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

import dispatcher.DispatcherInterface;
import policies.ApplicationPriority;
import policies.Policy;
import requests.Request;

public class Scheduler {

	
	
	
	
	
	private static final int MAX_REQUEST = 100;
	private static final double TIMER = 10;
	
	public static Solver solver = Solver.getInstance();
	
	public static Policy policy;
	public static ArrayList<ProcessSolver> schedule;
	public static DispatcherInterface dispInterface = new DispatcherInterface();
	public static String POLICY_TYPE = "MaxBufferEfficiency"; //"MaxApplicationBandwidth";
	
	
	
	public synchronized static ArrayList<ProcessSolver> getResponse(LinkedList<Request> queue){
		
		//System.out.println("Request : "+ queue); 
		
		// Prepare Policy
		solver.setPolicy(POLICY_TYPE, queue);
		policy = solver.getPolicy();
		//System.out.println("Policy : " + policy.getPolicyName()); 
		
		// Schedule requests
		policy.setDispInterface(dispInterface);
		
		//System.out.println("Scheduler;Scheduling with : " + policy.getPolicyName()); 
		schedule = policy.getScheduled(queue);
		


		return schedule;
	}
	
	
	public static Solver getSolver(){
		return Scheduler.solver;
	}
	
	
	public static DispatcherInterface getDispatcherInterface(){
		return Scheduler.dispInterface;
	}
	
	
}








