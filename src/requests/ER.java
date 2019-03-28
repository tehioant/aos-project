package requests;

public class ER extends Request{

	
	
	
	public ER(int requestType, int payload, boolean scheduled, long time, int appId) {
		super(requestType, payload, scheduled, time, appId);
	}
	
	
	public ER(){
		this(0, 0, false, 0, 0);
	}
	
	
	
	
	
	
	
	

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	
	
	
	
	
	
	
}
