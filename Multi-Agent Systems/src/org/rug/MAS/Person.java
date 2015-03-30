/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.HashMap;

/**
 * Abstract class representing involved people.
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public abstract class Person {

	/**
	 * Every person has a name.
	 */
	protected String name;
	
	/**
	 * A list of personal preferences, based on names.
	 */
	protected String[] preferences;
	
	/**
	 * Inner class for other preferences,
	 * holding preferences and a counter.
	 * @author Diederik, Sebastiaan & Pieter
	 *
	 */
	protected class OtherPref {
		public String[] preferences;
		public int counter;
	}
	
	/**
	 * Lists of preferences of proposers.
	 */
	protected HashMap<String, OtherPref> malePref;
	
	/**
	 * Lists of preferences of mistress.
	 */
	protected HashMap<String, OtherPref> femalePref;
	
	/**
	 * States considered possible by this person.
	 */
	protected State[] states;
	
	/**
	 * Constructor of a person, called by super().
	 * 
	 * @param name
	 * @param preferences
	 * @param states
	 */
	public Person(String name, String[] preferences, State[] states) {
		this.name = name;
		this.preferences = preferences;
		this.states = states;
	}
	
	public Person(String name, String[] preferences) {
		this.name = name;
		this.preferences = preferences;
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Makes empty lists for all other people.
	 * 
	 * @param suitors
	 * @param danzels
	 */
	public void initOtherPref(Man[] suitors, Woman[] danzels) {
		// Make lists for all men
		this.malePref = new HashMap<String, OtherPref>();
		for (Man m : suitors) {
			if (!m.getName().equals(this.name)) {
				// Do not need own preferences
				OtherPref pref = new OtherPref();
				pref.preferences = m.getPreferences();
				pref.counter = 0;
				this.malePref.put(m.getName(), pref);
			}
		}
		
		// Make lists for all women
		this.femalePref = new HashMap<String, OtherPref>();
		for (Woman w : danzels) {
			if (!w.getName().equals(this.name)) {
				// Do not need own preferences
				OtherPref pref = new OtherPref();
				pref.preferences = w.getPreferences();
				pref.counter = 0;
				this.femalePref.put(w.getName(), pref);
			}
		}
	}
	
	// Getters and setters
	public String getName() {
		return this.name;
	}
	
	public String[] getPreferences() {
		return this.preferences;
	}
}
