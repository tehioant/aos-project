package policies;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dispatcher.DispatcherInterface;
import dispatcher.model.Application;
import dispatcher.model.Buffer;
import requests.Request;
import solver.ProcessSolver;

public class MinStallTime extends Policy{

	public Date startTime;
	
	Random random = new Random();
	ArrayList<Application> appList;
	
	
	public MinStallTime(DispatcherInterface dispInterface, LinkedList<Request> queue){
		super(dispInterface, queue);
		this.appList = this.getAllApps(queue);
	}
	
	public MinStallTime() {
		super();
	}
	
	
	
	
	
	@Override
	public String getPolicyName() {
		return "MinStallTime";
	}

	@Override
	public ArrayList<ProcessSolver> getScheduled(LinkedList<Request> queue) {
		ArrayList<ProcessSolver> scheduled = new ArrayList<ProcessSolver>();
		
		// First select randomly an app to schedule
		int randomReq;
		Application application;
		int k;
		while(true){ // while buffers are available
			randomReq = random.nextInt(this.appList.size()-1);
			application = this.appList.get(randomReq);
			k = this.assignToBuffer(application);
			if(k != -1){
				for(int index=0; index < application.getListRequest().size(); index++){
					scheduled.add(new ProcessSolver(application.getListRequest().get(index), k));
				}
			} else {
				break;
			}
			this.appList.remove(randomReq);
		}
		if(this.appList.isEmpty()){
			return scheduled;
		} else {
			int id;
			for(Application app : this.appList){
				id = this.getMinStallTimeBuffer();
				for(int i=0; i < app.getListRequest().size(); i++){
					scheduled.add(new ProcessSolver(app.getListRequest().get(i), id));
					super.getDispInterface().getBuffers().get(id).getQueue().add(app.getListRequest().get(i));
				}
			}
		return scheduled;
		}
	}
	
	
	
	
	

	// TODO 
	public void costFunction(LinkedList<Request> queue){
		int cost = 0;
		for(Request req : queue){
			cost += (req.getStallTime());
		}
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
	
	
	
	public int assignToBuffer(Application app){
		for(Buffer buff : super.getDispInterface().getBuffers()){
			if(buff.getCurrentRessources() > app.getAppPayload() && buff.getProcess().size() < 3){
				return buff.getId();
			} 	
		}
		return -1;
	}
	
	
	public int getMinStallTimeBuffer(){
		int id = -1;
		long min = 999999999;
		long sum = 0;
		for(Buffer buffer : super.getDispInterface().getBuffers()){
			for(Request request : buffer.getQueue()){
				sum += request.getCompletionTime();
			}
			if(sum < min){
				id = buffer.getId();
			}
		}
		return id;
	}

	@Override
	public long costFunction(ArrayList<ProcessSolver> scheduled) {
		long sum = 0;
		for(Application app : this.appList){
			sum += app.getStallTime() + app.getAppCompTime();
		}
		return sum / this.appList.size();
	}
	
	
	
}
