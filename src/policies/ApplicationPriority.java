package policies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

import dispatcher.DispatcherInterface;
import requests.Request;
import solver.ProcessSolver;

public class ApplicationPriority extends Policy{


	public ApplicationPriority(DispatcherInterface dispInterface, LinkedList<Request> request) {
		super(dispInterface, request);
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationPriority() {
		super();
	}
	
	
	
	
	
	
	// Methods
	public String getPolicyName(){
		return "Application Priority";
	}


	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> schedule = new ArrayList<ProcessSolver>();
		schedule.add(new ProcessSolver(queue.poll(), 0));
		Request poll;
		while(queue.size() > 0){
			poll = queue.poll();
			boolean gotIN = false;
			for(int index=0;  index < schedule.size(); index++) {
				if(poll.getPriority() > schedule.get(index).getRequest().getPriority()){
					for(int num=0; num <  super.getDispInterface().getBuffers().size(); num++){
						if(super.getDispInterface().getBuffers().get(num).getCurrentRessources() > poll.getPayload()){
							schedule.add(new ProcessSolver(poll, num));
							gotIN = true;
							break;
						}
					}
				}
			}
			if(!gotIN){
				for(int num=0; num <  super.getDispInterface().getBuffers().size(); num++){
					if(super.getDispInterface().getBuffers().get(super.getDispInterface().getBuffers().size()-1).getCurrentRessources() > poll.getPayload()){
						schedule.add(new ProcessSolver(poll, super.getDispInterface().getBuffers().size()));
					}
				}
			}
		}
		return schedule;
	}

	

	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		return 0;
	}

	
	
	
	
	
	

}
