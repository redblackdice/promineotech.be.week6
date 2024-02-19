package promineotech.be.week6;

import java.util.*;

public class WarCardGame {
	private Deck mGameDeck;
	private Player mPlayer1, mPlayer2;
	private int nRounds;

	public WarCardGame(Deck _deck, Player _p1, Player _p2) {
		nRounds = 0;
		mGameDeck = _deck;
		mPlayer1 = _p1;
		mPlayer2 = _p2;
	}
	public WarCardGame(Player _p1, Player _p2) {
		this(null, _p1, _p2);
	}
	public WarCardGame(Deck _deck) {
		this(_deck, null, null);
	}
	public WarCardGame() {
		this(null, null, null);
	}

	// SGetter
	public Player getPlayer1() { return mPlayer1; }
	public WarCardGame setPlayer1(Player _p1) { mPlayer1 = _p1; return this; }

	public Player getPlayer2() { return mPlayer2; }
	public WarCardGame setPlayer2(Player _p2) { mPlayer2 = _p2; return this; }

	public Deck getGameDeck() { return mGameDeck; }
	public WarCardGame setGameDeck(Deck _deck) { mGameDeck= _deck; return this; }

	// Check if playable
	public boolean canPlay() {
		return mPlayer1.canPlay() && mPlayer2.canPlay();
	}

	// Compare the value of the cards
	private int compareCards(Card _c1, Card _c2) {
		return (_c1.getValue() != _c2.getValue())
			? (_c1.getValue() > _c2.getValue() ? 1 : -1) // 1: p1 is greater, -1: p2 is greater
			: 0; // Even
	}

	// Play!
	public WarCardGame playCards() throws Exception {
		// Flip cards
		Card c1 = mPlayer1.flip(), c2 = mPlayer2.flip();
		// Show what cards are played
		c1.describe();
		c2.describe();
		int result = compareCards(c1, c2);
		++nRounds;
		// Compare: Increment score or do nothing
		switch(result) {
		case -1:
			// Player 2 wins
			mPlayer2.incrementScore();
			System.out.println("msg: Player 2 wins++");
			break;
		case 1:
			// Player 1 wins
			mPlayer1.incrementScore();
			System.out.println("msg: Player 1 wins++");
			break;
		case 0:
			System.out.println("msg: Tied");
			// Do nothing
			break;
		default:
			throw(new Exception("compareCards yielded unrecognized comparison values: " + result));
		}

		return this;
	}
	// Check the game is ready to play
	public boolean isGamePrepared() {
		int msgFlags = 0, 
			PF_NOTENOUGHPLAYERS = 1,
			PF_NODECK = 2;
		// Set flags for messages if necessary
		if(mPlayer1 == null || mPlayer2 == null)
			msgFlags = msgFlags | PF_NOTENOUGHPLAYERS;
		if(mGameDeck == null)
			msgFlags = msgFlags | PF_NODECK;
		// Check flags, send message, and return false
		if(msgFlags != 0) {
			if((msgFlags & PF_NOTENOUGHPLAYERS) == PF_NOTENOUGHPLAYERS)
				System.out.println("msg: Not enough players.");
			if((msgFlags & PF_NODECK) == PF_NODECK)
				System.out.println("msg: No deck provided.");
			// CANNOT PLAY
			return false;
		}
		// CANNOT PLAY
		if(!mGameDeck.hasCards()) {
			System.out.println("msg: Card deck is empty.");
			return false;
		}
		// CAN PLAY
		return true;
	}

	// Deck shuffle
	public WarCardGame shuffleDeck(int maxNumberOfRandomShuffle) {
		int nShuffles = Math.abs(new Random().nextInt() % maxNumberOfRandomShuffle);
		System.out.println("msg: Number of times shuffled: " + nShuffles);
		while(nShuffles-- > 0)
			mGameDeck.shuffle();
		return this;
	}
	public WarCardGame dealCards() {
		while(mGameDeck.hasCards()) {
			mPlayer1.draw(mGameDeck);
			mPlayer2.draw(mGameDeck);
		}
		return this;
	}
	public WarCardGame announceWinner() {
		System.out.println("-----Game Result-----");
		System.out.println("Player 1: " + mPlayer1.getName() + " : " + mPlayer1.getScore());
		System.out.println("Player 2: " + mPlayer2.getName() + " : " + mPlayer2.getScore());
		System.out.println("Rounds played: " + nRounds);
		// Get winner: Check if there is a tie, otherwise get the high scorer's name
		String strWinner = (mPlayer1.getScore() == mPlayer2.getScore()) 
			? "TIED" 
			: (mPlayer1.getScore() > mPlayer2.getScore()) 
				? mPlayer1.getName() 
				: mPlayer2.getName();
		System.out.println("The winner is: " + strWinner);
		return this;
	}
}
