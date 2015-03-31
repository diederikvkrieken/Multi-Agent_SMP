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
	 * State the simulation is in.
	 */
	private State currentState;
	
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
		// Initialize initializer
		Initializer init = new Initializer();
		
		// Initialize people
		Person[] people = init.initializePreferences(init.initializeMen(n), init.initializeWomen(n));
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
		//TODO don't know whether this actually adapts the people rather than making new copies.
		init.initializeOtherPreferences(this.men, this.women);
		
		// Initialize states
		this.states = init.initializeStates(men, women);
		this.currentState = new State(new Engagement[this.men.length]);
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
	
	/**
	 * Updates the current state with a new engagement r.
	 * This may either break off a previous engagement, or add a new one.
	 * @param r
	 * @return The man dumped if a break occurred, otherwise the man currently engaged. 
	 */
	public Man updateCurrentState(Engagement r) {
		Man poorSod = r.getMan();	// Man who was or is engaged
		// Check whether woman was already engaged
		for (Engagement e : this.currentState.getEngagements()) {
			if (e == null) {
				// Not engaged yet, add engagement
				e = r;
			}
			if (e.isEngaged(r.getWoman().getName())) {
				// Already engaged, break off engagement
				poorSod = e.getMan();
				e = r;
			}
		}
		return poorSod;
	}
	
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
