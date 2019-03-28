package requests;

public class RR extends Request{

	
	
	
	
	
	public RR(int requestType, int payload, boolean scheduled, long time, int appId) {
		super(requestType, payload, scheduled, time, appId);
	}
	
	
	public RR(){
		this(0, 0, false, 0, 0);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
}
