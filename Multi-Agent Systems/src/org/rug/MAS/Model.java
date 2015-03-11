/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

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
