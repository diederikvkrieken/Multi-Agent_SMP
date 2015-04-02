/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
/*
 * Imports for input,
 */
import java.util.Scanner;
import java.util.Set;


/**
 * @author Diederik, Sebastiaan & Pieter
 *
 */
public class SMP {

	/**
	 * Shell for the stable marriage problem
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Shell for the stable marriage problem
		Scanner input = new Scanner(System.in);
		
		//TODO Initialization of the problem
		boolean init = true;	// True as long as initialization is still going on
		String in;				// Holder for input string
		int num;				// SMP n
		/*while (init) {
			System.out.println("For which n would you like to simulate the Stable Marriage Problem?");
			try {
				num = Integer.parseInt(input.next());
				if (num > 0 && num < 10) {
					num *= 2;	// To account for men and women
							System.out.println("Would you like to specify the people yourself (y/n)?");
							in = input.next();
							while (!(in.equals("y") || in.equals("n"))) {
								System.out.println("Please specify your input as 'y' or 'n'.");
								in = input.next();
							}
							if (in.equals("y")) {
								System.out.println("Specify a person as (name, gender)");
								while (num > 0) {
									System.out.println(num + " more to go...");
									in = input.next();
									while (/* in does not conform format *//*) {
										System.out.println("Please use the format (name, gender)");
										in = input.next();
									}
									num--;
									if (num == 0) {
										// Check whether there are just as many men as women
										// If not, reset num
									}
								}
							} else {
								// Randomly initialize people
							}
							System.out.println("Would you like to specify the preferences yourself (y/n)?");
							in = input.next();
							while (!(in.equals("y") || in.equals("n"))) {
								System.out.println("Please specify your input as 'y' or 'n'.");
								in = input.next();
							}
							//TODO handle initializing preferences
							init = false;
				} else {
					System.out.println("Please input an integer between 1 and 10.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please input an integer between 1 and 10.");
			}
		}*/
		num = 8;
		
		Model smp = new Model(num);
		
		Controller ctrl = new Controller(smp);
		
//		Engagement testa = new Engagement(new Man("Adam"), new Woman("Eve"));
//		Engagement testb = new Engagement(new Man("Adam"), new Woman("Eve"));
//		System.out.println("equal engagement: " + testa.equals(testb));
//		Engagement testc = new Engagement(new Man("Bob"), new Woman("Sandra"));
//		Engagement testd = new Engagement(new Man("David"), new Woman("Wilma"));
//		System.out.println("unequal engagement: " + testc.equals(testd));
//		State stateA = new State(new Engagement[] {testa, testc});
//		State statebla = new State(new Engagement[] {testc, testa});
//		State stateEx = new State(new Engagement[] {testa, testc, testd});
//		State stateB = new State(new Engagement[] {testb, testc});
//		System.out.println("equal state: " + stateA.equals(stateB));
//		System.out.println("shuffled state: " + stateA.equals(statebla));
//		System.out.println("extra state: " + stateA.equals(stateEx));
//		
//		State stateC = new State(new Engagement[] {testa, testd});
//		System.out.println("unequal state: " + stateA.equals(stateC));
//		
//		LinkedList<State> testQueue = new LinkedList<State>();
//		testQueue.add(stateA);
//		System.out.println("Adding equal state: " + !testQueue.contains(stateB));
//		System.out.println("Adding unequal state: " + !testQueue.contains(stateC));
		
		/*Man[] men = smp.getMen();
		Woman[] women = smp.getWomen();
		
		System.out.println(men[0].getName());
		System.out.println(men[1].getName());
		System.out.println(women[0].getName());
		System.out.println(women[1].getName());*/
		
//		Person[] people = Initializer.initializePreferences(men, women); 
//		men = (Man[]) Initializer.initializePreferences(men, num);
//		women = (Woman[]) Initializer.initializePreferences(women, num);
		//String[] pref = men[1].getPreferences();
//		String Woman1 = people[5].name.toString();
//		System.out.println(Woman1);
		

//		for (ArrayList<State> layer : smp.getStates()) {
//			System.out.println(layer.get(0));
//		}
				
		/*
		Set<ArrayList<State>> s = new LinkedHashSet<ArrayList<State>>(smp.getStates());
		Set<State> t = new HashSet<State>();
	    Iterator<ArrayList<State>> itr = s.iterator();
		while (itr.hasNext()){
		      t.addAll(itr.next());
		}
		s.clear();
		//List<String> list = new ArrayList<String>(hash);
		for (int i = 0; i < t.size(); i++) {
			s.add(t[i]);
		}
		ArrayList<ArrayList<State>> layer = new ArrayList<ArrayList<State>>(s); //remove duplicates */
		
		System.out.print("smp: "+smp.getStates().size()+"\n");

		ArrayList<ArrayList<State>> layer = smp.getStates();
		Iterator<ArrayList<State>> iterator = layer.iterator();
		while(iterator.hasNext()){
			/*ArrayList<State> temp = iterator.next();
			Set<State> temp2 = new LinkedHashSet<State>(temp);
			ArrayList<State> states = new ArrayList<State>(temp2);
			ArrayList<State> states = new ArrayList<State>(new LinkedHashSet<State>(temp));
			 */
			ArrayList<State> temp = iterator.next();
			ArrayList<State> states = new ArrayList<State>(new LinkedHashSet<State>(temp));
			//ArrayList<State> states = iterator.next();
			System.out.print("layer: "+states.size()+"\n");
			Iterator<State> state = states.iterator();
	        while(state.hasNext()){
	        	
	        	//System.out.println(state.next());
	        	state.next();
	        }
	        System.out.print("next!\n");
		}
		
		ctrl.runSimulation();

		//for (int i = 0; i < ((CharSequence) layer).length(); i++) {
		//	System.out.println(layer.get(i));
		//}
		
		//TODO Running the problem
		boolean terminate = false;	//whether or not to exit
		//TODO here the big for loop to rule them all
		while (!terminate) {
			// Or just a while loop to rule some of them...
			//TODO here step by step run of the model
			//TODO if stable match, terminate
			terminate = true;
		}
	}

}
