package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import requests.Request;

public class MaxApplicationBandwidth extends Policy{

	
	
	
	public MaxApplicationBandwidth(LinkedList<Request> request){
		super(request);
	}
	
	public MaxApplicationBandwidth() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MaxApplicationBandwidth";
	}

	@Override
	public ArrayList<Request> getScheduled(LinkedList<Request> queue) {
		// TODO Auto-generated method stub
		return null;
	}

}
