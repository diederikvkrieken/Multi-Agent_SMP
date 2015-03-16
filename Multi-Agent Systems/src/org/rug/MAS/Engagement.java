/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

/**
 * Engagement represents a (temporary) relation between a man and a woman.
 * It is possible to query this relationship.
 * 
 * @author Diederik, Sebastiaan, & Pieter
 *
 */
public class Engagement {
	
	/**
	 * The man in this engagement.
	 */
	private Man dude;
	
	/**
	 * The woman in this engagement.
	 */
	private Woman chick;

	/**
	 * The Engagement constructor instates a relation between m and w.
	 * @param m
	 * @param w
	 */
	public Engagement(Man m, Woman w) {
		this.dude = m;
		this.chick = w;
	}
	
	/**
	 * Checks whether a guy and a gal are engaged.
	 * Note that the check is based on name! As such, people should have distinct names.
	 * @param m
	 * @param w
	 * @return True if m and w are engaged.
	 */
	public boolean areEngaged(String m, String w) {
		if (m == this.dude.getName() && w == this.chick.getName()) return true;
		else return false;
	}

}
