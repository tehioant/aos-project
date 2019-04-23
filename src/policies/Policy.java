package policies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

import dispatcher.DispatcherInterface;
import requests.Request;
import solver.ProcessSolver;

public abstract class Policy {
	
	
	public static final String DEFAULT_MODE = "ApplicationPriority";
	
	public LinkedList<Request> requests;
	public int buffers;
	public DispatcherInterface dispInterface;
	
	public Policy(DispatcherInterface dispInterface, LinkedList<Request> requests) {
		this.requests = requests;
	}
	
	
	public Policy() {
		this(null, null);
	}
	
	
	
	/**
	 * @return the requests
	 */
	public LinkedList<Request> getRequests() {
		return requests;
	}
	/**
	 * @param requests the requests to set
	 */
	public void setRequests(LinkedList<Request> requests) {
		this.requests = requests;
	}
	


	/**
	 * @return the buffers
	 */
	public int getBuffers() {
		return buffers;
	}


	/**
	 * @param buffers the buffers to set
	 */
	public void setBuffers(int buffers) {
		this.buffers = buffers;
	}


	public abstract String getPolicyName();
	
	// momentary method
	public abstract ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue);
	
	public abstract long costFunction(ArrayList<ProcessSolver> scheduled);


	/**
	 * @return the dispInterface
	 */
	public DispatcherInterface getDispInterface() {
		return dispInterface;
	}


	/**
	 * @param dispInterface the dispInterface to set
	 */
	public void setDispInterface(DispatcherInterface dispInterface) {
		this.dispInterface = dispInterface;
	}

}
