package policies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

import requests.Request;

public class ApplicationPriority extends Policy{


	public ApplicationPriority(ArrayList<Request> request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationPriority() {
		super();
	}
	
	
	
	
	
	
	// Methods
	
	
	
	public ArrayList<Vector> sortVectorPragma(ArrayList<Vector> vector) {
		ArrayList<Vector> pragmaList = new ArrayList<Vector>();
		pragmaList.add(vector.get(0));
		for(int index=1;  index < vector.size(); index++) {
			for(int i=0;  i < pragmaList.size(); i++) {
				if((int) vector.get(index).get(4) >  (int)pragmaList.get(i).get(4)) {
					pragmaList.add(i, vector.get(index));
					break;
				}
			}
			if(pragmaList.size() != index+1) {
				pragmaList.add(vector.get(index));
			}
		}
		return pragmaList;
	}
	
	
	
	
	
	public String getPolicyName(){
		return "Application Priority";
	}


	@Override
	public LinkedList<Request> getScheduled(LinkedList<Request> queue) {
		// TODO Auto-generated method stub
		return queue;
	}

	
	

}
