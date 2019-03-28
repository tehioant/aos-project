package policies;

import java.util.ArrayList;
import java.util.LinkedList;

import dispatcher.DispatcherInterface;
import requests.Request;
import solver.ProcessSolver;

public class MaxApplicationBandwidth extends Policy{

	
	
	
	public MaxApplicationBandwidth(DispatcherInterface dispInterface, LinkedList<Request> queue){
		super(dispInterface, queue);
	}
	
	public MaxApplicationBandwidth() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MaxApplicationBandwidth";
	}

	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {


		
		
		return null;
	}

}
