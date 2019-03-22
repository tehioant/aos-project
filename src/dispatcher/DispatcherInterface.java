package dispatcher;

import requests.Request;

public class DispatcherInterface {
	
	
	public int capacity;
	public Request request;
	public DummyDispatcher dispatcher = DummyDispatcher.getInstance();
	public static int MAX_RESSOURCES_DISPATCHER = 10000000; // IN MBits 
	
	
	public DispatcherInterface(){
		dispatcher.setMaxRessources(MAX_RESSOURCES_DISPATCHER);
	}
	
	
	
	
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	
	
	
	public int getCapacity(){
		return this.capacity;
	}
	
	
	public void processRequest(Request request){
		try {
			this.assignRessources(request.getPayload());
			Thread.sleep(request.getTime());
			this.releaseRessources(request.getPayload());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void assignRessources(int ressources) throws InterruptedException{
		while(true){
			if(dispatcher.checkRessources(ressources)){
				dispatcher.assignRessources(ressources);
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}
	
	
	public void releaseRessources(int ressources){
		dispatcher.releaseRessources(ressources);
	}
	
	
	

}












