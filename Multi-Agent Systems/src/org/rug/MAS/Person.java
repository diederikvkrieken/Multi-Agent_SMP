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
	 * The counter is used by men to indicate who to propose to next.
	 * For women it indicates to whom they are engaged.
	 */
	protected Preferences preferences;
	
	/**
	 * Inner class for other preferences,
	 * holding preferences and a counter.
	 * @author Diederik, Sebastiaan & Pieter
	 *
	 */
	protected class Preferences {
		public String[] preferences;
		public int counter;
	}
	
	/**
	 * Lists of preferences of proposers.
	 */
	protected HashMap<String, Preferences> malePref;
	
	/**
	 * Lists of preferences of mistress.
	 */
	protected HashMap<String, Preferences> femalePref;
	
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
		initPref(preferences);
		this.states = states;
	}
	
	public Person(String name, String[] preferences) {
		this.name = name;
		initPref(preferences);
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Makes a Preferences object out of a list of names. 
	 * @param pref
	 */
	private void initPref(String[] pref) {
		this.preferences = new Preferences();
		this.preferences.preferences = pref;
	}
	
	/**
	 * Makes empty lists for all other people.
	 * 
	 * @param suitors
	 * @param danzels
	 */
	public void initOtherPref(Man[] suitors, Woman[] danzels) {
		// Make lists for all men
		this.malePref = new HashMap<String, Preferences>();
		for (Man m : suitors) {
			if (!m.getName().equals(this.name)) {
				// Do not need own preferences
				Preferences pref = new Preferences();
				pref.preferences = m.getPreferences();
				pref.counter = 0;
				this.malePref.put(m.getName(), pref);
			}
		}
		
		// Make lists for all women
		this.femalePref = new HashMap<String, Preferences>();
		for (Woman w : danzels) {
			if (!w.getName().equals(this.name)) {
				// Do not need own preferences
				Preferences pref = new Preferences();
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
		return this.preferences.preferences;
	}
	
	public void setPreferences(String[] pref) {
		this.preferences.preferences = pref;
	}
}
