/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
	private String[] namesWomen = {"Alice", "Beatrice", "Cleopatra", "Daniella", "Eve",
			"Florentina", "GLADOS", "Harriete", "Isabelle", "Juliette", "Kristina",
			"Luna", "Mildred", "Nilin", "Olivia", "Patricia", "Qlen", "Ramina", "Sophia",
			"Tamar", "Uno", "Valerie", "Wilma", "Xanthippe", "Yvette", "Zara"};
	
	/**
	 * All 'random' names of men.
	 */
	private String[] namesMen = {"Adam", "Bob", "Charles", "Dave", "Edward", "Frank",
			"Henry", "Ibo", "Jacob", "Kaleb", "Lars", "Menno", "Norris", "Oliver",
			"Presto", "Qrey", "Romeo", "Stephan", "Thomas", "Ursul",
			"V", "Willy", "Xerxes", "Yannick", "Zorro"};
	
	/**
	 * Initializes n men. Also assures there are no similar names.
	 * @param n
	 * @return n randomly initialized men.
	 */
	public Man[] initializeMen(int n) {
		Man[] men = new Man[n];		// Initialize array of men
		Random rand = new Random();	// RNG
		for (int i = 0; i < n; i++) {
			String name = namesMen[rand.nextInt(n)];
			for (int check = 0; check < i; check++) {
				// Check name was not used before, use getName
				if (name.equals(men[check].getName())) {
					name = namesMen[rand.nextInt(n)];
					check = -1;
				}
			}
			men[i] = new Man(name);
		}
		return men;
	}

	/**
	 * Initializes n women. Also assures there are no similar names.
	 * @param n
	 * @return n randomly initialized women.
	 */
	public Woman[] initializeWomen(int n) {
		Woman[] women = new Woman[n];	// Initialize array of women
		Random rand = new Random();		// RNG
		for (int i = 0; i < n; i++) {
			String name = namesWomen[(this.namesWomen.length-n)+rand.nextInt(n)];
			for (int check = 0; check < i; check++) {
				// Check name was not used before
				if (name.equals(women[check].getName())) {
					name = namesWomen[(this.namesWomen.length-n)+rand.nextInt(n)];
					check = -1;
				}
			}
			women[i] = new Woman(name);
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
			m.setPreferences(wames.toArray(new String[wames.size()]));
			res.add(m);
		}
		for (Woman w : women) {
			// Shuffle preferences
			Collections.shuffle(mames);
			w.setPreferences(mames.toArray(new String[mames.size()]));
			res.add(w);
		}
		return res.toArray(new Person[res.size()]);
	}
	
	/**
	 * Initializes the lists of preferences each person infers.
	 * 
	 * @param men
	 * @param women
	 * @return All men and women with initialized inferred lists.
	 */
	public Person[] initializeOtherPreferences(Man[] men, Woman[] women) {
		for (Man m : men) m.initOtherPref(men, women);
		for (Woman w : women) w.initOtherPref(men, women);
		
		Person[] persons = new Person[men.length + women.length];	// Array becoming all men and women
		System.arraycopy(men, 0, persons, 0, men.length);
		System.arraycopy(women, 0, persons, men.length, women.length);
		return persons;
	}
	
	/**
	 * Recursive method of initializing states.
	 * @param men
	 * @param women
	 * @param n
	 * @return List of states in the layer of depth n.
	 */
	private ArrayList<State> statesRec(ArrayList<ArrayList<State>> comp, Man[] men, Woman[] women, int n) {
		//System.out.println("Recursive call with n = " + n);
		// Base cases
		if (n < 1) {
			// Zeroth layer, contains only one state
			ArrayList<State> layer = new ArrayList<State>();
			layer.add(new State(new Engagement[0]));
			return layer;
		}
		if (n == 1) {
			// First layer
			ArrayList<State> layer = new ArrayList<State>();
			// Fill queue with a relation between each man and woman
			for (Man m : men) {
				for (Woman w: women) {
					Engagement[] eng = new Engagement[1];
					eng[0] = new Engagement(m, w);
					layer.add(new State(eng));
				}
			}
			return layer;
		}
		
		ArrayList<State> prev = new ArrayList<State>(comp.get(n-1));	// States in previous layer
		if (!prev.isEmpty()) {
			// Previous layer already computed
			// Drop towards the common part with recursive case
		} else {
			// Recursive case
			prev = new ArrayList<State>(statesRec(comp, men, women, n-1));
		}
		// Common part in computing current layer
		ArrayList<State> current = new ArrayList<State>();	// States in current layer starts empty
		// Dequeue layer until empty
		for (State s : prev) {
			Engagement[] r = s.getEngagements();
			// Add extra states with an extra relation
			for (Man m : men) {
				if (!s.isEngaged(m.getName())) {
					for (Woman w : women) {
						if (!s.isEngaged(w.getName())) {
							// Both are not engaged yet
							Engagement[] newr = new Engagement[n];	// New array of relations for next state
							System.arraycopy(r, 0, newr, 0, r.length);	// Extend for adding a new relation
							newr[n-1] = new Engagement(m, w);
							State news = new State(newr);
							if (!current.contains(news)) current.add(news);	// enqueue
						}
					}
				}
			}
		}
		return current;
	}
	
	/**
	 * Initializes all states.
	 * @param men
	 * @param women
	 * @return All states.
	 */
	public ArrayList<ArrayList<State>> initializeStates(Man[] men, Woman[] women) {
		ArrayList<ArrayList<State>> states = new ArrayList<ArrayList<State>>(); // empty state list

		// Make states for each layer
		for (int l = 0; l <= men.length; l++) {
			states.add(statesRec(states, men, women, l));
		}
		
		return states;
	}
}
