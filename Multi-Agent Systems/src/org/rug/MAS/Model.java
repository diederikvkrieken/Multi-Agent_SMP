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
	private State[] states;
	
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
		this.men = Initializer.initializeMen(n);
		this.women = Initializer.initializeWomen(n);
		
		Person[] people = Initializer.initializePreferences(men, women);
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
	 * Initializes a model with states, men and women.
	 * 
	 * @param states
	 * @param men
	 * @param women
	 */
	public Model(State[] states, Man[] men, Woman[] women) {
		this.states = states;
		this.men = men;
		this.women = women;
	}
	
	//TODO Queries for knowledge in the model?
	
}
