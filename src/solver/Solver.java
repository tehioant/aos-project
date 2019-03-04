package solver;


import policies.ApplicationPriority;
import policies.Policy;
import java.util.Queue;
import javax.ejb.Singleton;



public class Solver{
	
	private static Solver instance=null;
	
	
	public Queue queue;
	public Policy policy;
	public double ressources;
	
	
	
	private Solver(Queue queue, Policy policy, double ressources){
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
	

	
	
	
	public Queue getQueue(){
		return this.queue;
	}
	
	public void setQueue(Queue q){
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








