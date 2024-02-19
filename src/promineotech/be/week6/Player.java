package promineotech.be.week6;

import java.util.*;

public class Player {
	private LinkedList<Card> mHand;
	private int mScore;
	private String mName;

	// Full Constructor
	public Player(String _name) {
		mScore = 0;
		mHand = new LinkedList<Card>();
		mName = _name;
	}
	// Default Values Constructor
	public Player() {
		this("");
	}

	// Action Methods
	public Player describe() {
		System.out.println("Player Name: " + mName);
		ListIterator<Card> itr = mHand.listIterator();
		// Describe names
		while(itr.hasNext()) {
			itr.next().describe();
		}
		return this;
	}
	public boolean canPlay() {
		return !mHand.isEmpty();
	}
	// Removes a card from player
	public Card flip() {
		return mHand.pop();
	}
	// Adds a card to player from deck
	public Player draw(Deck _deck) {
		// Add to the hand from last card of Deck
		mHand.push(_deck.draw()); return this;
	}
	// Increases player score by 1
	public Player incrementScore() { ++mScore; return this; }

	// SGetter: Score
	public int getScore() { return mScore; }
	public Player setScore(int _score) { mScore = _score; return this; }

	// SGetter: Name
	public String getName() { return this.mName; }
	public Player setName(String _name) { mName = _name; return this; }
}
