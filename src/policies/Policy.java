package policies;

import java.util.ArrayList;

import model.Pragma;

public abstract class Policy {
	
	
	public static final int DEFAULT_MODE = 1;
	
	public ArrayList<Pragma> pragmas;
	public int mode;



	public Policy(ArrayList<Pragma> pragmas, int mode) {
		this.pragmas = pragmas;
		this.mode = mode;
	}
	public Policy(ArrayList<Pragma> pragmas) {
		this(pragmas, DEFAULT_MODE);
	}
	
	public Policy() {
		this(null, DEFAULT_MODE);
	}
	
	
	/**
	 * @return the pragmas
	 */
	public ArrayList<Pragma> getPragmas() {
		return pragmas;
	}


	/**
	 * @param pragmas the pragmas to set
	 */
	public void setPragmas(ArrayList<Pragma> pragmas) {
		this.pragmas = pragmas;
	}
	
	
	
	public Pragma getThePragma(int id) {
		return pragmas.get(id);
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	public abstract ArrayList<Pragma> sortPragmas(ArrayList<Pragma> array);
	public abstract String getPolicyName();

}
