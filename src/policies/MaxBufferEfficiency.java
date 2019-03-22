package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import requests.Request;

public class MaxBufferEfficiency extends Policy{

	
	
	
	public MaxBufferEfficiency(LinkedList<Request> request){
		super(request);
	}
	
	public MaxBufferEfficiency() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MaxBufferEfficiency";
	}

	@Override
	public ArrayList<Request> getScheduled(LinkedList<Request> queue) {
		// TODO Auto-generated method stub
		return null;
	}

}
