package policies;

import java.util.ArrayList;

import model.Pragma;

public class ApplicationPriority extends Policy{

	
	public ApplicationPriority(ArrayList<Pragma> pragmas) {
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
	
	public String getPolicyName(){
		return "Application Priority";
	}
	

}
