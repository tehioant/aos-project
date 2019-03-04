package requests;



import java.io.Serializable;





public abstract class Request implements Serializable{
	
	
	public int requestType;
	public String payload;
	
	
	
	public Request(int requestType, String payload){
		this.requestType = requestType;
		this.payload = payload;
	}


	
	
	public int getRequestType(){
		return this.requestType;
	}
	
	public String getPayload(){
		return this.payload;
	}

	
	
	public String toString(){
		return  this.getRequestType() + " / " + this.getPayload();
	}
	
}
