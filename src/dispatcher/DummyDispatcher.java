package dispatcher;

import java.util.LinkedList;

import policies.Policy;
import requests.Request;
import solver.Solver;

public class DummyDispatcher {
	
	
	
	private static DummyDispatcher instance=null;
	public int maxRessources;
	public int currentRessources;
	public int buffers;
	
	
	private DummyDispatcher(int maxRessources, int buffers){
		this.maxRessources = maxRessources;
		this.currentRessources = maxRessources;
		this.buffers = buffers;
	}
	
	
	private DummyDispatcher(){
		this(0, 0);
	}
	
	
	public boolean authorization(int ressources){
		if(ressources < 200)
			return true;
		else
			return false;
	}

	
	public synchronized static DummyDispatcher getInstance(){
		if (instance == null) 
			instance = new DummyDispatcher(); 
        return instance;
	}


	/**
	 * @return the maxRessources
	 */
	public int getMaxRessources() {
		return maxRessources;
	}


	/**
	 * @param maxRessources the maxRessources to set
	 */
	public void setMaxRessources(int maxRessources) {
		this.maxRessources = maxRessources;
	}


	/**
	 * @return the currentRessources
	 */
	public int getCurrentRessources() {
		return currentRessources;
	}


	/**
	 * @param currentRessources the currentRessources to set
	 */
	public void setCurrentRessources(int currentRessources) {
		this.currentRessources = currentRessources;
	}
	
	
	/**
	 * @return the buffers
	 */
	public int getBuffers() {
		return buffers;
	}


	/**
	 * @param buffers the buffers to set
	 */
	public void setBuffers(int buffers) {
		this.buffers = buffers;
	}


	public boolean checkRessources(int ressources){
		if(this.getCurrentRessources() > ressources){
			
			return true;
		} else {
			return false;
		}
	}
	
	
	public void assignRessources(int ressources){
		this.setCurrentRessources(this.getCurrentRessources() - ressources);
	}
	
	
	public void releaseRessources(int ressources){
		this.setCurrentRessources(this.getCurrentRessources() + ressources);
	}
	
	
	
	
	
	
	
	
	
	
	
}










