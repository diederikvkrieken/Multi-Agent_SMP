/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

/*
 * Imports for input, data structures
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * The one loop to rule them all...
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
		
		boolean init = true;	// True as long as initialization is still going on
		int num = 0;			// SMP n
		String in;				// Holder for input string
		boolean printstates = false; // If all possible states should be printed. Mostly for debugging.
		boolean privatetalk = false;
		while (init) {
			System.out.println("For how many pairs (n) would you like to simulate the Stable Marriage Problem? Max 7 pairs: running 7 pairs exceeds 10 min.");
			try {
				num = Integer.parseInt(input.next());
				if (num > 0 && num < 8) {	
					System.out.println("Simulating for "+num+" pairs");
					// Printing states?
					System.out.println("Would you like all the possible states to be printed? (y/n)?");
					in = input.next();
					while (!(in.equals("y") || in.equals("n"))) {
						System.out.println("Please specify your input as 'y' or 'n'.");
						in = input.next();
					}
					if (in.equals("y")) {
						printstates = true;
					}
					// Private or public? Default is public.
					System.out.println("Would you like private or public announcements? (pr/pu)?");
					in = input.next();
					while (!(in.equals("pr") || in.equals("pu"))) {
						System.out.println("Please specify your input as 'pr' for private or 'pu' for public announcements.");
						in = input.next();
					}
					if (in.equals("pr")) {
						privatetalk = true;
					}
					init = false;
				}else{
					System.out.println("Please input an integer between 1 and 8, more will be computationally impfeasible.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please input an integer between 1 and 8.");
			}
		}
		
		Model smp = new Model(num);
		
		Controller ctrl = new Controller(smp); 
		
		System.out.print("\nThe smp has: "+smp.getStates().size()+" layers.\n"); // print the amound of layers
		int l = 1; // placeholder to show which layer is being printed
		ArrayList<ArrayList<State>> layer = smp.getStates();
		Iterator<ArrayList<State>> iterator = layer.iterator();
		while(iterator.hasNext()){
			
			ArrayList<State> temp = iterator.next();
			ArrayList<State> states = new ArrayList<State>(new LinkedHashSet<State>(temp));
			System.out.print("Layer "+l+" has: "+states.size()+" states with "+(l-1)+" engagements.\n"); // print amount of states and engagements in this layer
			Iterator<State> state = states.iterator();
	        while(state.hasNext()){
	        	if (printstates){
	        		System.out.println(state.next());
	        	}else{
	        		state.next();
	        	}
	        }
	        l++;
		}
		if (privatetalk){
			System.out.print("\nRunning simulation with private talk!\n\n"); // print amount of engagement in this layer
		}else{
			System.out.print("\nRunning simulation with public talk!\n\n"); // print amount of engagement in this layer
		}
		ctrl.runSimulation(privatetalk);	// Run the hard-coded simulation

		input.close();
	}

}
