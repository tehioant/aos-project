package dispatcher.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import requests.Request;

public class Buffer {
	
	public int id;
	public long capacity;
	public LinkedList<Request> queue;
	public ArrayList<ProcessBuffer> process;
	public ArrayList<Application> apps;
	public LinkedList<Application> appQueue;
	
	
	public Buffer(int id, long capacity){
		this.id = id;
		this.capacity = capacity;
		queue = new LinkedList<Request>();
		process = new ArrayList<ProcessBuffer>();
		apps = new ArrayList<Application>();
		appQueue = new LinkedList<Application>();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the capacity
	 */
	public long getCapacity() {
		return capacity;
	}


	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}


	/**
	 * @return the queue
	 */
	public LinkedList<Request> getQueue() {
		return queue;
	}


	/**
	 * @param queue the queue to set
	 */
	public void setQueue(LinkedList<Request> queue) {
		this.queue = queue;
	}


	/**
	 * @return the currentRessources
	 */
	synchronized public long getCurrentRessources() {
		this.updateProcess();
		this.updateApplication();
		long ressources = this.getCapacity();
		for(ProcessBuffer process : this.getProcess()){
			ressources -= process.getRequest().getPayload();
		}
		return ressources ;
	}




	/**
	 * @return the process
	 */
	public ArrayList<ProcessBuffer> getProcess() {
		return process;
	}


	/**
	 * @param process the process to set
	 */
	public void setProcess(ArrayList<ProcessBuffer> process) {
		this.process = process;
	}
	
	
	/**
	 * @return the apps
	 */
	public ArrayList<Application> getApps() {
		return apps;
	}


	/**
	 * @param apps the apps to set
	 */
	public void setApps(ArrayList<Application> apps) {
		this.apps = apps;
	}
	
	
	
	public void addApp(Application app){
		if(this.getApps().size() > 3){
			this.getAppQueue().add(app);
		} else {
			this.getApps().add(app);
		}
	}


	/**
	 * @return the appQueue
	 */
	public LinkedList<Application> getAppQueue() {
		return appQueue;
	}


	/**
	 * @param appQueue the appQueue to set
	 */
	public void setAppQueue(LinkedList<Application> appQueue) {
		this.appQueue = appQueue;
	}


	public synchronized void updateProcess(){
		try{
			
			float r;
			Calendar cal = Calendar.getInstance();
			if(!this.getProcess().isEmpty()){
				for(ProcessBuffer process : this.getProcess()){
					r = process.getRemainingTime();
					if(r <= 0){
						this.getProcess().remove(process);
						if(this.getQueue().getFirst() instanceof Request)
							this.getProcess().add(new ProcessBuffer(this.getQueue().poll(), cal.getTimeInMillis()));
					} else {
						process.setRemainingTime(r);
					}
				}
			}
		} catch(NoSuchElementException e){
			// This means the queue is empty and then we don't create an empty process
			//e.printStackTrace();
		}
	}
	public void updateApplication(){
		float r;
		Calendar cal = Calendar.getInstance();
		for(Application app : this.getApps()){
			r = cal.getTimeInMillis() - (app.getAppCompTime() + app.getListRequest().get(0).getStartTime());
			if(r <= 0){
				this.getApps().remove(app);
			}
		}
	}
	
	
	
	
	
	
	
}
