package dispatcher;

import java.util.ArrayList;

import dispatcher.model.Buffer;
import requests.Request;

public class DispatcherInterface {
	
	
	public Request request;
	public DummyDispatcher dispatcher; 
	public static long MAX_RESSOURCES_BUFFER = 150000; // IN MBits 
	public static int NB_BUFFERS = 10;
	
	public DispatcherInterface(){
		dispatcher = this.getDispatcher();
	}
	
	
	
	public long getCurrentCapacity(){
		return dispatcher.getCurrentRessources();
	}
	
	public long getMaxRessources(){
		return this.MAX_RESSOURCES_BUFFER;
	}
	
	public int getNbMaxBuffers(){
		return this.NB_BUFFERS;
	}
	
	
	public boolean processRequest(int bufferID, Request request){
		if(dispatcher.assignRessources(bufferID, request))
			return true;
		return false;
	}
	
	
	
	public DummyDispatcher getDispatcher(){
		return DummyDispatcher.getInstance(MAX_RESSOURCES_BUFFER, NB_BUFFERS);
	}
	
	
	public synchronized ArrayList<Buffer> getBuffers() {
		this.getDispatcher().updateBuffers();
		return this.getDispatcher().getBuffers();
	}
	
	
	public boolean checkRessources(int bufferId, Request request){
		return this.getDispatcher().checkRessources(bufferId, request);
	}
	
	
	public void update(){
		dispatcher.updateBuffers();
	}
	

}












