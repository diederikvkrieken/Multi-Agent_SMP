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
		this.preferences.counter = pref.length-1;
	}
	
	public Woman(String name, String[] pref) {
		// TODO Auto-generated constructor stub
		super(name, pref);
		this.preferences.counter = pref.length-1;
	}
	
	public Woman(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}

}
