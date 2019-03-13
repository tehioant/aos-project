package dispatcher;

public class DispatcherInterface {
	
	
	public int capacity;
	public int ressources;
	public DummyDispatcher dispatcher = new DummyDispatcher();
	
	
	public DispatcherInterface(int ressources){
		this.ressources = ressources;
	}
	
	public DispatcherInterface(){
		this(0);
	}
	
	
	
	
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	
	
	
	public int getCapacity(){
		return this.capacity;
	}
	
	
	
	
	public void setAllocatedRessource(int ressources){
		this.ressources = ressources;
	}
	
	
	
	
	public int getAllocatedRessource(){
		return this.ressources;
	}
	
	
	
	public boolean getRessourcesDispatcher(int ressources){
		return dispatcher.authorization(ressources);
	}
	
	

}












