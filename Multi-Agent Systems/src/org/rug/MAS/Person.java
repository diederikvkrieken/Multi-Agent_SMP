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
	 * Inner class for preferences,
	 * holding a list of preferences and a counter.
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
				pref.preferences = new String[suitors.length-1];
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
				pref.preferences = new String[danzels.length-1];
				pref.counter = 0;
				this.femalePref.put(w.getName(), pref);
			}
		}
	}
	
	/**
	 * Adds name to the top of target's list and increases counter.
	 * Called if target accepts name's proposal. 
	 * @param target
	 * @param name
	 */
	public void topPref(String target, String name) {
		Preferences tpref = this.femalePref.get(target);
		// Edit tpref
		for (int i = tpref.preferences.length-1; i > 0; i++) {
			// Shuffle everybody down by one
			tpref.preferences[i] = tpref.preferences[i-1];
		}
		tpref.preferences[0] = name;
		tpref.counter++;
	}
	
	/**
	 * Adds name at the place of counter and increments it.
	 * Called after target proposes.
	 * @param target
	 * @param name
	 */
	public void nextPref(String target, String name) {
		Preferences tpref = this.malePref.get(target);
		tpref.preferences[tpref.counter] = name;
		tpref.counter++;
	}
	
	/**
	 * Puts name on bottom of target's list.
	 * Called if target refused name's proposal.
	 * @param target
	 * @param name
	 */
	public void refused(String target, String name) {
		Preferences tpref = this.femalePref.get(target);
		for (int i = tpref.counter; i < tpref.preferences.length; i++) {
			if (tpref.preferences[i] == null) tpref.preferences[i] = name;
		}
	}
	
	/**
	 * A person equals another if they have the same name.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person && ((Person) obj).getName().equals(this.name)) return true;
		return false;
	}
	
	// Getters and setters
	public String getName() {
		return this.name;
	}
	
	public String[] getPreferences() {
		return this.preferences.preferences;
	}
	
	public void setPreferences(String[] pref) {
		System.out.println("test");
		this.initPref(pref);
		//this.preferences.preferences = pref;
	}
	
}
