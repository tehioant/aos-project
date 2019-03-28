package requests;

public class DR extends Request{

	public DR(int requestType, int payload, boolean scheduled, long time, int appId) {
		super(requestType, payload, scheduled, time, appId);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public DR(){
		this(0, 0, false, 0, 0);
	}
	
	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
