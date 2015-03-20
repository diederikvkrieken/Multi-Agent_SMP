/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

/**
 * A state is characterised by the engagements.
 * The same men and women are always present.
 *  
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public class State {
	
	/**
	 * All relations holding in the current state.
	 */
	private Engagement[] relations;
	
	//TODO add connections to other states?

	/**
	 * Constructor for a state.
	 * @param relations
	 */
	public State(Engagement[] relations) {
		this.relations = relations;
	}
	
	/**
	 * Checks whether a person is in an engagement.
	 * @param a
	 * @return True if a is engaged
	 */
	public boolean isEngaged(String a) {
		for (Engagement r : relations) {
			if (r.isEngaged(a)) return true;
		}
		return false;
	}
	
	/**
	 * Checks whether there is an engagement between a and b.
	 * @param a
	 * @param b
	 * @return True if a and b are engaged
	 */
	public boolean areEngaged(String a, String b) {
		for (Engagement r : relations) {
			if (r.areEngaged(a, b)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String engagements = new String();
		for (Engagement r : relations) {
			engagements += r.toString() + "\n";
		}
		return "State with " + relations.length + " relations:\n" + engagements;
	}
	
	// Getter
	public Engagement[] getEngagements() {
		return this.relations;
	}
	
}
