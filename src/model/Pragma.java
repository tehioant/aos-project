package model;

public class Pragma {
	
	
	public int start;
	public int duration;
	public int space;
	public int priority;
	
	
	public Pragma(int start, int duration, int space, int priority){
		this.start = start;
		this.duration = duration;
		this.space = space;
	}
   

	//Getters and setters
	
	
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}


	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}


	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}


	/**
	 * @return the space
	 */
	public int getSpace() {
		return space;
	}


	/**
	 * @param space the space to set
	 */
	public void setSpace(int space) {
		this.space = space;
	}


	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}


	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
	// Methodes
	

}
