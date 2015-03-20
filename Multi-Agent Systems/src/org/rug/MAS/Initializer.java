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
	private static String[] namesWomen = {"Alice", "Beatrice", "Cleopatra", "Eve"};
	
	/**
	 * All 'random' names of men.
	 */
	private static String[] namesMen = {"Willy", "Xerxes", "Yannick", "Zorro"};
	
	/**
	 * Initializes n men. Also assures there are no similar names.
	 * @param n
	 * @return n randomly initialized men.
	 */
	public static Man[] initializeMen(int n) {
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
	public static Woman[] initializeWomen(int n) {
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
	public static Person[] initializePreferences(Man[] men, Woman[] women) {
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
	
	public static State[] initializeStates(Man[] men, Woman[] women){
		//TODO initialize possible states
		for (int i = 0; i < men.length; i++) {
			for (int j = 0; j < women.length; j++) {
				Engagement.Engagement(men[i], women[j]);
			}
			
		}
		return states
	}
}
