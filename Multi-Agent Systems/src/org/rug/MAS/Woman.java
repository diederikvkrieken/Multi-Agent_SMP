/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

/**
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public class Woman extends Person {

	/**
	 * Constructor for a woman.
	 */
	public Woman(String name, String[] pref, State[] states) {
		// TODO Auto-generated constructor stub
		super(name, pref, states);
		this.preferences.counter = pref.length;
	}
	
	public Woman(String name, String[] pref) {
		// TODO Auto-generated constructor stub
		super(name, pref);
		this.preferences.counter = pref.length;
	}
	
	public Woman(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}

	/**
	 * Simulates the beautiful princess considering a proposal by proposer.
	 * @param proposer
	 * @return True if proposal is accepted, false otherwise.
	 */
	public boolean ponder(String proposer) {
		int priority = this.preferences.preferences.length;	// Rank of proposer
		for (int idx = 0; idx < this.preferences.preferences.length; idx++) {
			if (this.preferences.preferences[idx].equals(proposer)) {
				// Proposer found in list, mark priority and terminate loop.
				priority = idx;
				break;
			}
		}
		if (priority < this.preferences.counter) {
			// Oooh handsome.. update counter and accept
			this.preferences.counter = priority;
			return true;
		}
		return false;	// Reject proposal
	}
}
