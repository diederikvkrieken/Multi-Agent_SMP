/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;

/**
 * Class from which the model is instantiated.
 * The model contains the complete representation of the SMP.
 * 
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public class Model {

	/**
	 * All states in the model.
	 */
	private ArrayList<ArrayList<State>> states;
	
	/**
	 * The men to be coupled.
	 */
	private Man[] men;
	
	/**
	 * The women to be coupled.
	 */
	private Woman[] women;
	
	/**
	 * Generic model. Initializes states, men and women randomly
	 * in an SMP of size n.
	 * 
	 * @param n
	 */
	public Model(int n) {
		Initializer init = new Initializer();
		this.men = init.initializeMen(n);
		this.women = init.initializeWomen(n);
		
		Person[] people = init.initializePreferences(men, women);
		ArrayList<Man> m = new ArrayList<Man>();
		ArrayList<Woman> w = new ArrayList<Woman>();
		for(Person p : people) {
			if (p instanceof Man) {
				m.add((Man) p);
			} else {
				w.add((Woman) p);
			}
		}
		this.men = m.toArray(new Man[n]);
		this.women = w.toArray(new Woman[n]);
	}
	
	/**
	 * Initializes a model with states, given men and women.
	 * 
	 * @param men
	 * @param women
	 */
	public Model(Man[] men, Woman[] women) {
		this.men = men;
		this.women = women;
		
		Initializer init = new Initializer();
		this.states = init.initializeStates(men, women);
	}
	
	//TODO Queries for knowledge in the model?
	
	
	// Getters and setters
	public ArrayList<ArrayList<State>> getStates() {
		return this.states;
	}
	
	public Man[] getMen() {
		return this.men;
	}
	
	public Woman[] getWomen() {
		return this.women;
	}
	
	public void setStates(ArrayList<ArrayList<State>> s) {
		this.states = s;
	}
	
	public void setMen(Man[] m) {
		this.men = m;
	}
	
	public void setWomen(Woman[] w) {
		this.women = w;
	}
}
