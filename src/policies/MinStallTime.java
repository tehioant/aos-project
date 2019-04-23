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
import dispatcher.model.ProcessBuffer;
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
		Buffer buffer = null;
		for(int index=0; index < queue.size(); index++){
			buffer = this.getMinStallTimeBuffer();
			scheduled.add(new ProcessSolver(queue.get(index), buffer.getId()));
		}
		return scheduled;
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
	
	
	public Buffer getMinStallTimeBuffer(){
		Buffer buff = null;
		long min = 999999999;
		long sum = 0;
		ArrayList<Buffer> buffers = super.getDispInterface().getBuffers();
		for(Buffer buffer : buffers){
			sum = 0;
			for(ProcessBuffer req : buffer.getProcess()){
				sum += req.getRequest().getCompletionTime();
			}
			for(Request request : buffer.getQueue()){
				sum += request.getCompletionTime();
			}
			if(sum < min){
				buff = buffer;
				min = sum;
			}
		}
		return buff;
	}
	
	
	public int assignToBuffer(Application app){
		ArrayList<Buffer> buffers = super.getDispInterface().getBuffers();
		for(Buffer buff : buffers){
			if(buff.getCurrentRessources() > app.getAppPayload() && buff.getProcess().size() < 3){
				return buff.getId();
			} 	
		}
		return -1;
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
