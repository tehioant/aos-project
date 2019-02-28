package requests;

public abstract class Request {
	
	
	public Enum requestType;
	public String payload;
	
	
	
	public Request(Enum requestType, String payload){
		this.requestType = requestType;
		this.payload = payload;
	}


	
	
	
	
	

}
