package policies;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import requests.Request;

public class MinStallTime extends Policy{

	public Date startTime;
	
	
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
		Calendar calendar = Calendar.getInstance();
		startTime = calendar.getTime();
		
		
		
		return null;
	}
	

	// TODO 
	public void costFunction(LinkedList<Request> queue){
		int cost = 0;
		for(Request req : queue){
			cost += (req.getStallTime());
		}
	}
	
	
	
	
	
	
	
	
	
	
}
