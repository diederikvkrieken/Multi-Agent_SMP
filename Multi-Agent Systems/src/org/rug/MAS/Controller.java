/**
 * Package containing our final project for the Multi-Agent Systems course.
 */
package org.rug.MAS;

import java.util.ArrayList;

/**
 * Controller is what MVC implies. It is used to interact with the model.
 * @author Diederik, Sebastiaan, Pieter
 *
 */
public class Controller {

	/**
	 * Entire model to act upon.
	 */
	private Model model;
	
	/**
	 * Constructor without further information
	 */
	public Controller(Model m) {
		this.model = m;
	}
	
	/**
	 * The big method to rule them all...
	 */
	public void runSimulation() {
		// Let every man propose
		for (Man m : this.model.getMen()) {
			privateProposal(m);
		}
		System.out.println("Do we have a stable marriage? " + stableMarriage()+"\n");
		while (!stableMarriage()) {
			// Another round, make single men propose
			for (Man m : this.model.getMen()) {
				if (!this.model.currentState().isEngaged(m.getName())) {
					privateProposal(m);
				}
			}
			System.out.println("Do we have a stable marriage? " + stableMarriage()+"\n");
		}
	}
	
	/* Actions here */
	
	/**
	 * Simulates a public proposal by m.
	 * @param m
	 * @return True if the proposal was accepted, false otherwise.
	 */
	public boolean publicProposal(Man m) {
		// m proposes
		String hottie = m.propose();
		System.out.println(m.getName() + " publicly proposes to " + hottie);
		// Update with knowledge that this woman is next preference
		System.out.println("That must mean that " + hottie +
				" is the next most preferred woman of " + m.getName());
		for (Man man : this.model.getMen()) {
			if (!m.equals(man)) man.nextPref(m.getName(), hottie);
		}
		
		// hottie decides to accept or not
		for (Woman w : this.model.getWomen()) {
			w.nextPref(m.getName(), hottie);	// Update knowledge in the meantime
			if (w.getName().equals(hottie)) {
				// Woman proposed to
				if (w.ponder(m.getName())) {
					// Accepted proposal, edit current state.
					System.out.println(hottie + " accepted " + m.getName() + "'s proposal");
					System.out.println("That must mean that " + hottie +
							" prefers " + m.getName() + " over her current engagement.\n");
					Man sod = this.model.updateCurrentState(new Engagement(m, w));
//					if (sod.equals(m)) {
//						// New engagement
//					} else {
//						// Broken off
//					}
					// Update knowledge that this man is most preferred
					for (Man man : this.model.getMen()) {
						man.topPref(hottie, m.getName());
					}
					for (Woman chicky : this.model.getWomen()) {
						if (!chicky.equals(hottie)) chicky.topPref(hottie, m.getName());
					}
					return true;
				} else {
					// Rejected proposal
					System.out.println(hottie + " rejected " + m.getName() + "'s proposal");
					System.out.println("That must mean that " + hottie +
							" likes " + m.getName() + " less than her current engagement.\n");
					// Update knowledge that this man is not preferred
					for (Man man : this.model.getMen()) {
						man.refused(hottie, m.getName());
					}
					for (Woman chicky : this.model.getWomen()) {
						if (!chicky.equals(hottie)) chicky.refused(hottie, m.getName());
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Simulates a private proposal by m.
	 * @param m
	 * @return True if the proposal was accepted, false otherwise.
	 */
	public boolean privateProposal(Man m) {
		String hottie = m.propose();
		System.out.println("Sly bastard " + m.getName() +
				" whispers a proposal into " + hottie + "'s ear");
		System.out.println("Only " + hottie + " now knows " + m.getName() + "'s next preference");
		for (Woman w : this.model.getWomen()) {
			if (w.getName().equals(hottie)) {
				// Woman proposed to
				w.nextPref(m.getName(), hottie);	// Update knowledge
				if (w.ponder(m.getName())) {
					// Accepted proposal, edit current state.
					System.out.println(hottie + " accepted " + m.getName() + "'s proposal");
					System.out.println("So everybody now knows the slut secretly preferred " + m.getName());
					Man sod = this.model.updateCurrentState(new Engagement(m, w));
//					if (sod.equals(m)) {
//						// New engagement
//					} else {
//						// Broken off
//					}
					// Update knowledge that this man is most preferred
					for (Man man : this.model.getMen()) {
						man.topPref(hottie, m.getName());
					}
					for (Woman chicky : this.model.getWomen()) {
						if (!chicky.equals(hottie)) chicky.topPref(hottie, m.getName());
					}
					return true;
				} else {
					// Rejected proposal
					System.out.println(hottie + " rejected " + m.getName() + "'s proposal");
					System.out.println("Unfortunately only the involved parties now know about this");
					// Man now knows he is not preferred
					m.refused(hottie, m.getName());
				}
			}
		}
		return false;
	}
	
	/* Queries here */
	
	/**
	 * Checks whether the model yielded a stable marriage.
	 * This is done by checking whether there are n engagements in the current state.
	 * NOTE: This assumes the model can only get into such a state if it is stable.
	 * @return True if all engagements are stable, false otherwise.
	 */
	public boolean stableMarriage() {
		// Check whether currentState contains as much engagements as possible
		State current = this.model.currentState();
		Engagement[] eng = current.getEngagements();
		for (Engagement e : eng) {
			if (e == null) return false;	// empty engagement
		}
		// As many engagements in eng as possible
		return true;
	}
	
	/**
	 * Validates a relation to be stable or not.
	 * This is realized by checking whether the engagement
	 * is true in all end-states.
	 * @param r
	 * @return True if all end-states contain r, false otherwise
	 */
	public boolean solidEngagement(Engagement r) {
		// For each state in the last layer..
		ArrayList<ArrayList<State>> states = model.getStates();
		if (!states.isEmpty()) {
			for (State s : states.get(states.size()-1)) {
				// Check whether there is an engagement coinciding r
				if (!s.areEngaged(r.getMan().getName(), r.getWoman().getName())) {
					// If not, it is not stable
					return false;
				}
			}
			// If the loop (properly) ends, it is.
			return true;
		}
		return false;	// empty
	}

}
