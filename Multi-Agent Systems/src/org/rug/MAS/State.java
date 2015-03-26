/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.Arrays;

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
		if (relations.length != 0) {
			for (Engagement r : relations) {
				if (r != null && r.isEngaged(a)) return true;
			}
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
		if (relations.length != 0) {
			for (Engagement r : relations) {
				if (r != null && r.areEngaged(a, b)) return true;
			}
		}
		return false;
	}
	
	public String[] areOccupied(){
		if (relations.length != 0) {
			String[] s = new String[(relations.length*2)];
			int i =0;
			for (Engagement r : relations) {
				s[i] = r.getMan().getName();
				i++;
				s[i] = r.getWoman().getName();
				i++;
			}
			return s;
		}else{
			String[] s = new String[0];
			s[0] = "";
			return s;			
		}
	}
	
	/**
	 * A state equals another if it contains exactly the same relations.
	 * 
	 * @return True if the relations are exactly the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		// I apologize up front for the one-liner :)
		if (obj instanceof State && Arrays.equals(((State) obj).getEngagements(), this.relations)) return true;
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
