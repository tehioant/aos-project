package requests;

public class ER extends Request{

	
	
	
	public ER(int requestType, int payload, boolean scheduled, long time) {
		super(requestType, payload, scheduled, time);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	
	
	
	
	
	
	
}
