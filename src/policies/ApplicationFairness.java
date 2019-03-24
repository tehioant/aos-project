package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import requests.Request;

public class ApplicationFairness extends Policy{
	
	
	
	public ApplicationFairness(LinkedList<Request> request){
		super(request);
	}
	
	public ApplicationFairness() {
		super();
	}

	@Override
	public String getPolicyName() {
		return "ApplicationFairness";
	}

	@Override
	public ArrayList<Request> getScheduled(LinkedList<Request> queue) {
		return null;
	}
	
	
	public void costFunction(){
		
	}
	
	

}
