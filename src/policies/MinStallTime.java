package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import requests.Request;

public class MinStallTime extends Policy{

	
	
	
	public MinStallTime(LinkedList<Request> request){
		super(request);
	}
	
	public MinStallTime() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MinStallTime";
	}

	@Override
	public ArrayList<Request> getScheduled(LinkedList<Request> queue) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
