package promineotech.be.week6;

import java.util.*;

public class Deck {
	// List of Cards
	private LinkedList<Card> mCards;

	public Deck(LinkedList<Card> _cards) { this.mCards = _cards; }
	// Parameterless Constructor
	public Deck() { this(new LinkedList<Card>()); }

	// Action Methods
	public Deck shuffle() {
		// Split deck
		if(hasCards()) {
			int halfPoint = mCards.size()/2;
			List<Card> shuffledPile = new LinkedList<Card>();
			//System.out.println("Shuffle!");
			for(int i = halfPoint - 1, j = mCards.size() - 1; i >= 0 || j >= halfPoint;) {
				if(i >= 0) {
					shuffledPile.add(mCards.get(i--));
				}
				if(j >= halfPoint) {
					shuffledPile.add(mCards.get(j--));
				}
			}
			mCards = (LinkedList<Card>)shuffledPile;

			
		}
		return this;
	}
	// Get the last card on top, AKA the last element in the list
	public Card draw() { return this.mCards.pop(); }
	public boolean hasCards() { return !this.mCards.isEmpty(); }
}