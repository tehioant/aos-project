package requests;



import java.io.Serializable;





public abstract class Request implements Serializable, Comparable{
	
	
	private static final long serialVersionUID = 1L;
	public int priority;
	public int payload;
	public boolean scheduled;
	public long completionTime;
	public long stallTime;
	
	
	
	
	public Request(int priority, int payload, boolean scheduled, long completionTime){
		this.priority = priority;
		this.payload = payload;
		this.scheduled = scheduled;
		this.completionTime = completionTime;
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
		return completionTime;
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
		return stallTime;
	}




	/**
	 * @param stallTime the stallTime to set
	 */
	public void setStallTime(long stallTime) {
		this.stallTime = stallTime;
	}
	
	
	
	
}





