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
	 * Checks whether a person is in this engagement.
	 * Note that the check is based on name! As such, people should have distinct names.
	 * 
	 * @param name
	 * @return True if the person is in this engagement.
	 */
	public boolean isEngaged(String name) {
		if (name.equals(this.dude.getName()) || name.equals(this.chick.getName())) return true;
		else return false;
	}
	
	/**
	 * Checks whether a guy and a gal are engaged.
	 * Note that the check is based on name! As such, people should have distinct names.
	 * @param m
	 * @param w
	 * @return True if m and w are engaged.
	 */
	public boolean areEngaged(String m, String w) {
		if (m.equals(this.dude.getName()) && w.equals(this.chick.getName())) return true;
		else return false;
	}

	
	/**
	 * An engagement equals another if the names contained are equal.
	 * 
	 * @return True if contained names are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Engagement) {
			if (this.dude.getName().equals(((Engagement) o).getMan().getName()) &&
					this.chick.getName().equals(((Engagement) o).getWoman().getName())) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.dude.getName() + " and " + this.chick.getName() + " are engaged!";
	}
	
	// Getters
	
	public Man getMan(){
		return this.dude;
	}
	
	public Woman getWoman(){
		return this.chick;
	}
}
