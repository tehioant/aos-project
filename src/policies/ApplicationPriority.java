package policies;

import java.util.ArrayList;
import java.util.Vector;

import model.Pragma;

public class ApplicationPriority extends Policy{

	
	public ApplicationPriority(ArrayList<Pragma> pragmas) {
		super(pragmas);
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationPriority(Vector pragmas) {
		super(pragmas);
		// TODO Auto-generated constructor stub
	}

	public ApplicationPriority() {
		super();
	}
	
	
	
	
	
	
	// Methods
	
	
	public ArrayList<Pragma> sortPragmas(ArrayList<Pragma> array){
		ArrayList<Pragma> pragmaList = new ArrayList<Pragma>();
		pragmaList.add(array.get(0));
		for(int index=1;  index < array.size(); index++) {
			for(int i=0;  i < pragmaList.size(); i++) {
				if(array.get(index).getPriority() >  pragmaList.get(i).getPriority()) {
					pragmaList.add(i, array.get(index));
					break;
				}
			}
			if(pragmaList.size() != index+1) {
				pragmaList.add(array.get(index));
			}
		}
		return pragmaList;
	}
	
	
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

	
	

}
