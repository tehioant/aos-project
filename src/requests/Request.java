package requests;



import java.io.Serializable;





public abstract class Request implements Serializable, Comparable{
	
	
	private static final long serialVersionUID = 1L;
	public int requestType;
	public int payload;
	public boolean scheduled;
	public long time;
	
	
	public Request(int requestType, int payload, boolean scheduled, long time){
		this.requestType = requestType;
		this.payload = payload;
		this.scheduled = scheduled;
		this.time = time;
	}


	
	
	public int getRequestType(){
		return this.requestType;
	}
	
	
	
	public int getPayload(){
		return this.payload;
	}

	
	
	public String toString(){
		return  this.getRequestType() + " / " + this.getPayload();
	}
	
	
	public void setScheduled(boolean s){
		this.scheduled = s;
	}
	
	
	public boolean getScheduled(){
		return this.scheduled;
	}




	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}




	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	
	
	
}





