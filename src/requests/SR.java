package requests;

public class SR extends Request{

	public SR(int requestType, int payload, boolean scheduled, long time, int appId) {
		super(requestType, payload, scheduled, time, appId);
	}

	public SR(){
		this(0, 0, false, 0, 0);
	}
	
	
	
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
