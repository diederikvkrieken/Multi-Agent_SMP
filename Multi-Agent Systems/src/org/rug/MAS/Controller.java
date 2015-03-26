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
