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
		ArrayList<Application> apps = new ArrayList<Application>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(queue.get(0).getAppId());
		Application application;
		for(Request item : queue ){
			for(int i : list){
				if(item.getAppId() != i){
					list.add(item.getAppId());
				}
			}
		}
		for(int app : list){
			application = new Application();
			application.setAppId(app);
			apps.add(application);
		}
		for(Request request : queue){
			for(Application m : apps){
				if(request.getAppId() == m.getAppId()){
					m.addRequest(request);
				}
			}
		}
		return apps;
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
