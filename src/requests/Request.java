package requests;



import java.io.Serializable;





public abstract class Request implements Serializable, Comparable{
	
	
	private static final long serialVersionUID = 1L;
	public int requestType;
	public int payload;
	public boolean scheduled;
	
	
	public Request(int requestType, int payload, boolean scheduled){
		this.requestType = requestType;
		this.payload = payload;
		this.scheduled = scheduled;
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
	
	
	
	
}





