package requests;



import java.io.Serializable;





public abstract class Request implements Serializable, Comparable{
	
	
	private static final long serialVersionUID = 1L;
	public int priority;
	public int payload;
	public boolean scheduled;
	public long completionTime;
	public long stallTime;
	public int appId;
	public long startTime;
	
	
	
	public Request(int priority, int payload, boolean scheduled, long completionTime, int appId){
		this.priority = priority;
		this.payload = payload;
		this.scheduled = scheduled;
		this.completionTime = completionTime;
		this.appId = appId;
	}

	public Request(){
		this(0, 0, false, 0, 0);
	}
	
	
	public int getPriority(){
		return this.priority;
	}
	
	
	
	public int getPayload(){
		return this.payload;
	}

	
	
	public String toString(){
		return  this.getPriority() + " / " + this.getPayload() + " / " + this.getCompletionTime() + " / " + this.getScheduled();
	}
	
	
	public void setScheduled(boolean s){
		this.scheduled = s;
	}
	
	
	public boolean getScheduled(){
		return this.scheduled;
	}


	/**
	 * @return the completionTime
	 */
	public long getCompletionTime() {
		return this.completionTime;
	}


	/**
	 * @param completionTime the completionTime to set
	 */
	public void setCompletionTime(long completionTime) {
		this.completionTime = completionTime;
	}




	/**
	 * @return the stallTime
	 */
	public long getStallTime() {
		return this.stallTime;
	}




	/**
	 * @param stallTime the stallTime to set
	 */
	public void setStallTime(long stallTime) {
		this.stallTime = stallTime;
	}




	/**
	 * @return the id
	 */
	public int getAppId() {
		return appId;
	}




	/**
	 * @param id the id to set
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	
	
	
	/**
	 * @return the id
	 */
	public long getStartTime() {
		return this.startTime;
	}




	/**
	 * @param id the id to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	
}





