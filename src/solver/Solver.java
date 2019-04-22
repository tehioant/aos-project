package solver;


import policies.ApplicationFairness;
import policies.ApplicationPriority;
import policies.MaxApplicationBandwidth;
import policies.MaxBufferEfficiency;
import policies.MinStallTime;
import policies.Policy;
import requests.Request;

import java.util.LinkedList;
import javax.ejb.Singleton;



public class Solver{
	
	private static Solver instance=null;
	
	
	public LinkedList<Request> queue;
	public Policy policy;
	
	
	
	private Solver(LinkedList<Request> queue, Policy policy){
		this.queue = queue;
		this.policy = policy;
	}
	
	
	private Solver(){
		this(null, null);
	}
	
	

	
	public synchronized static Solver getInstance(){
		if (instance == null){
			synchronized(Solver.class){
				instance = new Solver(); 
			}
		}
        return instance;
	}
	

	
	
	
	public LinkedList<Request> getQueue(){
		return this.queue;
	}
	
	public void setQueue(LinkedList<Request> q){
		this.queue = q;
	}
	
	
	public Policy getPolicy(){
		return this.policy;
	}
	
	public void setPolicy(String policy, LinkedList<Request> queue){
		switch (policy) {
			case "ApplicationPriority":
				this.policy = new ApplicationPriority(null, queue);
				break;
			case "ApplicationFairness":
				this.policy = new ApplicationFairness(null, queue);
				break;
			case "MaxApplicationBandwidth":
				this.policy = new MaxApplicationBandwidth(null, queue);
				break;
			case "MaxBufferEfficiency":
				this.policy = new MaxBufferEfficiency(null, queue);
				break;
			case "MinStallTime":
				this.policy = new MinStallTime(null, queue);
				break;
		}
			
	}
	
	
	
}








