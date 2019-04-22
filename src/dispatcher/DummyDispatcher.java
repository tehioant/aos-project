package dispatcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import dispatcher.model.Buffer;
import dispatcher.model.ProcessBuffer;
import policies.Policy;
import requests.Request;
import solver.Solver;

public class DummyDispatcher {
	
	
	
	private static DummyDispatcher instance=null;
	public ArrayList<Buffer> buffers;
	
	
	private DummyDispatcher(long maxRessources, int buffers){
		this.buffers = new ArrayList<Buffer>();
		for(int n=0; n < buffers; n++){ // Creating the buffers with the ressources
			this.buffers.add(new Buffer(n, maxRessources));
		}
	}
	
	
	private DummyDispatcher(){
		this(0, 0);
	}
	
	

	
	public synchronized static DummyDispatcher getInstance(long maxRessources, int buffers){
		if (instance == null) 
			instance = new DummyDispatcher(maxRessources, buffers); 
        return instance;
	}


	


	/**
	 * @return the currentRessources
	 */
	public long getCurrentRessources() {
		return 0;
	}


	
	/**
	 * @return the buffers
	 */
	public ArrayList<Buffer> getBuffers() {
		return buffers;
	}


	/**
	 * @param buffers the buffers to set
	 */
	public void setBuffers(ArrayList<Buffer> buffers) {
		this.buffers = buffers;
	}
	
	
	public synchronized void updateBuffers(){
		for(Buffer buffer : this.getBuffers()){
			buffer.updateProcess();
			buffer.updateApplication();
		}
	}
	


	public boolean checkRessources(int bufferId, Request request){
		this.getBuffers().get(bufferId).updateProcess();
		this.getBuffers().get(bufferId).updateApplication();
		if(this.getBuffers().get(bufferId).getCurrentRessources() > request.getPayload()){
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean assignRessources(int bufferId, Request request){
		if(checkRessources(bufferId, request)){
			Calendar cal = Calendar.getInstance();
			this.getBuffers().get(bufferId).getProcess().add(new ProcessBuffer(request, cal.getTimeInMillis()));
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}










