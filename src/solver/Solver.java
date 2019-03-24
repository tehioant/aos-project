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
		if (instance == null) 
			instance = new Solver(); 
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
	
	public void setPolicy(String policy){
		switch (policy) {
			case "ApplicationPriority":
				this.policy = new ApplicationPriority();
				break;
			case "ApplicationFairness":
				this.policy = new ApplicationFairness();
				break;
			case "MaxApplicationBandwidth":
				this.policy = new MaxApplicationBandwidth();
				break;
			case "MaxBufferEfficiency":
				this.policy = new MaxBufferEfficiency();
				break;
			case "MinStallTime":
				this.policy = new MinStallTime();
				break;
		}
			
	}
	
	
	
}








