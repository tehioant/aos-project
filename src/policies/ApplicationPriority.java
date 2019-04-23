package policies;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import dispatcher.DispatcherInterface;
import dispatcher.model.Application;
import dispatcher.model.Buffer;
import requests.Request;
import solver.ProcessSolver;

public class ApplicationPriority extends Policy{

	
	public ArrayList<Application> appList;

	public ApplicationPriority(DispatcherInterface dispInterface, LinkedList<Request> request) {
		super(dispInterface, request);
		//this.appList = this.getAllApps(request);
		//System.out.println("-------------applist size : " + appList.size() + " / " + request.size());
	}
	
	public ApplicationPriority() {
		super();
	}
	
	
	
	
	
	
	// Methods
	public String getPolicyName(){
		return "ApplicationPriority";
	}


	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> schedule = new ArrayList<ProcessSolver>();
		for(Request request : queue){
			for(int a=0; a < schedule.size(); a++){
				if(request.getAppId() >= schedule.get(a).getRequest().getAppId() || a == schedule.size()-1){
					schedule.add(a, new ProcessSolver(request, 0));
					break;
				}
			}
			if(schedule.size() == 0){
				schedule.add(new ProcessSolver(request, 0));
			}
		}
		for(ProcessSolver process : schedule){
			process.setBufferId(this.getMinProcessBuffer().getId());
		}
		return schedule;
	}

	

	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		return 0;
	}

	
	
	public Buffer getMinProcessBuffer(){
		super.getDispInterface().update();
		Buffer buffer = super.getDispInterface().getBuffers().get(0);
		for(int index=1; index < super.getDispInterface().getBuffers().size(); index++){
			if(buffer.getProcess().size() > super.getDispInterface().getBuffers().get(index).getProcess().size()){
				buffer = super.getDispInterface().getBuffers().get(index);
			}
		}
		return buffer;
	}
	
	
	
	
	

}
