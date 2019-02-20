package policies;

import java.util.ArrayList;
import java.util.Vector;

import model.Pragma;

public abstract class Policy {
	
	
	public static final int DEFAULT_MODE = 1;
	
	public ArrayList<Pragma> pragmas;
	public Vector vectorPragma;
	public int mode;



	public Policy(ArrayList<Pragma> pragmas, int mode) {
		this.pragmas = pragmas;
		this.mode = mode;
	}
	public Policy(Vector vectorPragma, int mode) {
		this.vectorPragma = vectorPragma;
		this.mode = mode;
	}
	
	
	public Policy(ArrayList<Pragma> pragmas) {
		this(pragmas, DEFAULT_MODE);
	}
	public Policy(Vector vectorPragma) {
		this(vectorPragma, DEFAULT_MODE);
	}
	
	
	public Policy() {
		this((Vector) null, DEFAULT_MODE);
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
	
	
	
	/**
	 * @return the vectorPragma
	 */
	public Vector getVectorPragma() {
		return vectorPragma;
	}
	/**
	 * @param vectorPragma the vectorPragma to set
	 */
	public void setVectorPragma(Vector vectorPragma) {
		this.vectorPragma = vectorPragma;
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
	public abstract ArrayList<Vector> sortVectorPragma(ArrayList<Vector> vector);
	public abstract String getPolicyName();

}
