/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;

/**
 * Controller is what MVC implies. It is used to interact with the model.
 * @author Diederik, Sebastiaan, Pieter
 *
 */
public class Controller {

	/**
	 * Entire model to act upon.
	 */
	private Model model;
	
	/**
	 * Constructor without further information
	 */
	public Controller(Model m) {
		this.model = m;
	}
	
	/* Actions here */
	
	/**
	 * Simulates a public proposal by m.
	 * @param m
	 * @return True if the proposal was accepted, false otherwise.
	 */
	public boolean publicProposal(Man m) {
		String hottie = m.propose();
		for (Woman w : this.model.getWomen()) {
			if (w.getName().equals(hottie)) {
				if (w.ponder(m.getName())) {
					// Accepted proposal, edit current state.
					//TODO edit current state
					//TODO update knowledge
					return true;
				} else {
					// Rejected proposal
					//TODO update knowledge
				}
			}
		}
		return false;
	}
	
	// Stub for a private proposal
	public boolean privateProposal(Man m) {
		String hottie = m.propose();
		for (Woman w : this.model.getWomen()) {
			if (w.getName().equals(hottie)) {
				// Woman proposed to
			}
		}
		return false;
	}
	
	/* Queries here */
	
	/**
	 * Validates a relation to be stable or not.
	 * This is realized by checking whether the engagement
	 * is true in all end-states.
	 * @param r
	 * @return True if all end-states contain r, false otherwise
	 */
	public boolean solidEngagement(Engagement r) {
		// For each state in the last layer..
		ArrayList<ArrayList<State>> states = model.getStates();
		if (!states.isEmpty()) {
			for (State s : states.get(states.size()-1)) {
				// Check whether there is an engagement coinciding r
				if (!s.areEngaged(r.getMan().getName(), r.getWoman().getName())) {
					// If not, it is not stable
					return false;
				}
			}
			// If the loop (properly) ends, it is.
			return true;
		}
		return false;	// empty
	}

}
