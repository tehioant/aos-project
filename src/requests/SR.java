package requests;

public class SR extends Request{

	public SR(int requestType, int payload, boolean scheduled, long time) {
		super(requestType, payload, scheduled, time);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
