package requests;

public class RR extends Request{

	
	
	
	
	
	public RR(int priority, int payload, boolean scheduled, long time, int appId) {
		super(priority, payload, scheduled, time, appId);
	}
	
	
	public RR(){
		this(0, 0, false, 0, 0);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	
	
	
	
}
