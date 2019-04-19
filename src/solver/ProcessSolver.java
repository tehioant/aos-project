package solver;

import requests.Request;

public class ProcessSolver {
	
	
	public Request request;
	public int bufferId;
	
	public ProcessSolver(Request request, int bufferId){
		this.request = request;
		this.bufferId = bufferId;
	}

	
	public ProcessSolver(){
		this(null, 0);
	}
	
	
	
	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the bufferId
	 */
	public int getBufferId() {
		return bufferId;
	}

	/**
	 * @param bufferId the bufferId to set
	 */
	public void setBufferId(int bufferId) {
		this.bufferId = bufferId;
	}
	
	
	
	

}
