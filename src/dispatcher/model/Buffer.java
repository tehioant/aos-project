package dispatcher.model;

import java.util.ArrayList;
import java.util.LinkedList;

import requests.Request;

public class Buffer {
	
	public int id;
	public long capacity;
	public LinkedList<Request> queue;
	public ArrayList<ProcessBuffer> process;
	
	
	public Buffer(int id, long capacity){
		this.id = id;
		this.capacity = capacity;
		queue = new LinkedList<Request>();
		process = new ArrayList<ProcessBuffer>();
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
	public long getCurrentRessources() {
		this.update();
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
	
	
	public void update(){
		float r;
		for(ProcessBuffer process : this.getProcess()){
			r = process.getRemainingTime();
			if(r <= 0){
				this.getProcess().remove(process);
			} else {
				process.setRemainingTime(r);
			}
		}
		
	}
	

	
	
	
}
