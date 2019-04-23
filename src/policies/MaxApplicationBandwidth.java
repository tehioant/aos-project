package policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dispatcher.DispatcherInterface;
import dispatcher.model.Application;
import dispatcher.model.Buffer;
import requests.Request;
import solver.ProcessSolver;

public class MaxApplicationBandwidth extends Policy{

	public ArrayList<Application> appList;
	
	
	public MaxApplicationBandwidth(DispatcherInterface dispInterface, LinkedList<Request> queue){
		super(dispInterface, queue);
		this.appList = this.getAllApps(queue);
	}
	
	public MaxApplicationBandwidth() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MaxApplicationBandwidth";
	}

	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> schedule = new ArrayList<ProcessSolver>();
		Collections.shuffle(this.appList);
		for(Application application : this.appList){
			if(this.getMinAppBuffer() != null){
				for(Request request : application.getListRequest()){
					schedule.add(new ProcessSolver(request, this.getMinAppBuffer().getId()));
				}
			} else {
				for(Request request : application.getListRequest()){
					schedule.add(new ProcessSolver(request, this.getMinQueue().getId()));
				}
			}
		}
		return schedule;
	}
	
	
	
	
	
	
	
	public Buffer getMinAppBuffer(){
		super.getDispInterface().update();
		Buffer buffer = super.getDispInterface().getBuffers().get(0);
		for(int index=1; index < super.getDispInterface().getBuffers().size(); index++){
			if(buffer.getApps().size() > super.getDispInterface().getBuffers().get(index).getApps().size()){
				buffer = super.getDispInterface().getBuffers().get(index);
			}
		}
		if(buffer.getApps().size() > 2){
			return null;
		} else {
			return buffer;
		}
	}
	
	
	
	public Buffer getMinQueue(){
		super.getDispInterface().update();
		Buffer buffer = super.getDispInterface().getBuffers().get(0);
		for(int index=1; index < super.getDispInterface().getBuffers().size(); index++){
			if(buffer.getAppQueue().size() > super.getDispInterface().getBuffers().get(index).getAppQueue().size()){
				buffer = super.getDispInterface().getBuffers().get(index);
			}
		}
		return buffer;
	}
	
	
	
public ArrayList<Application> getAllApps(LinkedList<Request> queue){
		
		ArrayList<Application> listOfApps = new ArrayList<Application>();
		int num = 0;
		for(Request request : queue){
			num++;
			for(int ap=0; ap < listOfApps.size(); ap++){
				if(request.getAppId() == listOfApps.get(ap).getAppId()){
					listOfApps.get(ap).addRequest(request);
					break;
				} else if(ap == listOfApps.size()-1){
					listOfApps.add(new Application(null, request.getAppId()));
					listOfApps.get(listOfApps.size()-1).addRequest(request);
					break;
				}
				
			}
			if(listOfApps.size() == 0){
				listOfApps.add(new Application(null, request.getAppId()));
				listOfApps.get(0).addRequest(request);
			}
		}
		return listOfApps;
	}
	
	public Application getApplication(LinkedList<Request> queue, int id){
		Application app = new Application();
		app.setAppId(id);
		for(Request request : queue){
			if(request.getAppId() == id)
				app.addRequest(request);
		}
		app.getAppPayload();
		app.getAppCompTime();
		return app;
	}
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	

}
