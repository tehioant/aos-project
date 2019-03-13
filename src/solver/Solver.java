package solver;


import policies.ApplicationPriority;
import policies.Policy;
import requests.Request;

import java.util.LinkedList;
import javax.ejb.Singleton;



public class Solver{
	
	private static Solver instance=null;
	
	
	public LinkedList<Request> queue;
	public Policy policy;
	public double ressources;
	
	
	
	private Solver(LinkedList<Request> queue, Policy policy, double ressources){
		this.queue = queue;
		this.policy = policy;
		this.ressources = ressources;
	}
	
	
	private Solver(){
		this(null, null, 0);
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
		}
			
	}
	
	public double getRessources(){
		return (Double) null;
	}
	
	public void setRessources(double r){
		this.ressources = r;
	}
	
	
	
}








