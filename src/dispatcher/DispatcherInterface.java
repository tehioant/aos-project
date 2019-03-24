package dispatcher;

import requests.Request;

public class DispatcherInterface {
	
	
	public Request request;
	public DummyDispatcher dispatcher = DummyDispatcher.getInstance();
	public static int MAX_RESSOURCES_DISPATCHER = 10000000; // IN MBits 
	public static int NB_BUFFERS = 100;
	
	public DispatcherInterface(){
		dispatcher.setMaxRessources(MAX_RESSOURCES_DISPATCHER);
		dispatcher.setBuffers(NB_BUFFERS);
	}
	
	
	
	public int getCurrentCapacity(){
		return dispatcher.getCurrentRessources();
	}
	
	public int getMaxRessources(){
		return this.MAX_RESSOURCES_DISPATCHER;
	}
	
	public int getBuffers(){
		return this.NB_BUFFERS;
	}
	
	
	public boolean processRequest(Request request){
		if(dispatcher.checkRessources(request.payload)){
			this.assignRessources(request.getPayload());
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public void assignRessources(int ressources) {
		dispatcher.assignRessources(ressources);
	}
	
	
	public void releaseRessources(int ressources){
		dispatcher.releaseRessources(ressources);
	}
	
	
	

}












