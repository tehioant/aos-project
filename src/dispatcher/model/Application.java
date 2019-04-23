package dispatcher.model;

import java.util.ArrayList;

import requests.Request;

public class Application {
	
	
	public ArrayList<Request> listRequest;
	public int appId;
	public long completionTime;
	public long appPayload;
	public long stallTime;
	
	
	public Application(ArrayList<Request> listRequest, int appId){
		this.listRequest = listRequest;
		this.appId = appId;
		this.completionTime = 0;
		this.appPayload = 0;
		this.stallTime = 0;
		if(this.listRequest == null){
			this.listRequest = new ArrayList<Request>();
		}
	}
	
	
	public Application(){
		this(null, 0);
	}

	
	

	/**
	 * @return the request
	 */
	public ArrayList<Request> getListRequest() {
		return this.listRequest;
	}


	/**
	 * @param request the request to set
	 */
	public void setListRequest(ArrayList<Request> request) {
		this.listRequest = request;
	}

	public void addRequest(Request request){
		this.getListRequest().add(request);
	}
	
	

	/**
	 * @return the appId
	 */
	public int getAppId() {
		return appId;
	}


	/**
	 * @param appId the appId to set
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	
	/**
	 * @return the stallTime
	 */
	public long getStallTime() {
		return stallTime;
	}


	/**
	 * @param stallTime the stallTime to set
	 */
	public void setStallTime(long stallTime) {
		this.stallTime = stallTime;
	}
	
	
	
	public void updateStallTime(long time){
		this.stallTime += time;
	}
	


	public long getAppCompTime(){
		if(this.completionTime == 0){
			for(Request request : this.getListRequest()){
				this.completionTime += request.getCompletionTime();
			}
			return this.completionTime;
		} else {
			return this.completionTime;
		}
	}
	
	
	
	
	public long getAppPayload(){
		if(this.appPayload == 0){
			for(Request request : this.getListRequest()){
				this.appPayload += request.getPayload();
			}
			return this.appPayload;
		} else {
			return this.appPayload;
		}
	}
	
	
	
	
	
	
	

}
