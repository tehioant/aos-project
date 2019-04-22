package dispatcher.model;

import java.util.Calendar;

import requests.Request;

public class ProcessBuffer {
	
	
	public Request request;
	public long startTime;
	public float remainingTime;
	
	
	
	
	public ProcessBuffer(Request request, long startTime) {
		this.request = request;
		this.startTime = startTime;
	}




	/**
	 * @return the requests
	 */
	public Request getRequest() {
		return request;
	}




	/**
	 * @param requests the requests to set
	 */
	public void setRequest(Request requests) {
		this.request = requests;
	}




	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}




	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}




	/**
	 * @return the remainingTime
	 */
	public float getRemainingTime() {
		Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
    	return this.getRequest().getCompletionTime() - (time - this.getStartTime());
	}




	/**
	 * @param remainingTime the remainingTime to set
	 */
	public void setRemainingTime(float remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	
	
	
	

}
