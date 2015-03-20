/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * Initializer contains methods for the random initialization of an SMP.
 * @author Diederik, Sebastiaan, Pieter
 *
 */
public class Initializer {

	/**
	 * All 'random' names of women.
	 */
	private String[] namesWomen = {"Alice", "Beatrice", "Cleopatra", "Eve"};
	
	/**
	 * All 'random' names of men.
	 */
	private String[] namesMen = {"Willy", "Xerxes", "Yannick", "Zorro"};
	
	/**
	 * Initializes n men. Also assures there are no similar names.
	 * @param n
	 * @return n randomly initialized men.
	 */
	public Man[] initializeMen(int n) {
		//TODO Something to initialize men.
		Man[] men = new Man[n];
		for (int i = 0; i < n; i++) {
			men[i] = new Man(namesMen[i]);
		}
		return men;		
	}

	/**
	 * Initializes n women. Also assures there are no similar names.
	 * @param n
	 * @return n randomly initialized women.
	 */
	public Woman[] initializeWomen(int n) {
		//TODO Something to initialize women.
		Woman[] women = new Woman[n];
		for (int i = 0; i < n; i++) {
			women[i] = new Woman(namesWomen[i]);
		}
		return women;
	}
	
	/**
	 * Randomly initializes preferences of all people.
	 * @param people
	 * @return All people with randomized preferences.
	 */
	public Person[] initializePreferences(Man[] men, Woman[] women) {
		// Lists for names and resulting array of persons
		ArrayList<String> wames = new ArrayList<String>();
		ArrayList<String> mames = new ArrayList<String>();
		ArrayList<Person> res = new ArrayList<Person>();
		for (Woman w : women) {
			// Extract all women names
			wames.add(w.getName());
		}
		for (Man m : men) {
			// Get name
			mames.add(m.getName());
			// Shuffle preferences
			Collections.shuffle(wames);
			m.preferences = wames.toArray(new String[wames.size()]);
			res.add(m);
		}
		for (Woman w : women) {
			// Shuffle preferences
			Collections.shuffle(mames);
			w.preferences = mames.toArray(new String[mames.size()]);
			res.add(w);
		}
		return res.toArray(new Person[res.size()]);
//		if (people[0] instanceof Man) {
//			for (int i = 0; i < n; i++) {
//				String[] shuffle = namesWomen;
//				Collections.shuffle(Arrays.asList(shuffle));
//				people[i] = new Man(people[i].getName(), shuffle); 
//			}
//			return people;
//		}else{
//			for (int i = 0; i < n; i++) {
//				String[] shuffle = namesMen;
//				Collections.shuffle(Arrays.asList(shuffle));
//				people[i] = new Woman(people[i].getName(), shuffle); 
//			}
//			return people;
//		}
	}
	
	/**
	 * Initializes all states.
	 * @param m
	 * @param w
	 * @return All states.
	 */
	public ArrayList<ArrayList<State>> initializeStates(Man[] men, Woman[] women) {
		ArrayList<ArrayList<State>> states = new ArrayList<ArrayList<State>>(men.length); // empty state list
		
		// Make states for each layer
		for (int l = 0; l <= men.length; l++) {
			ArrayList<State> layer = new ArrayList<State>();	// States in current layer
			ArrayList<Engagement> eng = new ArrayList<Engagement>();	//
			// Add states with l engagements until all combinations are made
			for (int idx = 0; idx < l; idx++) {
				for (Man m : men) {
					for (Woman w: women) {
						layer.add(new State(eng.toArray(new Engagement[eng.size()])));
					}
				}
			}
			states.set(l, layer);
		}
		
		return states;
	}
}
