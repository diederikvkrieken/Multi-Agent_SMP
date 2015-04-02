/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

/**
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public class Man extends Person {

	/**
	 * Constructor for a man.
	 */
	public Man(String name, String[] pref, State[] states) {
		// TODO Auto-generated constructor stub
		super(name, pref, states);
		this.preferences.counter = 0;
	}
	
	public Man(String name, String[] pref) {
		// TODO Auto-generated constructor stub
		super(name, pref);
		this.preferences.counter = 0;
	}
	
	public Man(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	/**
	 * Simulates a proposal by this bold guy.
	 * @return Woman he proposes to.
	 */
	public String propose() {
		System.out.println(this.name + ": Who will I propose to?");
		if (this.preferences.counter < 1) {
			System.out.println(this.preferences.preferences[this.preferences.counter] + " is nice, funny and sooooo hot."
					+ " I'll propose to her first.\n");
		} else {
			System.out.println(this.preferences.preferences[this.preferences.counter] + " is the next on my list.\n");
		}
		this.preferences.counter++;
		return this.preferences.preferences[this.preferences.counter-1];
	}

}
